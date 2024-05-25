package id.ac.ui.cs.advprog.admin.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.admin.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    
}
