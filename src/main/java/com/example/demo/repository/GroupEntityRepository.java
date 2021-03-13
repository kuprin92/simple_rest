package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, UUID> {
    Optional<GroupEntity> findByTitle(String title);
}