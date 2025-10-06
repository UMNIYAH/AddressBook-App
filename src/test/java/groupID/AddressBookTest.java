package groupID;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    @Test
    void testAddressBookInitialization() {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Umniyah", "K2O9P1", "12345"));
        book.addBuddy(new BuddyInfo("Danial", "K2O7P1", "12346"));

        List<BuddyInfo> buddies = book.getBuddies();

        assertEquals(2, buddies.size(), "AddressBook should contain 2 buddies");
        assertEquals("Umniyah", buddies.get(0).getName());
        assertEquals("Danial", buddies.get(1).getName());
    }

    @Test
    void testAddAndRemoveBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Alice", "A1B2C3", "54321");

        book.addBuddy(buddy);
        assertEquals(1, book.getBuddies().size());
        assertTrue(book.getBuddies().contains(buddy));

        book.removeBuddy(buddy);
        assertEquals(0, book.getBuddies().size(), "Buddy should be removed from AddressBook");
    }

    @Test
    void testToString() {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Bob", "H0H0H0", "99999"));

        String str = book.toString();
        assertTrue(str.contains("Bob"), "toString should contain buddy's name");
        assertTrue(str.contains("99999"), "toString should contain buddy's phone number");
    }
}
