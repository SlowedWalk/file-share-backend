/*
package dev.hidetora.secureShare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VaultService {
    @Autowired
    private VaultTemplate vaultTemplate;

    public void writeSecret(String path, Map<String, String> secrets) {
        VaultKeyValueOperations operations = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        operations.put(path, secrets);
    }

    public Map<String, Object> readSecret(String path) {
        VaultResponse response = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get(path);
        return response.getData();
    }
}
*/
