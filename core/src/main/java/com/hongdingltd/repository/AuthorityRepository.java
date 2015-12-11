package com.hongdingltd.repository;


import com.hongdingltd.domain.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcchen on 15-12-2.
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findByUsername(String username);
}
