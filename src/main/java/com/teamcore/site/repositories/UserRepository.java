package com.teamcore.site.repositories;

import com.teamcore.site.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
