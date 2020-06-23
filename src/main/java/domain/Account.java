package domain;

import org.bson.types.ObjectId;
import java.util.List;

public class Account {

    private ObjectId id;
    private ObjectId userId;
    private String account_number;
    private double balance;
    private List<Transaction> transaction;

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", account_number='" + account_number + '\'' +
                ", balance=" + balance +
                ", transaction=" + transaction +
                '}';
    }
}
