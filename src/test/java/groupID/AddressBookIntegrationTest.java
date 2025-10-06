package groupID;

import groupID.repositories.AddressBookRepository;
import groupID.repositories.BuddyInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @Test
    public void testCreateAddressBookAndAddBuddy() {
        ResponseEntity<AddressBook> createResponse = restTemplate.postForEntity(
                "/api/addressbooks", null, AddressBook.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        AddressBook createdBook = createResponse.getBody();
        assertNotNull(createdBook);
        Long bookId = createdBook.getId();
        assertNotNull(bookId);

        BuddyInfo buddy = new BuddyInfo("Umniyah", "K2O9P1", "12345");
        ResponseEntity<AddressBook> addBuddyResponse = restTemplate.postForEntity(
                "/api/addressbooks/" + bookId + "/buddies", buddy, AddressBook.class);
        assertEquals(HttpStatus.OK, addBuddyResponse.getStatusCode());

        AddressBook updatedBook = addBuddyResponse.getBody();
        assertNotNull(updatedBook);
        assertEquals(1, updatedBook.getBuddies().size());
        assertEquals("Umniyah", updatedBook.getBuddies().get(0).getName());
        assertEquals("K2O9P1", updatedBook.getBuddies().get(0).getAddress());
    }
}
