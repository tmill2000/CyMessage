package com.demo.GroupEvents;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_event")
public class GroupEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "group_id")
    private int groupId;
    @Column(name = "event_body")
    private String eventBody;
    @Column(name = "timestamp")
    private Date timestamp;

    public GroupEvent() {
    }

    public GroupEvent(int groupId, String eventBody, Date timestamp) {
        this.groupId = groupId;
        this.eventBody = eventBody;
        this.timestamp = timestamp;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getEventBody() {
        return this.eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" + " groupId='" + getGroupId() + "'" + ", eventBody='" + getEventBody() + "'" + ", timestamp='"
                + getTimestamp() + "'" + "}";
    }

}
