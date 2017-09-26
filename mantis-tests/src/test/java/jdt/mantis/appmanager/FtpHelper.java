package jdt.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
  private final ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    // inicjalizacja klienta który bedzie instalowac poloczenia, przenosic pliki itp
    ftp = new FTPClient();
  }
// ładowanie nowego pliku
  public void upload(File file, String target, String backup) throws IOException{
    //połączenie z serwerem
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    // usuwamy poprzednia kopie rezerwowa- na wszelki wypadek
    ftp.deleteFile(backup);
    //przemianowujemy usuniety plik i robimy kopię rezerwową
    ftp.rename (target,backup);
    //wlaczenie tzw trybu pasywnego transferu danych
    ftp.enterLocalPassiveMode();
    // z lokalnego pliku robimy plik InputStream przeznaczony do czytania danych binarnych,
    // dane sa czytane i zapisywane w zdalnym pliku który nazywa sie targert
    ftp.storeFile(target, new FileInputStream(file));
    //zerwanie polaczenia
    ftp.disconnect();

  }
  //przywracanie starego pliku
  public void restore(String backup, String target) throws IOException{
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();

  }
}
