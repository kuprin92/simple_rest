package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name = "GROUP_ENTITY")
@Entity
public class GroupEntity {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue
    private UUID id;
    @OneToMany(mappedBy = "group", orphanRemoval = true)
    @JsonIgnore
    private Set<UserEntity> userEntities;
    @Column(name = "TITLE", nullable = false, unique = true)
    private String title;

    public GroupEntity() {
    }

    public GroupEntity(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;

        return id != null && id.equals(that.id);
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}