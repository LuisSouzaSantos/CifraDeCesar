import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    private static final String NEW_LINE = "\n";

    public static String readFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder contentBuilder = new StringBuilder();
            
            String line;
            
            while ((line = reader.readLine()) != null) { contentBuilder.append(line).append(NEW_LINE); }
            
            return contentBuilder.toString();
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
        	throw new IOException("Error reading file '" + fileName + "'");
        }
    }

    public static void writeFile(String fileName, String content) {
        try (FileOutputStream outputFile = new FileOutputStream(fileName);
             OutputStreamWriter writer = new OutputStreamWriter(outputFile)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error to write a file");
        }
    }
    
    public static void createFile(String fileName) throws IOException {
    	File file = new File(fileName);
    	
    	if (file.exists()) { return; }
    	
    	file.createNewFile();
    }
    
    public static void createBackuptFile(String originFileName, String backupFileName) throws IOException {
    	File originalFile = new File(originFileName);
    	
    	if (!originalFile.exists()) {  throw new FileNotFoundException("Error creating backup file"); }
    	
    	File backupFile = new File(backupFileName);
    	
    	Files.copy(originalFile.toPath(), backupFile.toPath());
    	
        if (!backupFile.exists()) { throw new IOException("Backup file was not created: " + backupFileName); }
    }
    
    public static String calculateChecksum(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int numRead;
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    md.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            fis.close();
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
