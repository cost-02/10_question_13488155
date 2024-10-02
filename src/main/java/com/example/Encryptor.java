package com.example;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Encryptor {
    public static void main(String[] args) throws Exception {
        String originalContent = new String(Files.readAllBytes(Paths.get("path/to/your.html")));
        byte[] keyBytes = new byte[16]; // Chiave AES 128 bit
        new SecureRandom().nextBytes(keyBytes);

        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        cipher.init(true, new KeyParameter(keyBytes));
        byte[] input = originalContent.getBytes();
        byte[] encryptedData = new byte[cipher.getOutputSize(input.length)];
        int length1 = cipher.processBytes(input, 0, input.length, encryptedData, 0);
        cipher.doFinal(encryptedData, length1);

        String encryptedHex = Hex.toHexString(encryptedData);
        Files.write(Paths.get("path/to/your_encrypted.html"), encryptedHex.getBytes());
    }
}
