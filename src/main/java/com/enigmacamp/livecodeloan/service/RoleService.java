package com.enigmacamp.livecodeloan.service;

import com.enigmacamp.livecodeloan.model.entity.Role;
import com.enigmacamp.livecodeloan.utils.constant.ERole;

public interface RoleService {
  Role getOrSave(ERole role);
}
