package io.github.alizarion.common.secure.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class KeyStoreUtilsTest {

    @Test
    public void testKeyStore() throws IOException, NoSuchAlgorithmException {
        Key key = KeyStoreUtils.loadKey();
        Assert.assertNotNull(key);
    }

}