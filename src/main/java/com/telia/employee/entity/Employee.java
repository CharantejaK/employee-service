package com.telia.employee.entity;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull
  @Column(name = "EMPLOYEE_ID", unique = true)
  private Long employeeId;

  @NotNull
  @Column(name = "FIRST_NAME")
  private String firstName;

  @NotNull
  @Column(name = "LAST_NAME")
  private String lastName;

}
