package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    List<GroupData> before= app.group().list();

    //int before= app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test2", null, null);
    app.group().create(group);
    List<GroupData> after= app.group().list();
    //int after= app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(),before.size()+1);

/*
    int max =0;
    for (GroupData g:after){
      if (g.getId()>max){
        max=g.getId();
      }
    }
*/
   // int max1= after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId=(g1, g2)->Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
   // Assert.assertEquals(new HashSet<Object>( before), new HashSet<Object>(after));
    Assert.assertEquals(before,after);
  }

}
