package cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.Base64;

public class Main {
	public static void main(String[] args){
		System.out.println("\n\n####RSA(2048) Encryptor/Decryptor####");
		RSACipher rsa = new RSACipher();
		ObjectInputStream inputStream = null;
		Scanner scan = new Scanner(System.in);
		
		if(!rsa.areKeysPresent()){
			rsa.generateKey();
		}
		
		System.out.println("Choose From Below:");
		System.out.println("1.Encrypt\t2.Decrypt\n3.Encrypt File\t4.Decrypt File\n");
		System.out.print("RSACipher>");
		String s = scan.nextLine();
		
		if(s.equals("1")){
			System.out.println("Enter Text To Encrypt:");
			String text = scan.nextLine();
			try {
				inputStream = new ObjectInputStream(new FileInputStream("C:/Users/Shakil/Desktop/RSACipherKeys/public.key"));
				String encPubKeyStr = (String) inputStream.readObject();
				byte[] pubKeyByte = Base64.getDecoder().decode(encPubKeyStr);
				KeyFactory kf = KeyFactory.getInstance("RSA");
				PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(pubKeyByte));
				byte[] cipherText = rsa.encrypt(text.getBytes(), publicKey);
				String cipherStr = Base64.getEncoder().encodeToString(cipherText);
				System.out.println("Encrypted Text:");
				System.out.println(cipherStr);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] a = {"a","b"};
			main(a);
		}
		else if(s.equals("2")){
			System.out.println("Enter Text To Decrypt:");
			String text = scan.nextLine();
			byte[] cipherText = Base64.getDecoder().decode(text);
			try {
				inputStream = new ObjectInputStream(new FileInputStream("C:/Users/Shakil/Desktop/RSACipherKeys/private.key"));
				String encPvtKeyStr = (String) inputStream.readObject();
				byte[] pvtKeyByte = Base64.getDecoder().decode(encPvtKeyStr);
				KeyFactory kf = KeyFactory.getInstance("RSA");
				PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(pvtKeyByte));
				String decryptedText = rsa.decrypt(cipherText, privateKey);
				System.out.println("Decrypted Text:");
				System.out.println(decryptedText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] a = {"a","b"};
			main(a);
		}
		else if(s.equals("3")){
			System.out.println("Enter Absolute File Location:");
			String loc = scan.nextLine();
			File orFile = new File(loc);
			if(!orFile.exists()){
				System.out.println("Given file does not exist!");
			}
			else{
				InputStream is;
				try {
					is = new FileInputStream(loc);
					byte[] ofByte = rsa.readFromStream(is, orFile.length());
					inputStream = new ObjectInputStream(new FileInputStream("C:/Users/Shakil/Desktop/RSACipherKeys/public.key"));
					String encPubKeyStr = (String) inputStream.readObject();
					byte[] pubKeyByte = Base64.getDecoder().decode(encPubKeyStr);
					KeyFactory kf = KeyFactory.getInstance("RSA");
					PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(pubKeyByte));
					byte[] cryptFile = rsa.encrypt(ofByte, publicKey);
					File newFile = new File(orFile.getAbsolutePath().substring(0,orFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+orFile.getName()+".crypt");
					if(newFile.exists()){
						int i = 1;
						while(newFile.exists()){
							newFile = new File(orFile.getAbsolutePath().substring(0,orFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+orFile.getName()+Integer.toString(i)+".crypt");
						}	
					}
					newFile.createNewFile();
					OutputStream os = new FileOutputStream(newFile);
					os.write(cryptFile);
					os.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			String[] a = {"a","b"};
			main(a);
		}
		/*else if(s.equals("4")){
			
			
			String[] a = {"a","b"};
			main(a);
		}*/
		else{
			System.out.println("Invalid Selection");
			String[] a = {"a","b"};
			main(a);
		}
		scan.close();
	}
}
