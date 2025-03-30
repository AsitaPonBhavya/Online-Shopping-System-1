import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailTest {

    public static void main(String[] args) {
        String host = "smtp.gmail.com";  // SMTP server
        String from = "asitaponbhavya@gmail.com";  // Your email address
        String to = "asitaponbhavyai@gmail.com";  // Recipient's email address
        String password = "Asita2@2003";  // Your email password (or App Password)

        // Email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Get session and authenticate
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test Email");
            message.setText("This is a test email.");

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
