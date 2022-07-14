package com.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CyUserRepository extends JpaRepository<CyUser, Integer> {

    CyUser findById(int id);
    CyUser findByUserName(String userName);
    void deleteById(int id);
}
