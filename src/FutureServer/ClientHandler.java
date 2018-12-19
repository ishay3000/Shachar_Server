package FutureServer;

import Ishay.MySqlUsersEntity;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements IClientHandler, IRequest {
    //#region Members
    private DataOutputStream mOutStream;
    private DataInputStream mInStream;
    private Socket mClientSocket;
    private Gson gson;
    //#endregion

    @Override
    public void handleClient(Socket clientSocket) {
        mClientSocket = clientSocket;
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

    @Override
    public void processRequest(String request) {
        ClientPacket clientPacket = gson.fromJson(request, ClientPacket.class);
        Commands command = clientPacket.Command;
        switch (command) {
            case REGISTER:
                // extract the user from the json
                MySqlUsersEntity user = clientPacket.getDataByKey("User", MySqlUsersEntity.class);
                // attempt to register user
                Server.OUR_INSTANCE.registerClient(user);
                break;
            case SEND:
                break;
            case LOGIN:
                break;
            case BROADCAST:
                break;
            default:
                System.err.println("Invalid command from client");
                break;
        }
    }
}
