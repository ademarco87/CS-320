package com.grandstrand.contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testContactCreationValidData() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        assertAll(
            "Valid Contact Creation",
            () -> assertEquals("12345", contact.getContactId(), "Contact ID should match"),
            () -> assertEquals("John", contact.getFirstName(), "First name should match"),
            () -> assertEquals("Doe", contact.getLastName(), "Last name should match"),
            () -> assertEquals("1234567890", contact.getPhone(), "Phone number should match"),
            () -> assertEquals("123 Elm Street", contact.getAddress(), "Address should match")
        );
    }

    @Test
    void testContactCreationNullContactId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Doe", "1234567890", "123 Elm Street")
        );
        assertAll(
            "Invalid Contact ID (Null)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Contact ID", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testContactCreationTooLongContactId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Elm Street")
        );
        assertAll(
            "Invalid Contact ID (Too Long)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Contact ID", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetFirstNameValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        contact.setFirstName("Jane");
        assertAll(
            "Valid First Name Update",
            () -> assertEquals("Jane", contact.getFirstName(), "First name should be updated"),
            () -> assertNotEquals("John", contact.getFirstName(), "Old name should not match")
        );
    }

    @Test
    void testSetFirstNameNull() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertAll(
            "Invalid First Name (Null)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid First Name", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetFirstNameTooLong() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("JohnathonDoe"));
        assertAll(
            "Invalid First Name (Too Long)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid First Name", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetFirstNameBoundaryLength() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        contact.setFirstName("JohnSmith"); // Exactly 10 characters
        assertAll(
            "Boundary Valid First Name",
            () -> assertEquals("JohnSmith", contact.getFirstName(), "First name should match"),
            () -> assertTrue(contact.getFirstName().length() <= 10, "First name length should be valid")
        );
    }

    @Test
    void testSetPhoneValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        contact.setPhone("0987654321");
        assertAll(
            "Valid Phone Update",
            () -> assertEquals("0987654321", contact.getPhone(), "Phone number should be updated"),
            () -> assertNotEquals("1234567890", contact.getPhone(), "Old phone number should not match")
        );
    }

    @Test
    void testSetPhoneNull() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertAll(
            "Invalid Phone (Null)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Phone Number", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetPhoneTooShort() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
        assertAll(
            "Invalid Phone (Too Short)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Phone Number", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetPhoneInvalidCharacters() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345abcde"));
        assertAll(
            "Invalid Phone (Non-Numeric)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Phone Number", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetAddressValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        contact.setAddress("456 Maple Avenue");
        assertAll(
            "Valid Address Update",
            () -> assertEquals("456 Maple Avenue", contact.getAddress(), "Address should be updated"),
            () -> assertNotEquals("123 Elm Street", contact.getAddress(), "Old address should not match")
        );
    }

    @Test
    void testSetAddressNull() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertAll(
            "Invalid Address (Null)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Address", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetAddressTooLong() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            contact.setAddress("This is an address that is far too long for validation")
        );
        assertAll(
            "Invalid Address (Too Long)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Address", exception.getMessage(), "Error message should match")
        );
    }
}
