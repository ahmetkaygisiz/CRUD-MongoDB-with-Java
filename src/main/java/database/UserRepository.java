package database;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.WriteModel;
import domain.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static final String SERVER_URL = "localhost:27017";
    private static final String USER = "akua";
    private static final String PASSWORD = "welcome1";
    private static final String DATABASE = "tutorial_db";
    private static final String COLLECTION = "users";

    private MongoClient client;
    private CodecRegistry pojoCodecRegistry;
    private MongoClientSettings settings;

    public UserRepository(){
        pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

       settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
    }

    // private MongoCredential credential = MongoCredential.createCredential(USER,DATABASE,PASSWORD.toCharArray());

    private MongoClient getClient(){
        if ( client == null )
                client = new MongoClient(new ServerAddress(SERVER_URL));
        return client;
    }

    private MongoDatabase getDatabase() {
        return getClient().getDatabase(UserRepository.DATABASE).withCodecRegistry(pojoCodecRegistry);
    }

    private MongoCollection<User> getCollection() {
        return getDatabase().getCollection("users",User.class);
    }

    public void create(User user){
        getCollection().insertOne(user);
    }

    public List<User> readAll(){
        return getCollection().find().into(new ArrayList<User>());
    }

    public List<User> read(Document filter){
        List<User> result = new ArrayList<User>();

        FindIterable<User> iterable = getCollection().find(filter);
        iterable.forEach(new Block<User>() {
            @Override
            public void apply(User user) {
                result.add(user);
            }
        });

        return result;
    }

    public User findOne(String id) {
        return getCollection().find(eq("_id", new ObjectId(id))).first();
    }

    public long count() {
        return getCollection().countDocuments();
    }

    public long delete(String id) {
        return getCollection().deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }


    public User update(User user) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        return getCollection().findOneAndReplace(eq("_id", user.get_id()), user, options);
    }

    /*
    public long update(List<User> users) {
        List<WriteModel<User>> writes = users.stream()
                .map(u -> new ReplaceOneModel(eq("_id", u.get_id()), u))
                .collect(Collectors.toList());
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> getCollection().bulkWrite(clientSession, writes).getModifiedCount());
        }
    }
*/
}
