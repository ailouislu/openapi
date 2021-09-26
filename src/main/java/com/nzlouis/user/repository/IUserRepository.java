package com.nzlouis.user.repository;

import com.nzlouis.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 *  @author Louis
 *  Interface IUserRepository
 */
public interface IUserRepository extends JpaRepository<User, String> {

}


