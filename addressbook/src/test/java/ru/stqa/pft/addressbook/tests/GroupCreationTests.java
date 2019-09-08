package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test(enabled = true)
    public void testGroupCreation(){
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test3");

        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsUsingCsvFile() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/max/sandbox/Addressbook/addressbook/src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsUsingXmlFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/max/sandbox/Addressbook/addressbook/src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream(new DomDriver());
//        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupsUsingXmlFile")
    public void testGroupCreationEdited(GroupData group){
            app.goTo().groupPage();
            Groups before = app.group().all();
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() + 1));
            Groups after = app.group().all();
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
