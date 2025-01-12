package interfaces;

import java.rmi.*; 
 
public interface ChatRoom {

    public int subscribe(String pseudo, String ip) throws RemoteException; 
    public void unsubscribe(String pseudo) throws RemoteException; 
    public boolean postMessage(String pseudo, String message) throws RemoteException; 
}