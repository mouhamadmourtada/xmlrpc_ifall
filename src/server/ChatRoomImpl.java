package server;
// Chat must be imported from a JAR file named ChatInterfaces.jar. this jar file is located in the archive folder. the archive folder is located in ../
// import List

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import interfaces.ChatRoom;
import interfaces.ChatUser;



public class ChatRoomImpl implements ChatRoom {

    private final List<ChatUser> clients = new ArrayList<ChatUser>();
    
    // uddi
    // wsdl
    // soap

    public ChatRoomImpl() {
        super();
    }

        // clients.put(pseudo, ip);
    public int subscribe(String pseudo, String ip) throws RemoteException {
        // clients.put(pseudo, ip);

        ChatUser client = new ChatUserImpl(pseudo);

        clients.add(client);
        return client.getId();
    }

    
    @Override
    public boolean postMessage(String pseudo, String message) throws RemoteException {
        boolean success = true;
        for (ChatUser client : clients) {
            try {
                success &= client.displayMessage(pseudo + " : " + message);
                System.out.println("Requete : " + pseudo + " : " + message);
            } catch (Exception e) {
                e.printStackTrace();

                // remove the client from the list of clients
                clients.remove(client);

                success = false;
            }
        }
        return success;
    }

    @Override
    public void unsubscribe(String pseudo) throws RemoteException {

        clients.removeIf(client -> client.getPseudo().equals(pseudo));

    }
}