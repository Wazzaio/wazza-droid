/*
 * wazza-droid
 * https://github.com/Wazzaio/wazza-droid
 * Copyright (C) 2013-2015  Duarte Barbosa, João Vazão Vasques
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.wazza.android.sdk.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap.SimpleEntry;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SecurityService {

    private final static int MAX_READ = 1024;

    private final static String algorithm = "AES/CBC/PKCS5Padding";

    public static SimpleEntry<byte[], byte[]> encrypt(String secretKey, byte[] decryptedData, byte[] IV) {

        try {
            System.out.println("Encrypting");

            //byte[] encodedKey = Base64.decode(secretKey, Base64.DEFAULT);
            byte[] hashKey = hash(secretKey);
            SecretKey key = new SecretKeySpec(hashKey, "AES");

            Cipher c = Cipher.getInstance(algorithm);
            if (IV == null) {
                c.init(Cipher.ENCRYPT_MODE, key);
            } else {
                c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
            }
            ByteArrayOutputStream encryptedData = new ByteArrayOutputStream();
            CipherOutputStream cos = new CipherOutputStream(encryptedData, c);
            cos.write(decryptedData);
            cos.close();
            //System.out.println("HERE  " + encryptedData.toByteArray().length);
            return new SimpleEntry<byte[], byte[]>(c.getIV(), encryptedData.toByteArray());
        } catch (Exception e) {
            //tba.
        } /*catch (NoSuchAlgorithmException e) {
            throw new FailedToEncryptException("Failed to encrypt. Algorithm chosen is not available on this machine!");
        } catch (NoSuchPaddingException e) {
            throw new FailedToEncryptException("Failed to encrypt. Padding chosen is not available on this machine!");
        } catch (InvalidKeyException e) {
            throw new FailedToEncryptException("Failed to encrypt. Secret Key is not valid!");
        } catch (FileNotFoundException e) {
            throw new FailedToEncryptException("Failed to encrypt. File not found!");
        } catch (InvalidAlgorithmParameterException e) {
            throw new FailedToEncryptException("Failed to encrypt. Algorithm parameter not available on this machine!");
        }catch (IOException e) {
            e.printStackTrace();
            throw new FailedToEncryptException("Failed to encrypt. I/O Exception!");
        }*/
        return null;
    }

    public static byte[] decrypt(SecretKey key, byte[] IV, byte[] encryptedData) {

        System.out.println("Decrypting");
        try {
            Cipher c = Cipher.getInstance(algorithm);

            c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
            CipherInputStream cis = new CipherInputStream(new ByteArrayInputStream(encryptedData), c);
            byte[] byteArray = new byte[MAX_READ];
            ByteArrayOutputStream decryptedData = new ByteArrayOutputStream();
            int len;
            //System.out.println("HERE " + encryptedData.length);
            while ((len = cis.read(byteArray)) != -1) {
                //System.out.println(len);
                decryptedData.write(byteArray, 0, len);
            }
            cis.close();
            decryptedData.close();
            return decryptedData.toByteArray();
        } catch (Exception e) {
            //tba.
        } /*catch (NoSuchAlgorithmException e) {
            throw new FailedToDecryptException("Failed to decrypt. Algorithm chosen is not available on this machine!");
        } catch (NoSuchPaddingException e) {
            throw new FailedToDecryptException("Failed to decrypt. Padding chosen is not available on this machine!");
        } catch (InvalidKeyException e) {
            throw new FailedToDecryptException("Failed to decrypt. Secret Key is not valid!");
        } catch (InvalidAlgorithmParameterException e) {
            throw new FailedToDecryptException("Failed to decrypt. Algorithm parameter not available on this machine!");
        } catch (IOException e) {
            throw new FailedToDecryptException("Failed to decrypt. I/O Exception!");
        }*/
        return null;
    }

    public static byte[] hash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = password.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            return passHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
