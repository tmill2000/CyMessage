package com.demo.UserAssociation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAssociationRepository extends JpaRepository<UserAssociation, Integer> {

    UserAssociation findById(int id);
    // UserAssociation findByName(String userName);
    void deleteById(int id);
}
