package client;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import org.apache.xmlrpc.*;

public class ChatClient {

    private String pseudo;
    private Vector<String> params = new Vector<String>();
    private int port;

    // il doit avoir les méthodes posteMessage, et déconnexion

    public ChatClient(String pseudo) {
        this.pseudo = pseudo;
    }

    public ChatClient(String pseudo, int port) {
        this.pseudo = pseudo;
        this.port = port;
    }

    public boolean receiveMessage(String message) {
        System.out.println(message);
        return true;  // Retourne true pour indiquer que le message a été reçu avec succès
    }

    public void deconnexion() {
        // TODO
    }

    public boolean posteMessage(String message) {
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:80/RPC2");
            params.addElement(pseudo);
            params.addElement(message);
            
            Object result = server.execute("chatRoom.postMessage", params);
            return Boolean.TRUE.equals(result);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public static ChatClient subscribe(String pseudo, String ip) {
        // cree un client xmlrpc
        int result;
        XmlRpcClient server;
        try {
            server = new XmlRpcClient("http://localhost/RPC2");
            // cree un vecteur de parametres
            Vector<String> params = new Vector<String>();
            params.addElement(pseudo);
            params.addElement(ip);
            try {
                result =(int) server.execute("chatRoom.subscribe", params);
                System.out.println(result);
                
                // this

                ChatClient client = new ChatClient(pseudo);
                client.setPort(result);
                return client;

            } catch (XmlRpcException | IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}