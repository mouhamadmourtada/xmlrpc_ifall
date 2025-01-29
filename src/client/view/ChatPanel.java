package client.view;

import client.model.ChatMessage;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;  
import java.util.Timer;
import java.util.TimerTask;
// importer les border
import javax.swing.border.*;

public class ChatPanel extends JScrollPane {
    private final JTextPane chatArea;
    private final SimpleDateFormat timeFormat;
    
    public ChatPanel() {
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        
        // Initialiser le document HTML
        chatArea.setContentType("text/html");
        chatArea.setText("<html><body id='chat-content' style='font-family: Segoe UI; font-size: 14pt;'></body></html>");
        chatArea.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        setBorder(new CompoundBorder(
            new EmptyBorder(0, 0, 10, 0),
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true)
        ));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setViewportView(chatArea);
    }

    public void appendMessage(ChatMessage message) {
        String timestamp = timeFormat.format(message.getTimestamp());
        String htmlMessage = String.format(
            "<div style='background-color: #f8f8f8; box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset ; margin: 5px 0; padding: 5px;'>" +
            "<span style='color: #0066cc; font-weight: bold;'>%s</span>" +
            "<span style='color: #666666; font-size: 8px;'>%s</span> " +
            "<div style='margin: 2px 0 2px 2px; color: black;'>%s</div>" +
            "</div>",
            message.getSender(), timestamp, message.getContent()
        );
        
        try {
            HTMLDocument doc = (HTMLDocument) chatArea.getDocument();
            Element body = doc.getElement("chat-content");
            if (body != null) {
                doc.insertBeforeEnd(body, htmlMessage);
                chatArea.setCaretPosition(doc.getLength());
            } else {
                chatArea.setText(chatArea.getText() + htmlMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
