import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
	
}
