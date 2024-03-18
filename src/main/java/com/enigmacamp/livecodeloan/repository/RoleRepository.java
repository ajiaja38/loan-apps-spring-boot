package com.enigmacamp.livecodeloan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigmacamp.livecodeloan.model.entity.Role;
import com.enigmacamp.livecodeloan.utils.constant.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Optional<Role> findByRole(ERole eRole);
}
