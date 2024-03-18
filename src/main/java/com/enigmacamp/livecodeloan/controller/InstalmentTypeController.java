package com.enigmacamp.livecodeloan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enigmacamp.livecodeloan.model.dto.req.CreateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateInstalmentTypeDto;
import com.enigmacamp.livecodeloan.model.entity.InstalmentType;
import com.enigmacamp.livecodeloan.service.InstalmentTypeService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;
import com.enigmacamp.livecodeloan.utils.res.ResponseMessage;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.INSTALMENT_TYPES
)
public class InstalmentTypeController {
  
  @Autowired
  private InstalmentTypeService instalmentTypeService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<InstalmentType>> createInstalmentTypeHandler(
    @RequestBody CreateInstalmentTypeDto createInstalmentTypeDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<InstalmentType>(
        "Berhasil Create Instalment Type",
        this.instalmentTypeService.createInstalmentType(createInstalmentTypeDto)
      )
    );
  }

  @GetMapping
  public ResponseEntity<Response<List<InstalmentType>>> getAllInstalmentTypeHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<List<InstalmentType>>(
        "Berhasil get all instalment type",
        this.instalmentTypeService.getAllInstalmentType()
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Response<InstalmentType>> getInstalmentTypeByIdHandler(
    @PathVariable String id
  ) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<InstalmentType>(
        "Berhasil get instalment type",
        this.instalmentTypeService.getInstalmentTypeById(id)
      )
    );
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<InstalmentType>> updateInstalmentType(
    @RequestBody UpdateInstalmentTypeDto updateInstalmentTypeDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<InstalmentType>(
        "Berhasil Update Instalment type",
        this.instalmentTypeService.updateInstalmentType(updateInstalmentTypeDto)
      )
    );
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<ResponseMessage> deleteInstalmentTypeHandler(@PathVariable String id) {
    this.instalmentTypeService.deleteInstalmentType(id);
    
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new ResponseMessage(
        HttpStatus.OK.value(),
        "Berhasil delete instalment type"
      )
    );
  }
  
}
