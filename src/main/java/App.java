import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import database.UserRepository;
import domain.Account;
import domain.Role;
import domain.Transaction;
import domain.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){

        UserRepository userRepository = new UserRepository();

/*
        ObjectId oid = new ObjectId();

        Role role = new Role();
        role.setName("USER");

        User user = new User();
        user.set_id(oid);
        user.setActive(true);
        user.setEmail("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setRole(role);
        user.setPhoneNumber("5dd555555");

        userRepository.create(user);
 */

        List<User> userList = userRepository.readAll();
        for (User u : userList)
            System.out.println(u.toString());

        /*
        Transaction t = new Transaction();
        t.setName("Ahmet Kaygisiz");
        t.setType("transfer");
        t.setDesc("kira ödemesi");

        List<Transaction> lt = new ArrayList<Transaction>();
        lt.add(t);

        Account account = new Account();
        account.set_id(new ObjectId());
        account.setBalance(122.32);
        account.setTransaction(lt);
        account.setAccount_id("1234123412341234");

         */
    }
}
