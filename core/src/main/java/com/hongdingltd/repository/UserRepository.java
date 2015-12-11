package com.hongdingltd.repository;

import com.hongdingltd.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by jcchen on 15-11-25.
 */
@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("update User u set u.lastAccessed = current_date where u.username = ?1")
    int updateLastAccessed(String username);

    User findByUsername(String username);
}
