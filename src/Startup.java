import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Startup {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int choosedOption = 0;
		String filePath= "";
		int key = 0;

		while (choosedOption == 0) {
			boolean anyInformationIsWrong = false;
			
			try { 
				System.out.println("1 - Encrypt (Using Key) | 2 - Decrypt (Using Key)");
				choosedOption = scanner.nextInt();
				
				if (choosedOption <= 0 || choosedOption>=4) { throw new Exception("Wrong Option"); }
				
				System.out.println("Type the file path");
				filePath = scanner.next();
				
				if (choosedOption != 3) {
					System.out.println("Type the cipher key number to use");
					key = scanner.nextInt();
				}
			
			}catch(Exception exception) { 
				System.out.println("Any information typed is wrong");
				choosedOption = 0;
				anyInformationIsWrong = true; 	
			}
			
			if (anyInformationIsWrong) { continue; }
			
			if (choosedOption == 1) { encryptInformation(filePath, filePath, key); }
			if (choosedOption == 2) { decryptInformation(filePath, filePath, key); }
			
		}
		
		scanner.close();
	}
	
	public static void encryptInformation(String unecryptedFileName, String encryptedFileName, int cipherKey) throws IOException {
		System.out.println("File path to get the unecrypted information: "+ unecryptedFileName);
		System.out.println();
		System.out.println("File path to send the encrypted information: "+ encryptedFileName);
		System.out.println();
		System.out.println("Cipther key to be used: "+ Integer.toString(cipherKey));
		
		String unecryptedInformation = Utils.readFile(unecryptedFileName);
		
		File backupFile = new File(unecryptedFileName);
		String backupFileName = backupFile.getName().split("\\.")[0]+ "Backup.txt";
		Utils.createBackuptFile(unecryptedFileName,backupFile.getParent()+"/"+backupFileName);
				
		String encryptedInformation = Cipher.encode(unecryptedInformation, cipherKey);
		Utils.writeFile(encryptedFileName, encryptedInformation);	
	}
	
	public static void decryptInformation(String encryptedFileName, String unencryptedFileName, int cipherKey) throws IOException {
		System.out.println("File path to get the encrypted information: "+ encryptedFileName);
		System.out.println();
		System.out.println("File path to send the unecrypted information: "+ unencryptedFileName);
		System.out.println();
		System.out.println("Cipther key to be used: "+ Integer.toString(cipherKey));
		
		String encryptedInformation = Utils.readFile(encryptedFileName);
		
		String unecryptedInformation = Cipher.decode(encryptedInformation, cipherKey);
		Utils.writeFile(unencryptedFileName, unecryptedInformation);	
		
		String backupFileName = new File(encryptedFileName).getName().split("\\.")[0]+ "Backup.txt";
		
		File unencryptedFile = new File(encryptedFileName);
		File backupFile = new File(unencryptedFile.getParent()+"/"+backupFileName);
		
		String backupCheckSum = Utils.calculateChecksum(backupFile);
		String unencryptedFileSum = Utils.calculateChecksum(unencryptedFile);
		
		System.out.println("Check Sum: "+ (backupCheckSum.equals(unencryptedFileSum) ? "is done and everying " : "not done as espected"));
	}
	
}
