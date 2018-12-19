package FutureServer;

import java.net.Socket;

public interface IClientSender {
    /**
     * sends a message to a client
     *
     * @param clientSocket the client
     * @param packet       the message
     */
    public void send(Socket clientSocket, ClientPacket packet);
}
