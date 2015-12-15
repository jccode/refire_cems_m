package com.hongdingltd.repository;

import com.google.common.collect.Sets;
import com.hongdingltd.TestRepositoryConfig;
import com.hongdingltd.domain.Authority;
import com.hongdingltd.domain.Bus;
import com.hongdingltd.domain.User;
import com.hongdingltd.domain.UserProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by jcchen on 15-12-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestRepositoryConfig.class)
//@WebAppConfiguration
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

//    @Autowired
//    private BusRepository busRepository;

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
    }

    private void hr() {
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    public void saveUser() {
        long count = userRepository.count();
        User user = new User();
        user.setUsername("kitty");
        userRepository.save(user);
        assertEquals(userRepository.count(), count+1);

        System.out.println(user);
//        user.setEnabled(true);
//        userRepository.save(user);

        User dbuser = userRepository.findByUsername("kitty");
//        assertTrue(dbuser.getEnabled());

        dbuser.setEnabled(true);
        userRepository.save(dbuser);

        System.out.println(dbuser);
        System.out.println(user);
    }


    @Test
    public void saveUserWithAuthorities() {
        long authoritiesCount = authorityRepository.count();
        long userCount = userRepository.count();
        User user = new User();
        user.setUsername("tom");
        User dbUser = userRepository.save(user);

        System.out.println(authoritiesCount);
        System.out.println(dbUser.getId() + " ; " + user.getId());
        System.out.println(authorityRepository.findAll());

        Authority authority = new Authority();
        authority.setAuthority("ROLE_TEST");
        authority.setUid(dbUser.getId());
        Set<Authority> authorities = Sets.newHashSet(authority);

        user.setAuthorities(authorities);
        userRepository.save(user);
        assertEquals(authorityRepository.count(), authoritiesCount + 1);


        System.out.println(authorityRepository.findAll());

        Authority dbAuthority = authorityRepository.findByUid(dbUser.getId());
        System.out.println(dbAuthority.getUser());
        assertNotNull(dbAuthority);


        // delete; orphanDelte=true
        userRepository.delete(user);
//        authorityRepository.delete(dbAuthority);
        assertEquals(authorityRepository.count(), authoritiesCount);
        assertEquals(userRepository.count(), userCount);
    }


    @Test
    public void userProfile() {
        long count = userProfileRepository.count();

        String username = "akka";
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
//        userRepository.save(user);

        UserProfile userProfile = new UserProfile();
//        userProfile.setUid(123l);
        userProfile.setFullname("Hello Kitty");
        userProfile.setGender(UserProfile.Gender.MALE);
        userProfile.setAge(15);
        userProfile.setUser(user);

        hr();
        System.out.println(userProfile);
        userProfileRepository.save(userProfile);

        assertEquals(userProfileRepository.count(), count+1);
        hr();
        System.out.println(userProfile.getUser());

        UserProfile dbUserProfile = userProfileRepository.findByUserId(userProfile.getUser().getId());
        System.out.println(dbUserProfile);
        System.out.println(dbUserProfile.getUser());

        User dbUser = userRepository.findByUsername(username);
        System.out.println(dbUser);
        System.out.println(dbUser.getProfile());

        assertEquals(dbUserProfile.getUser().getId(), dbUser.getId());
    }

    /*
    @Test
    public void saveBus() {
        System.out.println("----------------- BUS ------------------");
        long bc = busRepository.count();
        long uc = userRepository.count();
        long upc = userProfileRepository.count();

        Bus bus1 = new Bus("X12345");
        Bus bus2 = new Bus("Y12345");
        Bus bus3 = new Bus("Z00000");

        User user1 = new User();
        user1.setUsername("X1");

        User user2 = new User();
        user2.setUsername("Y1");

        UserProfile up1 = new UserProfile();
        up1.setUser(user1);

        UserProfile up2 = new UserProfile();
        up2.setUser(user2);

        up1.setBuses(Sets.newHashSet(bus1, bus2));
        up2.setBuses(Sets.newHashSet(bus2, bus3));

        userProfileRepository.save(Sets.newHashSet(up1, up2));

        assertEquals(busRepository.count(), bc+3);
        assertEquals(userRepository.count(), uc+2);
        assertEquals(userProfileRepository.count(), upc+2);

        Bus dbBus2 = busRepository.findByPlateNumber("Y12345");
        System.out.println(dbBus2);
        System.out.println(dbBus2.getDrivers());
        assertEquals(dbBus2.getDrivers().size(), 2);

        busRepository.delete(dbBus2);

        UserProfile dbUp1 = userProfileRepository.findByUserUsername("X1");
        System.out.println(dbUp1.getUser());
//        System.out.println(dbUp1.getBuses());
//        assertEquals(dbUp1.getBuses().size(), 1);

        Bus dbBus3 = busRepository.findByPlateNumber("Z00000");
        System.out.println(dbBus3.getDrivers());
        assertEquals(dbBus3.getDrivers().size(), 1);

        Set<UserProfile> set1 = dbBus3.getDrivers();
        set1.add(dbUp1);
        dbBus3.setDrivers(set1);
        Bus dbBus3_r = busRepository.save(dbBus3);
        assertEquals(dbBus3_r.getDrivers().size(), 2);
    }
    */
}
