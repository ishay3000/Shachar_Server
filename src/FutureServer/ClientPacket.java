package FutureServer;

import com.google.gson.Gson;

/**
 * client commands
 */
enum Commands {
    REGISTER, LOGIN, SEND, BROADCAST
}

enum Statuses {
    OK, USER_EXISTS, ERR, NULL
}

/**
 * A class representing a client's packet
 */
public class ClientPacket {
    static Gson gson = new Gson();
    Commands Command;
    String JsonFormattedData;
    Statuses Status = Statuses.NULL;

    /**
     * extract the data from the json data by a key
     *
     * @param key      the key in the json data
     * @param classOfT the item's class template to be used in the json extraction
     * @return the data retrieved by the key
     */
    public <T> T getDataByKey(String key, Class<T> classOfT) {
        T data = gson.fromJson(JsonFormattedData, classOfT);
        return data;
    }
}
