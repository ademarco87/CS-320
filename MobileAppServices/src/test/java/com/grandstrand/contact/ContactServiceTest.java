package com.grandstrand.contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    @Test
    void testAddContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");

        service.addContact(contact);
        assertAll(
            "Add Contact",
            () -> assertTrue(service.getAllContacts().containsKey("12345"), "Contact should be added"),
            () -> assertEquals(contact, service.getAllContacts().get("12345"), "Contact should match")
        );
    }

    @Test
    void testAddContactDuplicateId() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Contact contact2 = new Contact("12345", "Jane", "Smith", "0987654321", "456 Maple Avenue");

        service.addContact(contact1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
        assertEquals("Contact ID already exists.", exception.getMessage());
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");

        service.addContact(contact);
        service.deleteContact("12345");

        assertFalse(service.getAllContacts().containsKey("12345"), "Contact should be deleted");
    }

    @Test
    void testDeleteContactInvalidId() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deleteContact("99999"));
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    @Test
    void testUpdateContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");

        service.addContact(contact);
        service.updateContact("12345", "Jane", "Smith", "0987654321", "456 Maple Avenue");

        assertAll(
            "Update Contact",
            () -> assertEquals("Jane", service.getAllContacts().get("12345").getFirstName(), "First name should be updated"),
            () -> assertEquals("Smith", service.getAllContacts().get("12345").getLastName(), "Last name should be updated"),
            () -> assertEquals("0987654321", service.getAllContacts().get("12345").getPhone(), "Phone should be updated"),
            () -> assertEquals("456 Maple Avenue", service.getAllContacts().get("12345").getAddress(), "Address should be updated")
        );
    }

    @Test
    void testUpdateContactInvalidId() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            service.updateContact("99999", "Jane", "Smith", "0987654321", "456 Maple Avenue")
        );
        assertEquals("Contact ID not found.", exception.getMessage());
    }
}
