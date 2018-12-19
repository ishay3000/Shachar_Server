package FutureServer;

import java.net.Socket;

public interface IClientHandler {
    /**
     * handles client sockets after the server accepting them
     * @param clientSocket the newly-added client socket
     */
    public void handleClient(Socket clientSocket);
}
