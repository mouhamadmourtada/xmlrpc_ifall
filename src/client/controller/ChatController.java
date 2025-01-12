package client.controller;

import client.model.ChatMessage;
import client.ChatClient;
import java.util.ArrayList;
import java.util.List;

public class ChatController implements ChatClient.ChatMessageListener {
    private final ChatClient chatClient;
    private final List<ChatControllerListener> listeners;

    public interface ChatControllerListener {
        void onMessageReceived(ChatMessage message);
        void onConnectionStatusChanged(boolean connected);
        void onError(String errorMessage);
    }

    public ChatController(String pseudo) {
        this.chatClient = new ChatClient(pseudo);
        this.listeners = new ArrayList<>();
        this.chatClient.addMessageListener(this);
    }

    public void addListener(ChatControllerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ChatControllerListener listener) {
        listeners.remove(listener);
    }

    public void sendMessage(String message) {
        boolean success = chatClient.posteMessage(message);
        if (!success) {
            notifyError("Failed to send message");
        }
    }

    @Override
    public void onMessageReceived(String message) {
        // Expected format: "sender: content"
        String sender = "";
        String content = message;
        
        int colonIndex = message.indexOf(": ");
        if (colonIndex != -1) {
            sender = message.substring(0, colonIndex);
            content = message.substring(colonIndex + 2);
        }
        
        ChatMessage chatMessage = new ChatMessage(sender, content);
        notifyMessageReceived(chatMessage);
    }

    private void notifyMessageReceived(ChatMessage message) {
        for (ChatControllerListener listener : listeners) {
            listener.onMessageReceived(message);
        }
    }

    private void notifyConnectionStatus(boolean connected) {
        for (ChatControllerListener listener : listeners) {
            listener.onConnectionStatusChanged(connected);
        }
    }

    private void notifyError(String errorMessage) {
        for (ChatControllerListener listener : listeners) {
            listener.onError(errorMessage);
        }
    }

    public String getPseudo() {
        return chatClient.getPseudo();
    }

    public void disconnect() {
        chatClient.deconnexion();
        notifyConnectionStatus(false);
    }
}
