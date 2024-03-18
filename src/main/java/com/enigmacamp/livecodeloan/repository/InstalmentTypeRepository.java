package com.enigmacamp.livecodeloan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.livecodeloan.model.entity.InstalmentType;
import com.enigmacamp.livecodeloan.utils.constant.EInstalmentType;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
  Optional<InstalmentType> findByInstalmentType(EInstalmentType eInstalmentType);
}
