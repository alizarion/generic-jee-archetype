package io.github.alizarion.common.secure.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SecurityUtilsTest {

    @Test
    public void createToken() throws IOException, NoSuchAlgorithmException {
        String token = SecurityUtils.createToken(new HashMap<String,String>());
        Assert.assertNotNull(token);
    }

}