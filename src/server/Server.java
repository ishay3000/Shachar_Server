package server;

import CommunicationService.ClientHandler;
import CommunicationService.ClientReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * A Singleton representing tcp server
 */
public class Server implements Runnable {

    //#region Members
    public static Server OUR_INSTANCE = new Server();
    private HashMap<Socket, Integer> mClientsMap;
    private ServerSocket mServerSocket;
    private static int PORT = 8090;
    //#endregion

//    public static HashMap<HashMap<Integer, String>, HashSet<MySqlUsersEntity>> x;lololol

    private Server() {
    }

    @Override
    public void run() {
        try {
            mServerSocket = new ServerSocket(PORT);
            mClientsMap = new HashMap<>();
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * listens for new connections
     * @throws IOException who cares
     */
    private void listen() throws IOException {
        Socket currentClient = null;

        System.out.println("Listening for clients...");
        while (true) {
            // accept new client
            currentClient = mServerSocket.accept();
            // TODO start a new thread for communication with the client
            //new Thread(new ClientReader(currentClient)).start();
            System.out.println("[+] client connected");
            ClientReader reader = new ClientReader(currentClient);
            reader.run();
        }
    }

    public void addClientByID(int id){

    }

    public int getClientBySocket(Socket clientSocket){
        Integer id = mClientsMap.get(clientSocket);
        return id;
    }
}
