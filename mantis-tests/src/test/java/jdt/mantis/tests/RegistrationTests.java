package jdt.mantis.tests;

import jdt.mantis.model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class RegistrationTests extends TestBase {

  //@BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now=System.currentTimeMillis();

    String email =String.format( "user%s@localhost.localdomain",now);
    // tworzenie uzytkownika na serwerze pocztowym

    String user = String.format("user%s", now);
    String password = "password";
    app.james().createUser(user ,password);
    app.registration().start(user, email);
    // czekamy na 2 maile 10000ms=10s
   // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    //odbieramy mail z zewnetrznego servera
    List<MailMessage> mailMessages =app.james().waitForMail(user, password, 60000);
    //znajdujemy mail wysłany na dany adres i wyciagamy z niego link
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user,password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
   // wyrazenie reg które szuka wyrażenia zaczynajacego sie od http a po nim inne znaki nie bedace spacja jeden lub wiecej
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    //zastosowanie wyrazenia reg do wiadomosci mail
    return regex.getText(mailMessage.text);
  }

  //server pocztowy bedzie sie zatrzymywal nawet jak nie uda sie test
 // @AfterMethod (alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
