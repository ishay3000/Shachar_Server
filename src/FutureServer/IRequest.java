package FutureServer;

public interface IRequest {
    /**
     * gets the client's request
     * @return the request as a json string
     */
    String getRequest();

    /**
     * processes the client's request
     * @param request the client's request, retrieved by getRequest()
     */
    void processRequest(String request);
}
