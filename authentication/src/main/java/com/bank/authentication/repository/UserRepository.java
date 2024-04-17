package com.bank.authentication.repository;

import com.bank.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableJpaRepositories(basePackages = "com.bank.*")
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users u WHERE u.username = ?1 AND u.password = ?2", nativeQuery = true)
    User authenticateUser(String username, String password);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users u SET accbalance = (u.accbalance - ?1) WHERE u.id = ?2", nativeQuery = true)
    void reduceBillAmount(Double billAmount, int id);
}
