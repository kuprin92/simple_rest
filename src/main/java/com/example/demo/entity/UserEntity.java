package com.example.demo.entity;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "USERS")
@Entity(name = "User")
public class UserEntity {
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue
    private UUID id;

    @JoinColumn(name = "GROUP_ID")
    @ManyToOne
    private GroupEntity group;

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity(" +
                "name = " + name + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}