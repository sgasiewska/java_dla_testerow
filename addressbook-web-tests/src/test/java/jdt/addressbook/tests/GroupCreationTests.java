package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    int before= app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    int after= app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before+1);

  }

}
