package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().goToHomePage();
        ContactData contact = new contact.all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
