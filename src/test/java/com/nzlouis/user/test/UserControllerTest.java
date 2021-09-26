package com.nzlouis.user.test;

import com.nzlouis.SpringdocOpenapiApplication;
import com.nzlouis.user.repository.IUserRepository;
import com.nzlouis.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;


@SpringBootTest(classes = SpringdocOpenapiApplication.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    @Rollback(false)
    @Order(1)
    public void testFindUsers() {
        List<User> list =  userRepository.findAll();

        Assertions.assertEquals(4, list.size());
    }

    @Test
    @Rollback(false)
    @Order(2)
    public void testCreateUser() {
        String email = "50@gmail.com";
        userRepository.save(new User
                (email, "ACCOUNTING", "Tom", "Smith"));
        User savedUser = userRepository.findById(email).orElse(new User());

        Assertions.assertEquals(savedUser.getEmail(), email);
    }

    @Test
    @Rollback(false)
    @Order(3)
    public void findUserByEmail() {
        List<User> firstFindAllList =  userRepository.findAll();
        int firstFindAllListNums = firstFindAllList.size();

        if(firstFindAllListNums == 0)  return;
        String email = firstFindAllList.get(0).getEmail();

        User user = userRepository.findById(email).orElse(new User());

        Assertions.assertEquals(email, user.getEmail());
    }


    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateUser() {
        List<User> firstFindAllList =  userRepository.findAll();
        int firstFindAllListNums = firstFindAllList.size();

        if(firstFindAllListNums == 0)  return;
        String email = firstFindAllList.get(0).getEmail();

        String changedFirstName = "Lucy";
        User user = userRepository.findById(email).orElse(new User());

        user.setFirstName(changedFirstName);
        userRepository.save(user);

        User changedUser = userRepository.findById(email).orElse(new User());

        Assertions.assertEquals(changedUser.getFirstName(), changedFirstName);
    }

    @Test
    @Rollback(false)
    @Order(5)
    public void testDeleteUser() {
        List<User> firstFindAllList =  userRepository.findAll();
        int firstFindAllListNums = firstFindAllList.size();

        if(firstFindAllListNums == 0)  return;
        String email = firstFindAllList.get(0).getEmail();
        userRepository.deleteById(email);

        List<User> secondFindAllList =  userRepository.findAll();

        Assertions.assertEquals(firstFindAllListNums - 1, secondFindAllList.size());
    }
}