package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
                app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        int before= app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before-1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after= app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after,before-1);
    }


}

