package FutureServer;

import Ishay.MySqlUsersEntity;
import dao.UserDAO;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    //#region Members
    // server singleton
    public static Server OUR_INSTANCE = new Server();
    // clients map
    private HashMap<Socket, Integer> mClientsMap;
    // listener
    private IListener mListener;

    private static int PORT = 8090;
    //#endregion


    private Server() {
        mClientsMap = new HashMap<>();
        mListener = new ClientListener();
    }

    public void start() {
        new Thread(() -> {
            try {
                System.out.println("Listening for connections...");
                mListener.listen(PORT);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    /**
     * attempts to register the user to the dabatase
     *
     * @param user the newly-added user
     * @return whether the client was registered
     */
    synchronized boolean registerUser(MySqlUsersEntity user) {
        return UserDAO.OUR_INSTANCE.add(user);
    }
}
