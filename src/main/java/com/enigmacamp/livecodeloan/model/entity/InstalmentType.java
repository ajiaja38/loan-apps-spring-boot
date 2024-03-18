package com.enigmacamp.livecodeloan.model.entity;

import java.util.UUID;

import com.enigmacamp.livecodeloan.utils.constant.EInstalmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "t_instalment_type")
public class InstalmentType {
  
  @Id
  @Column(name = "instalment_type_id")
  private String id;

  @Enumerated(EnumType.STRING)
  private EInstalmentType instalmentType;

  @PrePersist
  public void prefixId() {
    this.id = "instalment-type-" + UUID.randomUUID();
  }

}
