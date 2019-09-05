package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification(){
        app.goTo().goToHomePage();
        app.contact().initContactModification();
        app.contact().fillContactForm(new ContactData("test_name", "test_lastname", null), false);
        app.contact().submitContactModification();
        app.contact().returnToHomePage();
    }
}
