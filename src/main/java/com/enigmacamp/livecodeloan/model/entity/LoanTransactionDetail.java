package com.enigmacamp.livecodeloan.model.entity;

import java.util.Date;
import java.util.UUID;

import com.enigmacamp.livecodeloan.utils.constant.LoanStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {
  
  @Id
  private String id;

  private Double nominal;

  private Date transactionDate;

  @Enumerated(EnumType.STRING)
  private LoanStatus loanStatus;

  private Date createdAt;

  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "trx_loan_id")
  @JsonIgnoreProperties("loanTransactionDetail")
  private LoanTransaction loanTransaction;

  @PrePersist
  public void prefixId() {
    this.id = "trx-dtl-loan" + UUID.randomUUID();
  }
  
}
