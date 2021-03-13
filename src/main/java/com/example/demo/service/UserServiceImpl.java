package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.GroupEntityRepository;
import com.example.demo.repository.UserEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserEntityRepository repository;
    private final GroupEntityRepository groupEntityRepository;

    public UserServiceImpl(UserEntityRepository repository, GroupEntityRepository groupEntityRepository) {
        this.repository = repository;
        this.groupEntityRepository = groupEntityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> findUsers(PageRequest request) {
        return repository.findAll(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UserEntity> getUsersByGroupName(String groupName) {
        GroupEntity byTitle = groupEntityRepository.findByTitle(groupName)
                .orElseThrow(() -> new IllegalArgumentException(groupName + " not found"));
        return byTitle.getUserEntities();
    }

    @Override
    @Transactional
    public UserEntity createUser(String userName, String groupName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userName);
        GroupEntity groupEntity = groupEntityRepository.findByTitle(groupName)
                .or(() -> Optional.of(groupEntityRepository.save(new GroupEntity(groupName))))
                .get();
        userEntity.setGroup(groupEntity);
        return repository.save(userEntity);
    }
}
