package FutureServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * client commands
 */
enum Commands {
    REGISTER, LOGIN, SEND, BROADCAST
}

enum Statuses {
    OK, ERR, NULL
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
        JsonObject jsonObject = gson.fromJson(JsonFormattedData, JsonObject.class);
        String specificData = jsonObject.get(key).toString();
        T data = gson.fromJson(specificData, classOfT);
        return data;
    }
}
