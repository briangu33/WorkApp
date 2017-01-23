package utils;

import completables.TaskList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

    public static final String FROM_EMAIL = "taskappsender@gmail.com";
    public static final String TO_EMAIL = "zhukeepa@gmail.com";
    public static final String PASSWORD = "TaskAppSender123";

    public static void checkWantToEmail(String[] args, TaskList taskList) {
        if (args.length > 0 && args[0].equals("email")) {
            try {
                System.out.println("Sending email...");
                EmailUtils.sendEmail("Your Tasks", taskList.serialize());
                FileUtils.updateTaskList(taskList, FileUtils.FILE_PATH, FileUtils.OLD_TASKS_FILE_NAME);
                taskList.clearAllTasks();
                FileUtils.updateTaskList(taskList, FileUtils.FILE_PATH, FileUtils.FILE_NAME);
                System.out.println("Successfully sent email.");
                System.exit(0);
            } catch (Exception e) {
                System.out.println("An exception occurred while attempting to send email.");
            }
        }
    }

    /**
     * Code below taken from http://stackoverflow.com/questions/26935737/sending-an-email-from-java-using-apis-not-with-localhost
     */
    private static class SMTPAuthenticator extends Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
        }
    }

    public static void sendEmail(String subject, String messageText) throws MessagingException {

        // Get system properties
        Properties props = System.getProperties();
        props = new Properties();
        props.put("mail.smtp.user", "username@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        SMTPAuthenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);
        session.setDebug(false);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(messageText);
        msg.setSubject(subject);
        msg.setFrom(new InternetAddress(FROM_EMAIL));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_EMAIL));

        Transport transport = session.getTransport("smtps");
        transport.connect("smtp.gmail.com", 465, "username", "password");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();

    }

}
