package jdt.addressbook.tests;

import jdt.addressbook.appmanager.ApplicationManager;
import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestBase {
  Logger logger= LoggerFactory.getLogger(TestBase.class);
  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method m, Object [] p){
    logger.info("Start test"+m.getName() +"with parameters" + Arrays.asList(p));
  }
  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test"+m.getName());
  }
  public ApplicationManager getApp() {
    return app;
  }

  public void verifyGroupListInUI() {
    if(Boolean.getBoolean("verifyUI")){
      Groups dbGroups= app.db().groups();
      Groups uiGroups= app.group().all();
      assertThat(uiGroups,equalTo(dbGroups.stream()
              .map((g)->new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
}
