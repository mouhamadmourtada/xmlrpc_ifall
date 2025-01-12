package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StatusPanel extends JPanel {
    private final JLabel statusLabel;
    private final Color textColor = new Color(33, 33, 33);
    private final JButton disconnectButton;

    public StatusPanel(String username) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        
        statusLabel = new JLabel("Connecté");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(76, 175, 80));
        statusLabel.setIcon(createStatusIcon(true));
        
        JLabel userLabel = new JLabel(username);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        userLabel.setForeground(textColor);

        disconnectButton = new JButton("Déconnecter");
        disconnectButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        disconnectButton.setBackground(new Color(220, 53, 69));
        disconnectButton.setForeground(Color.WHITE);
        disconnectButton.setOpaque(true);
        disconnectButton.setBorderPainted(false);
        disconnectButton.setFocusPainted(false);
        disconnectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(statusLabel);
        add(new JSeparator(JSeparator.VERTICAL));
        add(userLabel);
        add(Box.createHorizontalGlue());
        add(disconnectButton);
    }

    public void setOnDisconnect(Runnable onDisconnect) {
        disconnectButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment vous déconnecter ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                onDisconnect.run();
                System.exit(0);
            }
        });
    }

    private ImageIcon createStatusIcon(boolean online) {
        int size = 8;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(online ? new Color(76, 175, 80) : Color.RED);
        g2d.fillOval(0, 0, size, size);
        g2d.dispose();
        return new ImageIcon(image);
    }

    public void updateStatus(boolean connected) {
        statusLabel.setText(connected ? "Connecté" : "Déconnecté");
        statusLabel.setForeground(connected ? new Color(76, 175, 80) : Color.RED);
        statusLabel.setIcon(createStatusIcon(connected));
        disconnectButton.setEnabled(connected);
    }
}
