package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    Groups before= app.group().all();

    GroupData group = new GroupData().withName("name");
    app.group().create(group);
    Groups after= app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

  }

}
