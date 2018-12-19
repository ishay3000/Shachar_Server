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
        Socket currentClient;
        while (true){
            // new client connection
            currentClient = mServerSocket.accept();
            //handle the new client
            mClientHandler.handleClient(currentClient);
        }
    }
}
