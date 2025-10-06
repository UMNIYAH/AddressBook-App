package groupID.controllers;

import groupID.AddressBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import groupID.repositories.AddressBookRepository;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepo;

    public AddressBookViewController(AddressBookRepository addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping("/addressbooks/{id}/buddies")
    public String viewBuddies(@PathVariable Long id, Model model) {
        AddressBook book = addressBookRepo.findById(id).orElse(null);
        if (book != null) {
            model.addAttribute("buddies", book.getBuddies());
        }
        return "buddies"; // Name of the Thymeleaf template (buddies.html)
    }
}
