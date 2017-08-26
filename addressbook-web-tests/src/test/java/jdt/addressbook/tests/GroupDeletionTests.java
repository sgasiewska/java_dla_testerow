package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }
    @Test
    public void testGroupDeletion() {

        List<GroupData> before= app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after= app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size()-1);

       // for(int i=0; i<after.size();i++){
          //  Assert.assertEquals(before.get(i),after.get(i));}

        Assert.assertEquals(before,after);
    }


}

