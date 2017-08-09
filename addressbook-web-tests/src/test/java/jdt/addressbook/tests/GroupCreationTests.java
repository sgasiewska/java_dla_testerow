package jdt.addressbook.tests;

import jdt.addressbook.GroupData;
import jdt.addressbook.model.TestBase;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCration();
    app.getGroupHelper().returnToGroupPage();
  }

}
