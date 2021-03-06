package com.hongdingltd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jcchen on 15-11-26.
 */
@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long uid;

    private String authority;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", uid=" + uid +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }

//    @Override
//    public int hashCode() {
//        return getId() != null ? getId().hashCode() : 0;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if(this == o) return true;
//        if(o == null || getClass() != o.getClass()) return false;
//        Authority that = (Authority) o;
//        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
//            return false;
//        }
//        return true;
//    }

    public static enum Role {
        USER, DRIVER, ADMIN
    }
}
