package com.xss.FilterSanitize.entity;

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
@Table(name = "deposity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Deposity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column
  String name;
}
