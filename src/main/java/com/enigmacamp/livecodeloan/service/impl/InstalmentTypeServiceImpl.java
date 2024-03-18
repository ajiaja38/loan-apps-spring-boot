package com.enigmacamp.livecodeloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigmacamp.livecodeloan.model.dto.req.CreateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.entity.InstalmentType;
import com.enigmacamp.livecodeloan.repository.InstalmentTypeRepository;
import com.enigmacamp.livecodeloan.service.InstalmentTypeService;

@Service
public class InstalmentTypeServiceImpl implements InstalmentTypeService {

  @Autowired
  private InstalmentTypeRepository instalmentTypeRepository;
  
  @Override
  public InstalmentType createInstalmentType(CreateInstalmentTypeDto createInstalmentTypeDto) {
    InstalmentType instalmentType = InstalmentType.builder()
    .instalmentType(createInstalmentTypeDto.getInstalmentType())
    .build();

    return this.instalmentTypeRepository.save(instalmentType);
  }

  @Override
  public List<InstalmentType> getAllInstalmentType() {
    return this.instalmentTypeRepository.findAll();
  }

  @Override
  public InstalmentType getInstalmentTypeById(String id) {
    return this.instalmentTypeRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instalment Type Not Found")
    );
  }

  @Override
  public InstalmentType updateInstalmentType(UpdateInstalmentTypeDto updateInstalmentTypeDto) {
    InstalmentType instalmentType = this.getInstalmentTypeById(updateInstalmentTypeDto.getId());

    instalmentType.setInstalmentType(updateInstalmentTypeDto.getInstalmentType());
    this.instalmentTypeRepository.save(instalmentType);

    return instalmentType;
  }

  @Override
  public void deleteInstalmentType(String id) {
    this.getInstalmentTypeById(id);
    this.instalmentTypeRepository.deleteById(id);
  }
  
}
