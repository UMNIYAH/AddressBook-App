package groupID;

import groupID.repositories.AddressBookRepository;
import groupID.repositories.BuddyInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean
    CommandLineRunner runner(AddressBookRepository addressRepo, BuddyInfoRepository buddyRepo) {
        return args -> {
            BuddyInfo buddy1 = new BuddyInfo("Umniyah", "12345", "6131231234");
            BuddyInfo buddy2 = new BuddyInfo("Danial", "67890", "6131231234");

            AddressBook book = new AddressBook();
            book.addBuddy(buddy1);
            book.addBuddy(buddy2);

            addressRepo.save(book);
            System.out.println("Saved AddressBook with ID: " + book.getId());
        };
    }
}