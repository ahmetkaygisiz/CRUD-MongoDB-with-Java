package domain;

import org.bson.types.ObjectId;

import java.util.Date;

public class Transaction {
    private String  type;
    private String  name;
    private String  desc;
    private ObjectId from;
    private ObjectId to;
    private Date date = new Date();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ObjectId getFrom() {
        return from;
    }

    public void setFrom(ObjectId from) {
        this.from = from;
    }

    public ObjectId getTo() {
        return to;
    }

    public void setTo(ObjectId to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
