package client;

import java.util.Scanner;

import org.apache.xmlrpc.WebServer;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        String ip = "localhost";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pseudo: ");
        String pseudo = sc.nextLine();

        ChatClient client = ChatClient.subscribe(pseudo, ip);

        // creer un serveur, ecoute les 
        try { 
            
            // System.out.println("Attempting to start XML-RPC Server...");

            WebServer server = new WebServer(client.getPort());
            server.addHandler("client", client);
            server.start();
            
            
        }
        catch (Exception exception){ 
            System.err.println("JavaServer: " + exception); 
        }

        System.out.println("Enter your message: ");
        while (true) {
            String message = sc.nextLine();
            client.posteMessage(message);
        }
        

        
    }
}
