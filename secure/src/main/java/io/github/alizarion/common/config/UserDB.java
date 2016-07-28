package io.github.alizarion.common.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sbn
 */
public class UserDB {


    public static final Set<User> users = Collections.synchronizedSet(new HashSet<User>(Arrays.asList(
            new User("admin", "password", new HashSet<String>(Arrays.asList("admin"))),
            new User("selim", "a", new HashSet<String>(Arrays.asList("user"))),
            new User("user", "password", new HashSet<String>(Arrays.asList("user"))))));


    public static   User findUserByUsername(final String username){
        for (User user : users){
            if (user.getUsername().equalsIgnoreCase(username) ){
                return user;
            }

        }
        return null;
    }
}
