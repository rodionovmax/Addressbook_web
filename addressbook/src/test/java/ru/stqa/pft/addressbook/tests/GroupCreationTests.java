package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation(){
        app.goTo().groupPage();
//        Set<GroupData> before = app.group().all();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");

        app.group().create(group);

//        Set<GroupData> after = app.group().all();
        Groups after = app.group().all();
//        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1));

//        int max = 0;
//        for (GroupData g : after) {
//            if (g.getId() > max){
//                max = g.getId();
//            }
//        }
        // First method
//        Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//        int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

        // Second method
//        group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
//        before.add(group);
        // Old style of assert
//        Assert.assertEquals(before, after);
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void testGroupCreationEdited(){
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size() + 1));
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation(){
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size()));
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before));
    }


}
