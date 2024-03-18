package com.enigmacamp.livecodeloan.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "t_loan_type")
public class LoanType {
  
  @Id
  @Column(name = "loan_type_id")
  private String id;

  private String type;

  private Double maxLoan;

  @PrePersist
  public void prefixId() {
    this.id = "loan-type-" + UUID.randomUUID();
  }
}
