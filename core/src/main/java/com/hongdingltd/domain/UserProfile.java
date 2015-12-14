package com.hongdingltd.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by jcchen on 15-11-30.
 */
@Entity
@Table(name = "users_profile")
public class UserProfile implements Serializable {

    public enum Gender {
        MALE, FEMALE;
    }

    @Id
    private Long uid;

    private String fullname;

    @Enumerated
    private Gender gender;

    private Integer age;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "uid", insertable = true, updatable = true)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL) // fetch = FetchType.EAGER
    @JoinTable(name = "user_bind_bus",
            joinColumns = @JoinColumn(name = "uid", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(name = "bid", referencedColumnName = "bid")
    )
    private Set<Bus> buses;

    public UserProfile() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Bus> getBuses() {
        return buses;
    }

    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "uid=" + uid +
                ", fullname='" + fullname + '\'' +
                ", gender=" + gender +
                ", age=" + age +
//                ", user=" + user +
//                ", buses=" + buses +
                '}';
    }
}
