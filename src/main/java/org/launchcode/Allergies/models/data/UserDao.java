package org.launchcode.Allergies.models.data;


import org.launchcode.Allergies.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}