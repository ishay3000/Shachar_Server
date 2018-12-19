package FutureServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler implements IClientHandler, IRequest {
    private DataOutputStream mOutStream;
    private DataInputStream mInStream;
    private Socket mClientSocket;
    @Override
    public void handleClient(Socket clientSocket) {
        mClientSocket = clientSocket;
        handleRequests();
    }

    /**
     * process the client's requests
     */
    private void handleRequests(){
        String request = "";
        while (true){
            request = getRequest();
            processRequest(request);
        }
    }

    @Override
    public String getRequest() {
        return null;
    }

    @Override
    public void processRequest(String request) {

    }
}
