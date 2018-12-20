package FutureServer;

import Ishay.MySqlUsersEntity;
import dao.LoginDetails;
import dao.UserDAO;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    //#region Members
    // server singleton
    public static Server OUR_INSTANCE = new Server();
    // clients map
    private HashMap<Socket, MySqlUsersEntity> mClientsMap;
    // listener
    private IListener mListener;

    private static int PORT = 8090;
    //#endregion


    private Server() {
        mClientsMap = new HashMap<Socket, MySqlUsersEntity>();
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

    /**
     * attempts to perform a login to a uname-pass set
     *
     * @param uID  user ID
     * @param pass user password
     * @return null of usersEntity if the login was successful
     */
    synchronized MySqlUsersEntity login(int uID, String pass) {
        LoginDetails loginDetails = new LoginDetails(uID, pass);
        return UserDAO.OUR_INSTANCE.getItemByID(loginDetails);
    }

    /**
     * adds the client socket to the clients map
     *
     * @param clientSocket the client socket
     * @param usersEntity  the user object of the client, retrieved by the login method
     */
    synchronized void addClientToMap(Socket clientSocket, MySqlUsersEntity usersEntity) {
        mClientsMap.put(clientSocket, usersEntity);
    }
}
