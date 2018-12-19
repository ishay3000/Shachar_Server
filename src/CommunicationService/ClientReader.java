package CommunicationService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReader implements Runnable {
    private DataOutputStream mOutStream;
    private DataInputStream mInStream;
    private Socket clientSocket;
    private ClientHandler mClientHandler;


    public ClientReader(Socket clientSocket) {
        this.clientSocket = clientSocket;
        mClientHandler = new ClientHandler(clientSocket);
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

    @Override
    public void run() {
        while (true){
            mClientHandler.handleCommand();
        }
    }

}
