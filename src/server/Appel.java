package server;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;

public class Appel {
    
    public static boolean requete(String pseudo, String message, String ip, int port) throws Exception {
        System.out.println("Requete : " + pseudo + " : " + message);

        String url = "http://" + ip + "/RPC2";
        System.out.println("URL : " + url);
        XmlRpcClient server = new XmlRpcClient(url);
        Vector<String> params = new Vector<String>();

        params.addElement(message);
        Object result = server.execute("client.receiveMessage", params);
        return Boolean.TRUE.equals(result);  // Convertit le résultat en boolean
    }
}
