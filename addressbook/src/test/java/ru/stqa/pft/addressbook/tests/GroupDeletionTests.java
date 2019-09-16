package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void deleteGroup() {
//        Set<GroupData> before = app.group().all();
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
//        int index = before.size() - 1;
        app.group().delete(deletedGroup);
//        Set<GroupData> after = app.group().all();
        Groups after = app.group().all();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));
//        Assert.assertEquals(after, before);

    }

    @Test
    public void deleteGroupLesson5_8() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    @Test
    public void deleteGroupLesson7_4() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }



}
