package com.hongdingltd.repository;

import com.hongdingltd.domain.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcchen on 15-12-2.
 */
@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    UserProfile findByUserUsername(String username);
}
