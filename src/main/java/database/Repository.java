package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Repository {
    private static final String SERVER_URL = "localhost:27017";
    private static final String USER = "akua";
    private static final String PASSWORD = "welcome1";
    private static final String DATABASE = "tutorial_db";
    private static final String COLLECTION = "users";

    private MongoClient client;
    private CodecRegistry pojoCodecRegistry;
    private MongoClientSettings settings;

    // private MongoCredential credential = MongoCredential.createCredential(USER,DATABASE,PASSWORD.toCharArray());

    public Repository(){
        pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
    }

    public MongoClient getClient(){
        if ( client == null )
            client = new MongoClient(new ServerAddress(SERVER_URL));
        return client;
    }

    public MongoDatabase getDatabase() {
        return getClient().getDatabase(Repository.DATABASE).withCodecRegistry(pojoCodecRegistry);
    }

}
