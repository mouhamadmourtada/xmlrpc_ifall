package interfaces;

/**
 
 */
public interface ChatUser { 
    
    public boolean displayMessage(String message);  // Changé void en boolean pour XML-RPC
    public String getPseudo();
    public int getId();
    // publi
}