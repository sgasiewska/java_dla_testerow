package jdt.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app){
    //powstaje nowa sesja
    this.app=app;
    //powstaje nowy klient (sesja) do pracy po protokole http, objekt wysyłający zapotrzebowania na serwer
    httpclient= HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }
  public boolean login(String username, String password) throws IOException{
    //adres na jajki wysylane jest zapytanie
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl")+"/login.php");
    List<NameValuePair> params = new ArrayList<>();
    //inicjalizacja zbioru parametrow
    params.add(new BasicNameValuePair("usernane", username));
    params.add(new BasicNameValuePair("passwors", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    //parametry zapakowują się zgodnie z określonymi zasadami i lokują się we wczesniej strworzone zapytanie
    post.setEntity(new UrlEncodedFormEntity(params));
    //wysyłka zapytania httpclient.execute(post) a wynikiem jest odp od serwera
    CloseableHttpResponse response= httpclient.execute(post);
    //Analizujemy odp od serwera
    String body =geTextFrom(response);
    //sprawdzanie czy uzytkownik sie zalogowal, czy kod strony zawiera taka linijke
    return body.contains(String.format("<span class=\"italic\">%s</span>",username));
  }
//metoda do otrzymywania tekstu z odp serwera
  private  String geTextFrom(CloseableHttpResponse response) throws  IOException{
    try{
      return EntityUtils.toString(response.getEntity());
    } finally{
      response.close();
    }
  }
  //jaki uzytkownik jest teraz zalogowany
  public boolean isLoggedInAs(String username) throws IOException{
    //wchodzimy na strone glowna
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+ "/index.php");
   // wykonujemy zapytanie i otzymujemy odpowiedz
    CloseableHttpResponse response=httpclient.execute(get);
    String body= geTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));

  }
}
