package com.enigmacamp.livecodeloan.model.entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "mst_customer")
@SQLDelete(sql = "UPDATE mst_customer SET status = false WHERE customer_id = ?")
public class Customer {
  
  @Id
  @Column(name = "customer_id")
  private String id;

  private String firstName;
  
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
  private Date dateOfBirth;

  private String phone;

  @JsonIgnore
  private boolean status;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  public void prefixId() {
    this.id = "customer-" + UUID.randomUUID();
  }
}
