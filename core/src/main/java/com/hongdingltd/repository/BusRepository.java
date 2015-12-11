package com.hongdingltd.repository;

import com.hongdingltd.domain.Bus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcchen on 15-12-3.
 */
@Repository
public interface BusRepository extends CrudRepository<Bus, Long> {

    Bus findByPlateNumber(String plateNumber);
}
