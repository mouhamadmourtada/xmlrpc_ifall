package server;
import org.apache.xmlrpc.*;

public class Serveur {
    public static void main(String[] args) {
        System.out.println("Serveur");
        try { 
            
            System.out.println("Attempting to start XML-RPC Server...");

            WebServer server = new WebServer(80);
            server.addHandler("chatRoom", new ChatRoomImpl());
            server.start();
            System.out.println("Started successfully.");
            System.out.println("Accepting requests. (Halt program to stop.)");
            
        }
        catch (Exception exception){ 
            System.err.println("JavaServer: " + exception); 
        }
    }
}
