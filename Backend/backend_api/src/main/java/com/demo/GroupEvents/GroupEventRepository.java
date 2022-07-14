package com.demo.GroupEvents;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupEventRepository extends JpaRepository<GroupEvent, Integer> {
    GroupEvent findById(int id);
    void deleteById(int id);
}
