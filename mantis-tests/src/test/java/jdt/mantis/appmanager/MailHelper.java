package jdt.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import jdt.mantis.model.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.http.MessageConstraintException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
  private ApplicationManager app;
  private final Wiser wiser;

  public MailHelper(ApplicationManager app){
    this.app=app;
    //powstaje serwer pocztowy
    wiser = new Wiser();
  }
  //oczekiwanie na mail
  //pierw.  param > ilosc listow ktore powinny przyjsc
  // drugi to czas oczekiwania
  public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException,IOException {
    //zapamietujemy bierzacy czas
    long start = System.currentTimeMillis();
    //spr czy nie skonczyl sie nam czas oczekiwania
    while (System.currentTimeMillis() < start + timeout) {
      //Czy przyszly wszystkie maile?
      if (wiser.getMessages().size() >= count) {
        //tak? przekształcamy rzeczywiste maile w modelowe
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }
      try {
        //nie? czekamy 1000 ms
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail:(");
  }
  //met wyciagajaca z maily tylko potrzebne nam info
  public static MailMessage toModelMail(WiserMessage m){
    try{
      MimeMessage mm =m.getMimeMessage();
      //bierzemy objekt rzeczywisty i listę odbiorców a zniej tylko pierwszego odbiorce(bo wiemy ze zawsze jest jeden odbiorca)
      return new MailMessage(mm.getAllRecipients()[0].toString(),(String) mm.getContent());
    } catch (MessagingException e){
      e.printStackTrace();
      return null;
    } catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }
  public void start(){
    wiser.start();
  }
  public void stop(){
    wiser.stop();
  }
  }

