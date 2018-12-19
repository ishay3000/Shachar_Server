import Ishay.MySqlUsersEntity;
import com.google.gson.Gson;
import dao.UserDAO;
import server.Server;

import java.util.List;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args) {

/*
        UserDAO userDAO = new UserDAO();*/
/*        MySqlUsersEntity user =
                new MySqlUsersEntity(5, "Dor", "Sucks", "1234", "example@example.com");
        CommunicationService.MessagePacket packet = new CommunicationService.MessagePacket();
        packet.command = "Register";
        packet.data = "{\"userid\":5,\"forename\":\"Dor\",\"surname\":\"Sucks\",\"password\":\"1234\",\"email\":\"example@example.com\"}";

        Gson g = new Gson();
        System.out.println(g.toJson(packet));*/

        //#region delete
        // add
//        userDAO.add(user);

        // update
/*        user.setForename("PHP");
        userDAO.update(user);

        List lst = userDAO.getAllItems();
        lst.stream()
                .forEach(System.out::println);*/
//#endregion

        Server.OUR_INSTANCE.run();
        Thread t = new Thread(Server.OUR_INSTANCE);
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //exit(0);
    }
}
