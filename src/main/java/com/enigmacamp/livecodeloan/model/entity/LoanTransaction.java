package com.enigmacamp.livecodeloan.model.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.enigmacamp.livecodeloan.utils.constant.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "trx_loan")
public class LoanTransaction {
  
  @Id
  private String id;

  @Enumerated(EnumType.STRING)
  private ApprovalStatus approvalStatus;

  private Double nominal;
  
  private String approvedBy;
  
  private Date approvedAt;

  private Date createdAt;

  private Date UpdatedAt;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "loan_type_id")
  private LoanType loanType;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "instalment_type_id")
  private InstalmentType instalmentType;

  @OneToMany(mappedBy = "loanTransaction")
  @JsonIgnoreProperties("loanTransaction")
  private List<LoanTransactionDetail> loanTransactionDetails;

  @PrePersist
  public void prefixId() {
    this.id = "trx-loan-" + UUID.randomUUID();
  }
}
