package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

public class ContactCreationTests extends TestBase{

    @Test(enabled = false)
    public void testContactCreation(){
        app.goTo().goToHomePage();
        app.contact().initContactCreation();
        File photo = new File("src/test/resources/stru.png");
        app.contact().fillContactForm(new ContactData()
                .withFirstname("test_name").withLastname("test_lastname").withPhoto(photo), true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }

    @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
