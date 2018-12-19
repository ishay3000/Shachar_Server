package FutureServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable {

    //#region Members
    // server singleton
    public static Server OUR_INSTANCE = new Server();
    // clients map
    private HashMap<Socket, Integer> mClientsMap;
    // listener
    IListener mListener;

    private static int PORT = 8090;
    //#endregion


    public Server() {
        mClientsMap = new HashMap<>();
        mListener = new ClientListener();
    }

    @Override
    public void run() {
        try {
            mListener.listen(PORT);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
