package com.skypro.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  private static List<Employee> employees = new ArrayList<>();

  static {
    employees.add(new Employee("Ivan", "Ivanov", 5000, List.of("Sales", "Support")));
    employees.add(new Employee("Petr", "Petrov", 7000, List.of("Engineering", "DevOps", "CI/CD")));
    employees.add(
        new Employee("Nikolay", "Nikolayev", 10000, List.of("Java", "Engineering", "DevOps")));
    employees.add(new Employee("Artem", "Sidorov", 4000, List.of("Go", "Engineering", "Support")));
    employees.add(new Employee("Olga", "Yakovleva", 6000, List.of("Sales", "Customer Management")));
  }

  public static void main(String[] args) {
    //Создание Stream
    Stream<Employee> employeeStream = employees.stream(); //Определен у интерфейса Collection
    Stream<Integer> integerStream =
        Stream.of(1, 2, 3, 4, 5, 6, 7); // Построение стрима из списка элементов
    Arrays.stream(new String[] {"123", "321"}); //Построение стрима из массива
    Stream.iterate(0, x -> x + 1); //Бесконечный стрим элементов
    Stream
        .generate(() -> ThreadLocalRandom.current().nextInt())
        .limit(10)
        .forEach(System.out::println);

    //Lambda и Method Reference
    Function<String, String> upperCaseFunction = s -> s.toUpperCase();
    String upperCaseTest = upperCaseFunction.apply("test");

    Function<String, Character> getFirstCharFunction = value -> value.charAt(0);

    Predicate<Employee> salaryPredicate = e -> e.getSalary() > 1000;

    BinaryOperator<String> concatOperator = (s1, s2) -> {
      s1 = s1.toUpperCase();
      s2 = s2.toUpperCase();
      return s1 + s2;
    };
    Function<String, String> upperCaseFunctionReference = String::toUpperCase;
    TripleStringFunction function = (s1, s2, s3) -> s1 + s2 + s3;


    //Terminal operations
    employees.stream().count();
    employees.stream().forEach(e -> System.out.println(e.getFirstName()));
    employees.stream().allMatch(salaryPredicate);
    employees.stream().allMatch(e -> e.getFirstName().length() > 5);
    employees.stream().collect(Collectors.toSet());


    Consumer<String> printLn = System.out::println;
    printLn.accept("123");
    printLn.accept("234");

    for (Employee employee : employees) {
      System.out.println(employee.getFirstName() + " " + employee.getLastName());
    }
    System.out.println("asdasdasdd");


    //Optional
    Optional<Employee> first = employees.stream().findFirst();
    first.ifPresent(System.out::println);
    Stream.of("Test1", "test2", "test3")
        .map(String::toUpperCase)
        .forEach(System.out::println);

    //Intermediate operations
    employees
        .stream()
        .map(employee -> employee.getFirstName() + " " + employee.getLastName())
        .forEach(System.out::println);

    Stream.of(1, 2, 3, 4, 5)
        .map(i -> i * 2)
        .collect(Collectors.toList());

    employees
        .stream()
        .filter(employee -> employee.getSalary() > 3000f)
        .collect(Collectors.toList());

    employees
        .stream()
        .flatMap(employee -> employee.getKnowledgeAreas().stream())
        .anyMatch(s -> s.equals("StreamAPI"));

    //Collectors
    String employeesString =
        employees.stream()
            .map(employee -> employee.getFirstName() + " " + employee.getLastName())
            .collect(Collectors.joining(","));
    Map<Integer, Employee> employeeMap =
        employees.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));

    //Skip and limit
    employees.stream().limit(2).collect(Collectors.toSet());
    employees.stream().skip(2).limit(2).collect(Collectors.toSet());

    //Sort
    employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
        .collect(Collectors.toList());

  }
}
