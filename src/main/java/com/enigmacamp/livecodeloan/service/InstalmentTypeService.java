package com.enigmacamp.livecodeloan.service;

import java.util.List;

import com.enigmacamp.livecodeloan.model.dto.req.CreateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.entity.InstalmentType;

public interface InstalmentTypeService {
  InstalmentType createInstalmentType(CreateInstalmentTypeDto createInstalmentTypeDto);
  List<InstalmentType> getAllInstalmentType();
  InstalmentType getInstalmentTypeById(String id);
  InstalmentType updateInstalmentType(UpdateInstalmentTypeDto updateInstalmentTypeDto);
  void deleteInstalmentType(String id);
}
