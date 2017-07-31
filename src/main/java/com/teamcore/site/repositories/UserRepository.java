package com.teamcore.site.repositories;

import com.teamcore.site.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
}
