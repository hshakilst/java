package cryptography;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSACipher {
	
	public void generateKey(){
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);
			System.out.println("Generating Keys.....");
			KeyPair key = keyGen.generateKeyPair();
			System.out.println("Keys Generated!");
			System.out.println("Private Key:");
			System.out.println(Base64.getEncoder().encodeToString((key.getPrivate()).getEncoded()));
			System.out.println("-----------------------------------------------------------");
			System.out.println("Public Key:");
			System.out.println(Base64.getEncoder().encodeToString((key.getPublic()).getEncoded()));
			
			File privateKey = new File("C:/Users/Shakil/Desktop/RSACipherKeys/private.key");
			File publicKey = new File("C:/Users/Shakil/Desktop/RSACipherKeys/public.key");
			
			if (privateKey.getParentFile() != null) {
		        privateKey.getParentFile().mkdirs();
		    }
		    privateKey.createNewFile();

		    if (publicKey.getParentFile() != null) {
		    	publicKey.getParentFile().mkdirs();
		    }
		    publicKey.createNewFile();
		    
		    ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKey));
		    publicKeyOS.writeObject(Base64.getEncoder().encodeToString((key.getPublic()).getEncoded()));
		    publicKeyOS.close();
		    
		    ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKey));
		    privateKeyOS.writeObject(Base64.getEncoder().encodeToString((key.getPrivate()).getEncoded()));
		    privateKeyOS.close();
		    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean areKeysPresent() {

	    File privateKey = new File("C:/Users/Shakil/Desktop/RSACipherKeys/private.key");
	    File publicKey = new File("C:/Users/Shakil/Desktop/RSACipherKeys/public.key");

	    if (privateKey.exists() && publicKey.exists()) {
	      return true;
	    }
	    return false;
	}
	
	public byte[] encrypt(byte[] text, PublicKey key) {
	    byte[] cipherText = null;
	    try {
	      Cipher cipher = Cipher.getInstance("RSA");
	      cipher.init(Cipher.ENCRYPT_MODE, key);
	      cipherText = cipher.doFinal(text);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return cipherText;
	}
	
	public String decrypt(byte[] text, PrivateKey key) {
	    byte[] decryptedText = null;
	    String decryptText = null;
	    try {
	      Cipher cipher = Cipher.getInstance("RSA");
	      cipher.init(Cipher.DECRYPT_MODE, key);
	      decryptedText = cipher.doFinal(text);
	      decryptText = new String(decryptedText, "UTF-8");

	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    
	    return decryptText;
	}
	
	public byte[] readFromStream(InputStream inputStream, long size) throws Exception
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(baos);
	    byte[] data = new byte[(int) size];
	    int count = inputStream.read(data);
	    while(count != -1)
	    {
	        dos.write(data, 0, count);
	        count = inputStream.read(data);
	    }

	    return baos.toByteArray();
	}
}
