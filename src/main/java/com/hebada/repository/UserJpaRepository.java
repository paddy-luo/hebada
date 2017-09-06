package com.hebada.repository;

import com.hebada.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by paddy on 2017/9/6.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByNameAndPassword(String name, String password);
}
