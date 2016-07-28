package io.github.alizarion.common.config;

import io.github.alizarion.common.config.UserDB;
import io.github.alizarion.common.secure.exception.ApplicationError;
import io.github.alizarion.common.secure.login.AuthenticateProvider;
import io.github.alizarion.common.secure.login.AuthenticationProvider;
import io.github.alizarion.common.secure.utils.SecurityUtils;

import javax.inject.Named;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author sbn
 */
@AuthenticateProvider
@Named //for spring compatiblity ...
public class SecurityProvider implements AuthenticationProvider {


    @Override
    public Boolean authenticate(String username, String password) {

        if (username!=null&password!= null){
            User user =  UserDB.findUserByUsername(username);
            if (user!=null){
                return user.getPassword().equals(password);
            }

        }
        return false;
    }

    @Override
    public String issueToken(String username) {
        Map claims  = new HashMap<String,String>();
        UserDB.findUserByUsername(username).getRoles();
        claims.put("role",
                new ArrayList<>(UserDB.findUserByUsername(username).getRoles()));
        try {
            return SecurityUtils.createToken(username,"jee-generic-archetype",claims);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new ApplicationError();
        }
    }


}
