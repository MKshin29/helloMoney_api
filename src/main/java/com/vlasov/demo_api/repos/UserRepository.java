package com.vlasov.demo_api.repos;

import com.vlasov.demo_api.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findById(int id);
    List<User> findByUsername(String username);
    User getByUsername(String username);

}
