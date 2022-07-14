package com.demo.Message;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int messageId;

    @Column(name = "group_id")
    private int groupID;
    @Column(name = "message_body")
    private String messageBody;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "sent_by")
    private String sentBy;

    public Message() {
    }

    public Message(int messageId, int groupID, String messageBody, Date timestamp, String sentBy) {
        this.messageId = messageId;
        this.groupID = groupID;
        this.messageBody = messageBody;
        this.timestamp = timestamp;
        this.sentBy = sentBy;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getGroupID() {
        return this.groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSentBy() {
        return this.sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Message messageId(int messageId) {
        setMessageId(messageId);
        return this;
    }

    public Message groupID(int groupID) {
        setGroupID(groupID);
        return this;
    }

    public Message messageBody(String messageBody) {
        setMessageBody(messageBody);
        return this;
    }

    public Message timestamp(Date timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    public Message sentBy(String sentBy) {
        setSentBy(sentBy);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return messageId == message.messageId && groupID == message.groupID && Objects.equals(messageBody, message.messageBody) && Objects.equals(timestamp, message.timestamp) && Objects.equals(sentBy, message.sentBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, groupID, messageBody, timestamp, sentBy);
    }


    @Override
    public String toString() {
        return "{" +
            " messageId='" + getMessageId() + "'" +
            ", groupID='" + getGroupID() + "'" +
            ", messageBody='" + getMessageBody() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", sentBy='" + getSentBy() + "'" +
            "}";
    }


}