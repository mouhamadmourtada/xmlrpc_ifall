package server;

import interfaces.ChatUser;
import server.Appel;

public class ChatUserImpl implements ChatUser {
    private String pseudo;
    private String ip;
    private int id = 0;
    
    public static int port = 11111;

    public synchronized static int getNewPort() {
        return ++port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatUserImpl() {
        this.id = ChatUserImpl.getNewPort();
    }

    public ChatUserImpl(String pseudo) {
        this.id = ChatUserImpl.getNewPort();
        this.pseudo = pseudo;
        this.ip = "127.0.0.1:" + this.id;  // Initialisation de l'IP avec le port
    }

    // @Override
    public boolean displayMessage(String message) {
        try {
            Appel.requete(this.pseudo, message, ip, this.id);
            return true;  // Retourne true si le message est envoyé avec succès
        } catch (Exception e) {
            e.printStackTrace();
            
            return false;  // Retourne false en cas d'erreur
        }
    }


    public void setIp(String ip) {
        this.ip = ip;
    }

    


    // @Override
    public String getPseudo() {
        return pseudo;
    }
}