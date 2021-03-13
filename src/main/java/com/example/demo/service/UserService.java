package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

public interface UserService {
    Page<UserEntity> findUsers(PageRequest request);

    Set<UserEntity> getUsersByGroupName(String groupName);

    UserEntity createUser(String userName, String groupName);
}
