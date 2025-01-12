package client.view;

import client.controller.ChatController;
import client.model.ChatMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;    

public class ChatFrame extends JFrame implements ChatController.ChatControllerListener {
    private final ChatController controller;
    private final ChatPanel chatPanel;
    private final InputPanel inputPanel;
    private final StatusPanel statusPanel;
    private final Color backgroundColor = new Color(245, 245, 245);

    public ChatFrame(ChatController controller) {
        this.controller = controller;
        controller.addListener(this);

        // Configuration de la fenêtre
        setTitle("Chat - " + controller.getPseudo());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(backgroundColor);

        // Création des composants
        statusPanel = new StatusPanel(controller.getPseudo());
        statusPanel.setOnDisconnect(() -> controller.disconnect());
        chatPanel = new ChatPanel();
        inputPanel = new InputPanel();
        inputPanel.setOnSendMessage(this::sendMessage);

        // Layout principal avec des marges
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnect();
            }
        });
    }

    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            controller.sendMessage(message);
        }
    }

    @Override
    public void onMessageReceived(ChatMessage message) {
        chatPanel.appendMessage(message);
    }

    @Override
    public void onConnectionStatusChanged(boolean connected) {
        statusPanel.updateStatus(connected);
    }

    @Override
    public void onError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
