package com.nzlouis.user.repositories;

import com.nzlouis.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 *  @author Louis
 *  Interface IUserRepository
 */
public interface IUserRepository extends JpaRepository<User, String> {

}


