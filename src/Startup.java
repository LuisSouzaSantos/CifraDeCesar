import java.io.IOException;
import java.util.Scanner;

public class Startup {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int choosedOption = 0;
		String outputFile = "";
		String inputFile= "";
		int key = 0;

		while (choosedOption == 0) {
			boolean anyInformationIsWrong = false;
			
			try { 
				System.out.println("1 - Encrypt (Using Key) | 2 - Decrypt (Using Key)");
				choosedOption = scanner.nextInt();
				
				if (choosedOption <= 0 || choosedOption>=4) { throw new Exception("Wrong Option"); }
				
				System.out.println("Type the input file");
				inputFile = scanner.next();
				
				System.out.println("Type the output file");
				outputFile = scanner.next();
				
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
			
			if (choosedOption == 1) { encryptInformation(inputFile, outputFile, key); }
			if (choosedOption == 2) { decryptInformation(inputFile, outputFile, key); }
			
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
	}
	
}
