package database;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository {

    private static final String COLLECTION = "users";

    public MongoCollection<User> getCollection() {
        return getDatabase().getCollection(UserRepository.COLLECTION,User.class);
    }

    public void create(User user){
        getCollection().insertOne(user);
    }

    public List<User> readAll(){
        return getCollection().find().into(new ArrayList<User>());
    }

    public List<User> read(Document filter){
        List<User> result = new ArrayList<>();

        FindIterable<User> iterable = getCollection().find(filter);
        iterable.forEach(new Block<User>() {
            @Override
            public void apply(User user) {
                result.add(user);
            }
        });

        return result;
    }

    public User findOneById(String id) {
        return getCollection().find(eq("_id", new ObjectId(id))).first();
    }

    public User findOneByEmail(String email) {
        return getCollection().find(eq("email", email)).first();
    }


    public long delete(String id) {
        return getCollection().deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    public long deleteByEmail(String email) {
        return getCollection().deleteOne(eq("email", email)).getDeletedCount();
    }

    public User update(User user) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        return getCollection().findOneAndReplace(eq("_id", user.getId()), user, options);
    }

    public long count() {
        return getCollection().countDocuments();
    }

}
