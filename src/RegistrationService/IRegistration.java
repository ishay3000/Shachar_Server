package RegistrationService;

import Ishay.MySqlUsersEntity;

public interface IRegistration {
    /**
     * registers the user to the database
     * @param usersEntity the user to be registered
     * @return if the user was registered
     */
    boolean register(MySqlUsersEntity usersEntity);
}
