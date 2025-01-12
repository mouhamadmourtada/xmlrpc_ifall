package client;

import org.apache.xmlrpc.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    private String pseudo;
    private Vector<String> params = new Vector<String>();
    private int port;
    private WebServer server;
    // private static int id = 11111;
    private List<ChatMessageListener> messageListeners = new ArrayList<>();

    public interface ChatMessageListener {
        void onMessageReceived(String message);
    }

    public void addMessageListener(ChatMessageListener listener) {
        messageListeners.add(listener);
    }

    public void removeMessageListener(ChatMessageListener listener) {
        messageListeners.remove(listener);
    }

    public ChatClient(String pseudo) {
        this.pseudo = pseudo;
        this.port = getNewPort();
        subscribe();
        initializeXmlRpcServer();
    }

    private static synchronized int getNewPort() {
        return 11111;
    }

    private void initializeXmlRpcServer() {
        try {
            server = new WebServer(port);
            server.addHandler("client", this);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start XML-RPC server", e);
        }
    }

    private void subscribe() {
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:80/RPC2");
            Vector<String> params = new Vector<String>();
            params.addElement(pseudo);
            params.addElement("localhost:" + port);
            Object result = server.execute("chatRoom.subscribe", params);
            this.port = (Integer) result;
            // if (result instanceof Integer) {
            // }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to subscribe to chat room", e);
        }
    }

    public boolean receiveMessage(String message) {
        // Notifier tous les listeners
        for (ChatMessageListener listener : messageListeners) {
            listener.onMessageReceived(message);
        }
        System.out.println(message);
        return true;
    }

    public void deconnexion() {
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:80/RPC2");
            Vector<String> params = new Vector<String>();
            params.addElement(pseudo);
            server.execute("chatRoom.unsubscribe", params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.shutdown();
            }
        }
    }

    public boolean posteMessage(String message) {
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:80/RPC2");
            params.clear();
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

    public int getPort() {
        return port;
    }
}