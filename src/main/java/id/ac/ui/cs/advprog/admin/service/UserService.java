package id.ac.ui.cs.advprog.admin.service;

import java.util.List;

import id.ac.ui.cs.advprog.admin.model.UserEntity;

public interface UserService {
    public List<UserEntity> findAll();
}
