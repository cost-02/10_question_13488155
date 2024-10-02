package com.example;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Decryptor {
    public static void main(String[] args) throws Exception {
        byte[] keyBytes = new byte[16]; // La stessa chiave usata per la crittografia
        byte[] encryptedData = Hex.decode(new String(Files.readAllBytes(Paths.get("path/to/your_encrypted.html"))));

        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        cipher.init(false, new KeyParameter(keyBytes));
        byte[] decryptedData = new byte[cipher.getOutputSize(encryptedData.length)];
        int length1 = cipher.processBytes(encryptedData, 0, encryptedData.length, decryptedData, 0);
        cipher.doFinal(decryptedData, length1);

        String decryptedContent = new String(decryptedData).trim();
        System.out.println("Decrypted: " + decryptedContent);
    }
}
