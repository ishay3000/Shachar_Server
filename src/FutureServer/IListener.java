package FutureServer;

import java.io.IOException;

/**
 * interface for listening to clients
 */
public interface IListener {
    /**
     * listens for client connections
     * @param port server port to listen on
     * @throws IOException who cares
     */
    public void listen(int port) throws IOException;
}
