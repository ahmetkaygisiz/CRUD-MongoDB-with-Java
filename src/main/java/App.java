import database.AccountRepository;
import database.UserRepository;
import domain.Account;
import domain.Role;
import domain.Transaction;
import domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static UserRepository userRepository;
    private static AccountRepository accountRepository;

    public static void main(String[] args){
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();

        List<Account> accountList = accountRepository.readAll();
        System.out.println("<< Account List >>");

        for(Account a: accountList)
            System.out.println(a.toString());

        User findOneByEmail = userRepository.findOneByEmail("ahmetkaygisiz17@gmail.com");

        Account a1 = accountRepository.read(new Document("account_number","123123123123123")).get(0);
        Account a2 = accountRepository.read(new Document("account_number","9876543123456234")).get(0);

        List<ObjectId> idList = new ArrayList<>();
        idList.add(a1.getId());
        idList.add(a2.getId());

        findOneByEmail.setAccountList(idList);
        userRepository.update(findOneByEmail);

        System.out.println("<< User List >>");

        for(User u : userRepository.readAll())
            System.out.println(u.toString());
        System.out.println("");

    }

    private static void createAccounts(){
        if( accountRepository == null)
            accountRepository = new AccountRepository();

        // Create Account
        Account account1 = new Account();
        account1.setUserId(new ObjectId("5ef1ef4bbb780f29b3e0a321"));
        account1.setAccount_number("123123123123123");
        account1.setBalance(123.33);

        Account account2 = new Account();
        account2.setUserId(new ObjectId("5ef1ef4bbb780f29b3e0a321"));
        account2.setAccount_number("9876543123456234");
        account2.setBalance(443.75);

        accountRepository.create(account1);
        accountRepository.create(account2);

    }
    private static void userCRUD(){
        // Get user list
        List<User> userList = userRepository.readAll();

        System.out.println("<< User List >>");
        for(User u : userList)
            System.out.println(u.toString());
        System.out.println("");

        // Read Users with Document Filter
        List<User> getUserWithFilter = userRepository.read(new Document("firstName","Ahmet"));
        for(User user : getUserWithFilter)
            System.out.println("<< Filtered User >> " + user.toString());
        System.out.println("");

        // Find User with Email
        User findOneByEmail = userRepository.findOneByEmail("me@ahmetkaygisiz.space");
        System.out.println("<< Find with Email >> " + findOneByEmail.toString() +"\n");

        // Update User
        findOneByEmail.setActive(true);

        User updatedUser = userRepository.update(findOneByEmail);
        System.out.println("<< Updated User >>" + updatedUser.toString() + "\n");

        // Record Count
        System.out.println("<< users doc record count>> " + userRepository.count() + "\n");

        // Delete By Mail
        userRepository.deleteByEmail("me@ahmetkaygisiz.space");

        // Record Count
        System.out.println("<< users doc record count>> " + userRepository.count() + "\n");
    }
    private static void createUsers(){
        if(userRepository == null)
            userRepository = new UserRepository();

        // Roles
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleUser.setName("USER");

        // Create Users
        User user1 = new User();
        user1.setFirstName("Ahmet");
        user1.setLastName("Kaygisiz");
        user1.setPhoneNumber("12312312312");
        user1.setEmail("ahmetkaygisiz17@gmail.com");
        user1.setActive(true);
        user1.setRole(roleAdmin);

        User user2 = new User();
        user2.setFirstName("Test");
        user2.setLastName("User");
        user2.setPhoneNumber("98798798798");
        user2.setEmail("me@ahmetkaygisiz.space");
        user2.setActive(false);
        user2.setRole(roleUser);

        // Create Users
        userRepository.create(user1);
        userRepository.create(user2);

    }
    private static void accountCRUD(){

    }
}
