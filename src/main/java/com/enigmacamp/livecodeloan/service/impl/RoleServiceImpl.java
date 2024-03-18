package com.enigmacamp.livecodeloan.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigmacamp.livecodeloan.model.entity.Role;
import com.enigmacamp.livecodeloan.repository.RoleRepository;
import com.enigmacamp.livecodeloan.service.RoleService;
import com.enigmacamp.livecodeloan.utils.constant.ERole;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role getOrSave(ERole role) {
    Optional<Role> optionalRole = this.roleRepository.findByRole(role);

    if (optionalRole.isPresent()) {
      return optionalRole.get();
    }

    Role currentRole = Role.builder().role(role).build();

    return this.roleRepository.save(currentRole);
  }
  
}
