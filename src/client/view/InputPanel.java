package client.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class InputPanel extends JPanel {
    private final JTextField messageField;
    private final JButton sendButton;
    private final Color primaryColor = new Color(25, 118, 210);
    private Consumer<String> onSendMessage;

    public InputPanel() {
        setLayout(new BorderLayout(10, 0));
        setBackground(new Color(245, 245, 245));

        messageField = new JTextField();
        messageField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(primaryColor, 1, true),
            new EmptyBorder(8, 10, 8, 10)
        ));

        sendButton = new JButton("Envoyer");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);
        sendButton.setBorder(new EmptyBorder(8, 15, 8, 15));
        sendButton.setFocusPainted(false);
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setupListeners();
        
        add(messageField, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }

    private void setupListeners() {
        sendButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sendButton.setBackground(sendButton.getBackground().darker());
            }
            public void mouseExited(MouseEvent e) {
                sendButton.setBackground(new Color(0, 120, 215));
            }
        });

        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        
        messageField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER) && e.isControlDown()) {
                    sendMessage();
                }
            }
        });
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && onSendMessage != null) {
            onSendMessage.accept(message);
            messageField.setText("");
        }
    }

    public void setOnSendMessage(Consumer<String> onSendMessage) {
        this.onSendMessage = onSendMessage;
    }

    public void setFocus() {
        messageField.requestFocusInWindow();
    }
}
