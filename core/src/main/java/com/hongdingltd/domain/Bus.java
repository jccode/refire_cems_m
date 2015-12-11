package com.hongdingltd.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by jcchen on 15-12-3.
 */
@Entity
public class Bus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String plateNumber;

    @ManyToMany(mappedBy = "buses", fetch = FetchType.EAGER)
    private Set<UserProfile> drivers;

    public Bus() {
    }

    public Bus(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Set<UserProfile> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<UserProfile> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
