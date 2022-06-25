package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credentials> getCredentials(int userid){
        return credentialsMapper.getCredentials(userid);

    }
    public int insertCredentials(Credentials credentials){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        /**
         * encrypting the password before storing it
         */
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);
        return credentialsMapper.insertCredentials(new Credentials(null,credentials.getUrl(),credentials.getUsername(),encodedKey, encryptedPassword, credentials.getUserid()));

    }
    public int updateCredentials(Credentials credentials) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(),encodedKey);
        /**
         * while updating the credentials we are updating the key and password
         */
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        return credentialsMapper.updateCredentials(credentials);

    }

    public int deleteCredentials(int credentialId){
        return credentialsMapper.deleteCredentials(credentialId);
    }


}
