package com.demo.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findById(int id);
    Group findByGroupName(String groupName);
    void deleteById(int id);
}
