
public class Cipher {
	
    private static final String ALPHABET = "AaÁáÀàÂâBbCcÇçDdEeÉéÊêFfGgHhIiÍíJjKkLlMmNnOoÓóÔôPpQqRrSsTtUuÚúÜüVvWwXxYyZz";
    private static final String RECOVER_MESSAGE = "#### FAZ O PIX BB: CHAVE 123.456.789.12 ####";

    public static String decode(String message, int key) {
    	key = findTheCurrentKey(key);
    	
        String decodedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char currentCharacter = message.charAt(i);
            
            if (currentCharacter == '#') { break; }
            
            int index = ALPHABET.indexOf(currentCharacter);
            if (index != -1) {
                int decodedIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
                decodedMessage += ALPHABET.charAt(decodedIndex);
            } else {
                decodedMessage += currentCharacter;
            }
        }

        return decodedMessage;
    }
    
    public static String encode(String message, int key) {
    	key = findTheCurrentKey(key);
    	
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentCharacter = message.charAt(i);
            
            int index = ALPHABET.indexOf(currentCharacter);
            if (index != -1) {
                int encodedIndex = (index + key) % ALPHABET.length();
                encodedMessage.append(ALPHABET.charAt(encodedIndex));
            } else {
                encodedMessage.append(currentCharacter);
            }
        }
        
        encodedMessage.append("\n\n"+RECOVER_MESSAGE);
        
        return encodedMessage.toString();
    }
    
    private static int findTheCurrentKey(int previousKey) {
    	if (previousKey <= (ALPHABET.length()-1)) { return previousKey; }
    	
    	boolean foundCorrentKey = false;
    	
    	while (!foundCorrentKey) {
    		previousKey = previousKey - ALPHABET.length();
    		
    		if (previousKey <= (ALPHABET.length()-1)) { foundCorrentKey = true; }
    	}
    	
    	return previousKey;
    }
    

}
