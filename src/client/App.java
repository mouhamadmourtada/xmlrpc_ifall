package client;

import client.controller.ChatController;
import client.view.ChatFrame;

import javax.swing.*;
import java.util.Scanner;

import org.apache.xmlrpc.WebServer;

/**
 * 
 */
public class App {
    public static void main(String[] args) {
        try {
            // Set Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pseudo = JOptionPane.showInputDialog(null, 
            "Entrez votre pseudo:", 
            "Connexion", 
            JOptionPane.QUESTION_MESSAGE);

        if (pseudo != null && !pseudo.trim().isEmpty()) {
            SwingUtilities.invokeLater(() -> {
                ChatController controller = new ChatController(pseudo.trim());
                ChatFrame frame = new ChatFrame(controller);
                frame.setVisible(true);
            });
        }
    }
}
