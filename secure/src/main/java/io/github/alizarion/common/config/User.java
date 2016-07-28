package io.github.alizarion.common.config;

import java.util.Set;

/**
 * Created by sbn on 28/07/2016.
 */
public class User {
    String username;
    String password;
    Set<String> roles;

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }
}