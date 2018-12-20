package FutureServer;

import Ishay.MySqlUsersEntity;
import com.google.gson.Gson;
import dao.LoginDetails;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements IClientHandler, IRequest {
    //#region Members
    private DataOutputStream mOutStream;
    private DataInputStream mInStream;
    private Socket mClientSocket;
    private IClientSender mClientSender;
    private Gson gson;
    //#endregion

    @Override
    public void handleClient(Socket clientSocket) {
        mClientSocket = clientSocket;
        mClientSender = new ClientSender();
        gson = new Gson();
        this.initIOStreams();
        handleRequests();
    }

    /**
     * initiates the client's IO streams
     */
    private void initIOStreams() {
        try {
            mOutStream = new DataOutputStream(mClientSocket.getOutputStream());
            mInStream = new DataInputStream(mClientSocket.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * process the client's requests
     */
    private void handleRequests(){
        String request = "";
        while (true){
            request = getRequest();
            processRequest(request);
        }
    }

    @Override
    public String getRequest() {
        String request = "";
        try {
            request = mInStream.readUTF();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return request;
    }

    /**
     * sends the client a feedback regarding their request
     *
     * @param clientRequestStatus the status of the client request's operation
     */
    private void sendFeedbackPacket(Commands clientRequestCommand, boolean clientRequestStatus) {
        // assemble the packet
        ClientPacket packet = new ClientPacket();
        packet.Command = clientRequestCommand;
        packet.Status = clientRequestStatus ? Statuses.OK : Statuses.ERR;

        // send the packet
        mClientSender.send(mClientSocket, packet);
    }

    @Override
    public void processRequest(String request) {
        ClientPacket clientPacket = gson.fromJson(request, ClientPacket.class);
        Commands command = clientPacket.Command;
        switch (command) {
            case REGISTER:
                // extract the user from the json
                MySqlUsersEntity user = clientPacket.getDataByKey("User", MySqlUsersEntity.class);
                // attempt to register user
                boolean isRegisterOK = Server.OUR_INSTANCE.registerUser(user);
                this.sendFeedbackPacket(command, isRegisterOK);
                break;
            case SEND:
                break;
            case LOGIN:
                LoginDetails loginDetails = clientPacket.getDataByKey("LoginDetails", LoginDetails.class);
                MySqlUsersEntity usersEntity = Server.OUR_INSTANCE.login(loginDetails.uID, loginDetails.pass);
                boolean isLoginOK = usersEntity != null;
                if (isLoginOK) {
                    Server.OUR_INSTANCE.addClientToMap(mClientSocket, usersEntity);
                }
                // send feedback
                sendFeedbackPacket(clientPacket.Command, isLoginOK);
                break;
            case BROADCAST:
                break;
            default:
                System.err.println("Invalid command from client");
                break;
        }
    }
}
