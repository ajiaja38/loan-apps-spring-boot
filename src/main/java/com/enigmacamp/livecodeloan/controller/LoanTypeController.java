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

import com.enigmacamp.livecodeloan.model.dto.req.CreateLoanTypeDto;
import com.enigmacamp.livecodeloan.model.dto.req.UpdateLoadTypeDto;
import com.enigmacamp.livecodeloan.model.entity.LoanType;
import com.enigmacamp.livecodeloan.service.LoanTypeService;
import com.enigmacamp.livecodeloan.utils.constant.ApiPathConstant;
import com.enigmacamp.livecodeloan.utils.res.Response;
import com.enigmacamp.livecodeloan.utils.res.ResponseMessage;

@RestController
@RequestMapping(
  ApiPathConstant.API +
  ApiPathConstant.VERSION +
  ApiPathConstant.LOAN_TYPES
)
public class LoanTypeController {
  
  @Autowired
  private LoanTypeService loanTypeService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<LoanType>> createLoanTypeHandler(
    @RequestBody CreateLoanTypeDto createLoanTypeDto
  ) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<LoanType>(
        "Berhasil Create Loantype",
        this.loanTypeService.createLoanType(createLoanTypeDto)
      )
    );
  }

  @GetMapping
  public ResponseEntity<Response<List<LoanType>>> getAllLoanTypeHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<List<LoanType>>(
        "Berhasil get All loan type",
        this.loanTypeService.getAllLoanType()
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Response<LoanType>> getLoanTypeById(@PathVariable String id) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new Response<LoanType>(
        "Berhasil Get loantype by id",
        this.loanTypeService.getLoanTypeById(id)
      )
    );
  }

  @PutMapping
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<Response<LoanType>> updateLoanTypeHandler(@RequestBody UpdateLoadTypeDto updateLoadTypeDto) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new Response<LoanType>(
        "Berhasil update loan type",
        this.loanTypeService.updateLoanType(updateLoadTypeDto)
      )
    );
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAnyRole('ROLE_STAFF', 'ROLE_ADMIN')")
  public ResponseEntity<ResponseMessage> deleteLoanTyoeHandler(@PathVariable String id) {
    this.loanTypeService.deleteLoanTypeById(id);
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new ResponseMessage(
        HttpStatus.OK.value(),
        "Berhasil Delete Loan Type"
      )
    );
  }
  
}
