package filecrypter;

import java.security.Key;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FileCrypter {
	private static final String ALGORITHM = "Blowfish";
	private static String keyString = "";

	public static void encrypt(File inputFile, File outputFile)
			throws Exception {
		doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);
		System.out.println("File encrypted successfully!");
	}

	public static void decrypt(File inputFile, File outputFile)
			throws Exception {
		doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
		System.out.println("File decrypted successfully!");
	}

	private static void doCrypto(int cipherMode, File inputFile,
			File outputFile) throws Exception {

		Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(cipherMode, secretKey);

		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] inputBytes = new byte[(int) inputFile.length()];
		inputStream.read(inputBytes);

		byte[] outputBytes = cipher.doFinal(inputBytes);

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();

	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		File input,newFile;
		Random rand = new Random();
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
		
        while(true){	
			System.out.println("\n\n####BlowFish Encryption System####\n\n");
			System.out.println("1.Ecrypt File\t2.Decrypt File\n\n");
			System.out.print("BlowFish>");
			String choice = scan.nextLine();
			if(choice.equals("1")){
				System.out.println("Enter A Password:");
				keyString = scan.nextLine();
				System.out.println("Enter File Location:");
				String inLoc = scan.nextLine();
				input = new File(inLoc);
				newFile = new File(inLoc);
		        while (salt.length() < 5) {
		            int index = (int) (rand.nextFloat() * SALTCHARS.length());
		            salt.append(SALTCHARS.charAt(index));
		        }
				if(input.isFile())	
		        	while(newFile.exists()){
						String[] name = newFile.getName().split("[.]");
						if(name.length < 2)	
							newFile = new File(newFile.getAbsolutePath().substring(0,newFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+salt.toString()+"_crypt");
						else
							newFile = new File(newFile.getAbsolutePath().substring(0,newFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+salt.toString()+"_crypt."+name[1]);
					}
				else{
					ZipHelper zip = new ZipHelper();
					try {
						System.out.println("Adding to archive...");
						zip.zipDir(inLoc, salt.toString()+".zip");
						System.out.println("Successfully created "+salt.toString()+".zip !");
						input = new File(salt.toString()+".zip");
						newFile = new File(inLoc+"/"+salt.toString()+".zip");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Invalid Directory!");
					}
				}
				try {
					encrypt(input,newFile);
					input.delete();
				}
				catch(IllegalArgumentException e){
					System.out.println("Invalid Password");
				}
				catch(FileNotFoundException e){
					System.out.println("Invalid File Location");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(choice.equals("2")){
				System.out.println("Enter File Location:");
				String inLoc = scan.nextLine();
				System.out.println("Enter Password:");
				keyString = scan.nextLine();
				input = new File(inLoc);
				newFile = new File(inLoc);
		        while (salt.length() < 7) {
		            int index = (int) (rand.nextFloat() * SALTCHARS.length());
		            salt.append(SALTCHARS.charAt(index));
		        }
				while(newFile.exists()){
					String[] name = newFile.getName().split("[.]");
					if(name.length < 2 )	
						newFile = new File(newFile.getAbsolutePath().substring(0,newFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+salt.toString()+"_dcrypt");
					else
						newFile = new File(newFile.getAbsolutePath().substring(0,newFile.getAbsolutePath().lastIndexOf(File.separator))+"/"+salt.toString()+"_dcrypt."+name[1]);
				}
				try {
					decrypt(input,newFile);
				} 
				catch(IllegalArgumentException e){
					System.out.println("Invalid Password");
				}
				catch(FileNotFoundException e){
					System.out.println("Invalid File Location");
				}
				catch(BadPaddingException e){
					System.out.println("Wrong Password!");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(choice.equals("exit"))
					break;
			else{
				System.out.println("Invalid Selection");
			}
		}
		scan.close();
	}

}
