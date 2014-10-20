import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
 
//import java.io.PrintWriter;

public class Mailer extends HttpServlet{
    
    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException
    {

	PrintWriter writer = response.getWriter();
	String name = request.getParameter("name");
	String org = request.getParameter("org");
	String form_title = request.getParameter("title");
	String email = request.getParameter("email");
	String type = request.getParameter("type");


	writer.println(request.getParameter("name"));
	writer.println(request.getParameter("org"));
	writer.println(request.getParameter("title"));
	writer.println(request.getParameter("email"));
	writer.println(request.getParameter("type"));
 
	// // Sender's email ID needs to be mentioned
	String from = "navigator@cancer-iq.com";
	//String to = "chrisbun@gmail.com";
	String to = "dev@cancer-iq.com";
 
	// // Assuming you are sending email from localhost
	String host = "localhost";
 
	// // Get system properties
	Properties properties = System.getProperties();
 
	// // Setup mail server
	// properties.setProperty("mail.smtp.user", "navigator@cancer-iq.com");
	// properties.setProperty("mail.password", "Cancer!&");
	properties.setProperty("mail.smtp.host", "smtp.office365.com");
	properties.setProperty("mail.smtp.port", "587");
	properties.setProperty("mail.smtp.auth", "true"); 
	properties.setProperty("mail.smtp.starttls.enable","true");
	// properties.setProperty("mail.smtp.socketFactory.port", d_port);
	// properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	// properties.setProperty("mail.smtp.socketFactory.fallback", "false");

	// // Get the default Session object.
	Session session = Session.getDefaultInstance(properties, 
	    new javax.mail.Authenticator(){
		protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication("navigator@cancer-iq.com", "Cancer!&");
		}
	    });
	//	Session session = Session.getDefaultInstance(properties);
      
	// // Set response content type
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	try{
	    // Create a default MimeMessage object.
	    MimeMessage message = new MimeMessage(session);
	    // Set From: header field of the header.
	    message.setFrom(new InternetAddress(from));
	    // Set To: header field of the header.
	    message.addRecipient(Message.RecipientType.TO,
				 new InternetAddress(to));
	    // Set Subject: header field
	    message.setSubject("[TEST] Demo Request");
	    // Now set the actual message
	    message.setText("Test message for people requesting demos on the CancerIQ site. \n" 
			    + "Name: " + name + "\n"
			    + "Org: " + org  + "\n"
			    + "Title: " + form_title + "\n"
			    + "email: " + email + "\n"
			    + "Type: " + type + "\n");
			    
	    // Send message
	    Transport.send(message);
	    String title = "Send Email";
	    String res = "Sent message successfully....";
         String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
	     "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n" +
         "<p align=\"center\">" + res + "</p>\n" +
		     "</body></html>");
	}catch (MessagingException mex) {
	    mex.printStackTrace();
	}
    }
} 

