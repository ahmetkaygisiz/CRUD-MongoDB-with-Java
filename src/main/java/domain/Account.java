package domain;

import org.bson.types.ObjectId;

import java.util.List;

public class Account {
    /*
    - account_number
	- balance
	- List<Transactions>
//	- List<Card>
     */
    private ObjectId _id;
    private ObjectId userId;
    private String account_id;
    private double balance;
    private List<Transaction> transaction;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
