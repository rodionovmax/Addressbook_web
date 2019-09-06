package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test(enabled = false)
    public void testContactCreation(){
        app.goTo().goToHomePage();
        app.contact().initContactCreation();
//        app.contact().fillContactForm(new ContactData("test_name", "test_lastname", "test1"), true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
    }
}
