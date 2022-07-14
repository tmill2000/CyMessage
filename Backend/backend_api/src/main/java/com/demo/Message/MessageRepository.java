package com.demo.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findById(int id);
    Message findBySentBy(String sentBy);
    List<Message> findAllByGroupID(int groupID);
    void deleteById(int id);

}
