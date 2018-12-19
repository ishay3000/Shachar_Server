package CommunicationService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.ws.api.message.Packet;
import server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.HashMap;


enum Commands{
    LOGIN, REGISTER, SEND, BROADCAST
}
/**
 * A class which handles client connections
 */
public class ClientHandler {
    private DataOutputStream mOutStream;
    private DataInputStream mInStream;
    private Socket clientSocket;
    private Gson gson;
//    private HashMap<Commands, >

    public ClientHandler(Socket currentClient) {
        clientSocket = currentClient;
        gson = new Gson();
        initStreams();
    }

    /**
     * initiates the IO streams for the client socket
     */
    private void initStreams() {
        try {
            mInStream = new DataInputStream(clientSocket.getInputStream());
            mOutStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * reads the command from the client
     * @return the command string
     */
    private String getCommand(){
        String command = "";
        try {
            command = mInStream.readUTF();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return command;
    }

    /**
     * processes the command retrieved from the client
     * @param command the client's command
     */
    private void processCommand(String command){
        // TODO process command
        // process the json string
        MessagePacket messagePacket = gson.fromJson(command, MessagePacket.class);
        String result = messagePacket.command;


        switch (result){
            case "Register":
                break;
            default:
                break;
        }
    }

    /**
     * handles the client's requests
     */
    public void handleCommand(){
        // GET
        String command = this.getCommand();
        // PROCESS
        processCommand(command);
    }
}
