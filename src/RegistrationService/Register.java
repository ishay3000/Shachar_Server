package RegistrationService;

import Ishay.MySqlUsersEntity;
import dao.UserDAO;

public class Register implements IRegistration {
    public static Register OUR_INSTANCE;

    private Register(){}

    @Override
    public boolean register(MySqlUsersEntity usersEntity) {
        // if user exists
        if (UserDAO.OUR_INSTANCE.getItemByID(usersEntity.getUserid()) != null){
            // user exists
            return false;
        } else{
            // new user
            UserDAO.OUR_INSTANCE.add(usersEntity);
            return true;
        }
    }
}
