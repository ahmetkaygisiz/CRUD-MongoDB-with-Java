package database;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import domain.Account;
import domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AccountRepository extends  Repository {
    private static final String COLLECTION = "accounts";

    public MongoCollection<Account> getCollection() {
        return getDatabase().getCollection(AccountRepository.COLLECTION,Account.class);
    }

    public void create(Account account){
        getCollection().insertOne(account);
    }

    public List<Account> readAll(){
        return getCollection().find().into(new ArrayList<Account>());
    }

    public List<Account> read(Document filter){
        List<Account> result = new ArrayList<>();

        FindIterable<Account> iterable = getCollection().find(filter);
        iterable.forEach(new Block<Account>() {
            @Override
            public void apply(Account account) {
                result.add(account);
            }
        });

        return result;
    }

    public Account findOneById(String id) {
        return getCollection().find(eq("_id", new ObjectId(id))).first();
    }

    public long delete(String id) {
        return getCollection().deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    public Account update(Account account) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        return getCollection().findOneAndReplace(eq("_id", account.getId()), account, options);
    }

    public long count() {
        return getCollection().countDocuments();
    }
}
