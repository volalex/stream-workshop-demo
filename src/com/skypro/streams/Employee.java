package com.skypro.streams;

import java.util.List;

public class Employee {
  private static int counter = 1;
  private final int id;
  private final String firstName;
  private final String lastName;
  private final float salary;
  private final List<String> knowledgeAreas;

  public Employee(String firstName, String lastName, float salary, List<String> knowledgeAreas) {
    this.id = counter++;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
    this.knowledgeAreas = knowledgeAreas;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public float getSalary() {
    return salary;
  }

  public List<String> getKnowledgeAreas() {
    return knowledgeAreas;
  }

  public int getId() {
    return id;
  }
}
