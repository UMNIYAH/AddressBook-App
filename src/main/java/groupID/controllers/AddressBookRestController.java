package groupID.controllers;

import groupID.AddressBook;
import groupID.BuddyInfo;
import groupID.repositories.AddressBookRepository;
import groupID.repositories.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/addressbooks")
public class AddressBookRestController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping
    public AddressBook createAddressBook() {
        AddressBook book = new AddressBook();
        return addressBookRepository.save(book);
    }

    @PostMapping("/{id}/buddies")
    public AddressBook addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        Optional<AddressBook> optionalBook = addressBookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("not found");
        }

        AddressBook book = optionalBook.get();
        book.addBuddy(buddy); // CascadeType.ALL handles persist
        return addressBookRepository.save(book);
    }

    @DeleteMapping("/{id}/buddies/{buddyId}")
    public AddressBook removeBuddy(@PathVariable Long id, @PathVariable Long buddyId) {
        AddressBook book = addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AddressBook not found"));

        BuddyInfo buddy = buddyInfoRepository.findById(buddyId)
                .orElseThrow(() -> new RuntimeException("Buddy not found"));

        book.removeBuddy(buddy);
        return addressBookRepository.save(book);
    }

    @GetMapping("/{id}")
    public AddressBook getAddressBook(@PathVariable Long id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AddressBook not found"));
    }
}