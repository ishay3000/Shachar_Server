package FutureServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener implements IListener {
    private ServerSocket mServerSocket;
    private IClientHandler mClientHandler;


    public ClientListener() {
        mClientHandler = new ClientHandler();
    }

    @Override
    public void listen(int port) throws IOException {
        mServerSocket = new ServerSocket(port);
        // indefinitely listen for new clients
        while (true){
            /*
            declaring socket inside while loop because lambda in thread forces you
            to transfer a final or effectively final variable
            */
            // accept new client connection
            Socket currentClient = mServerSocket.accept();
            System.out.println("[+] Client connected on " + currentClient.getInetAddress());

            // handle the new client
            new Thread(() -> mClientHandler.handleClient(currentClient)).start();
            mClientHandler.handleClient(currentClient);
        }
    }
}
