package com.xss.FilterSanitize.entity;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column
  String name;
  @Column
  String description;
  @Column
  BigDecimal value;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_brand")
  Brand brand;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_deposity")
  Deposity deposity;
}
