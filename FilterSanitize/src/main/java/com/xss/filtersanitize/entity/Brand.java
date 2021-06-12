package com.xss.filtersanitize.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column
  String name;
}
