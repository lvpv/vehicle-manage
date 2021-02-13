package com.lv.vehicle.utils;

import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaUtil {

    public static PublicKey getPublicKey(KeyProperties keyProperties){
        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore.getLocation(),keyStore.getSecret().toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStore.getAlias());
        return keyPair.getPublic();
    }

    public static PrivateKey getPrivateKey(KeyProperties keyProperties){
        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore.getLocation(),keyStore.getSecret().toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStore.getAlias());
        return keyPair.getPrivate();
    }
}
