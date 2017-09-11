package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

  }
  @Test
  public void testGroupModification (){

    Groups before= app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader( "test2").withFooter( "test3");
    app.goTo().groupPage();
    app.group().modifi(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after= app.db().groups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }


}
