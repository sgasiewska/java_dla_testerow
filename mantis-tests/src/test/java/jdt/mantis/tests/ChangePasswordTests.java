package jdt.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import jdt.mantis.model.MailMessage;
import jdt.mantis.model.Users;
import jdt.mantis.model.UsersData;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;
import javax.mail.MessagingException;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() throws IOException, MessagingException {
    app.mail().start();
    if (app.hBConnectionHelper().users().size() == 0) {
      long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost.localdomain", now);
      String user = String.format("user%s", now);
      String password = "password";
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      assertTrue(app.newSession().login(user, password));
    }
  }
  @Test
  public void testChangePassword() throws IOException, MessagingException {

    Users users = app.hBConnectionHelper().users();
    UsersData userTochange = users.iterator().next();
    String userToClick = userTochange.getUsername();
    String newPassword = "NewPassword";
    String email = String.format("%s@localhost.localdomain", userToClick);
    app.changePassword().start("administrator", "root");
    app.changePassword().goToUsersManagement();
    app.changePassword().chooseUser(userToClick);
    app.changePassword().initChangePass();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLinkPassword(mailMessages);
    app.changePassword().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(userToClick, newPassword));

  }

  private String findConfirmationLinkPassword(List<MailMessage> mailMessages) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.text.contains("password change")).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}