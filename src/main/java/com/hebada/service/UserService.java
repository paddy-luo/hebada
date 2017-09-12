package com.hebada.service;

import com.hebada.domain.User;
import com.hebada.repository.UserJpaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * Created by paddy on 2017/9/6.
 */
@Service
public class UserService {

  @Inject
  private UserJpaRepository userJpaRepository;

  public boolean findByNameAndPassword(String name, String password) {
    User user = userJpaRepository.findByNameAndPassword(name, password);
    if (user != null && user.getId() > 0) return true;
    else return false;
  }

  @Transactional
  public boolean saveOrUpdate(User user) {
    User userDomain = userJpaRepository.save(user);
    if (userDomain != null && userDomain.getId() > 0) return true;
    else return false;
  }

  @Transactional
  public void delete(long id) {
    userJpaRepository.delete(id);
  }

}

