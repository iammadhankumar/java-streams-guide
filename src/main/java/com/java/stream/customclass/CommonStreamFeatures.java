package com.java.stream.customclass;  // Package name

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * CommonStreamOperations class demonstrates various Stream API features 
 * using a custom Employee class with multiple attributes. This class 
 * includes examples of filtering, mapping, reducing, and collecting data 
 * from a list of Employee objects.
 * 
 * Features demonstrated:
 * 1. Filtering employees by department.
 * 2. Mapping employee data to different types (e.g., names).
 * 3. Flattening nested data structures (skills).
 * 4. Distinct values (job titles).
 * 5. Sorting employees by experience.
 * 6. Peek for debugging.
 * 7. Reducing to find maximum salary.
 * 8. Counting full-time employees.
 * 9. Grouping employees by department.
 * 10. Finding the first employee from a specific department.
 * 11. Checking conditions with anyMatch.
 * 12. Processing with parallel streams for performance.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class CommonStreamFeatures {

    public static void main(String[] args) {
        // Create a list of employees with their details
        List<Employee> employees = Arrays.asList(
            new Employee("John Doe", 1001, 45000, "IT", "Software Engineer", 5, "Full-time", Arrays.asList("Java", "Python")),
            new Employee("Alice Smith", 1002, 55000, "HR", "HR Manager", 10, "Full-time", Arrays.asList("Communication", "Recruiting")),
            new Employee("Bob Johnson", 1003, 50000, "IT", "DevOps Engineer", 3, "Contract", Arrays.asList("DevOps", "Python")),
            new Employee("Mary Davis", 1004, 60000, "Finance", "Financial Analyst", 8, "Full-time", Arrays.asList("Accounting", "Management")),
            new Employee("David Brown", 1005, 75000, "Finance", "Finance Manager", 12, "Part-time", Arrays.asList("Management", "Leadership"))
        );

        // 1. filter(): Get employees from the "IT" department
        List<Employee> itEmployees = employees.stream()
            .filter(employee -> "IT".equals(employee.getDepartment()))
            .collect(Collectors.toList());
        log.info("Employees from IT department: " + itEmployees);

        // 2. map(): Get a list of names of all employees
        List<String> employeeNames = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.toList());
        log.info("Names of all employees: " + employeeNames);

        // 3. flatMap(): Flatten the list of skills of all employees into a single list
        List<String> allSkills = employees.stream()
            .flatMap(employee -> employee.getSkills().stream())
            .distinct()  // Remove duplicate skills
            .collect(Collectors.toList());
        log.info("All unique skills: " + allSkills);

        // 4. distinct(): Get unique job titles from the employee list
        List<String> uniqueJobTitles = employees.stream()
            .map(Employee::getJobTitle)
            .distinct()
            .collect(Collectors.toList());
        log.info("Unique job titles: " + uniqueJobTitles);

        // 5. sorted(): Sort the list of employees by years of experience
        List<Employee> sortedByExperience = employees.stream()
            .sorted(Comparator.comparing(Employee::getYearsOfExperience))
            .collect(Collectors.toList());
        log.info("Employees sorted by years of experience: " + sortedByExperience);

        // 6. peek(): Inspect each employee in the stream without modifying the stream
        List<Employee> peeked = employees.stream()
            .peek(employee -> log.info("Processing: " + employee))
            .collect(Collectors.toList());

        // 7. reduce(): Find the employee with the highest salary using reduction
        Optional<Employee> highestPaid = employees.stream()
            .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2);
        highestPaid.ifPresent(employee -> log.info("Highest paid employee: " + employee));

        // 8. count(): Count the number of full-time employees
        long fullTimeEmployees = employees.stream()
            .filter(employee -> "Full-time".equals(employee.getEmploymentType()))
            .count();
        log.info("Number of full-time employees: " + fullTimeEmployees);

        // 9. collect(): Group employees by their department
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        log.info("Employees grouped by department: " + employeesByDepartment);

        // 10. findFirst(): Find the first employee from the "Finance" department
        Optional<Employee> firstFromFinance = employees.stream()
            .filter(employee -> "Finance".equals(employee.getDepartment()))
            .findFirst();
        firstFromFinance.ifPresent(employee -> log.info("First employee from Finance: " + employee));

        // 11. anyMatch(): Check if there’s any employee with more than 10 years of experience
        boolean isAnyoneWithMoreThan10YearsExperience = employees.stream()
            .anyMatch(employee -> employee.getYearsOfExperience() > 10);
        log.info("Is anyone with more than 10 years of experience? " + isAnyoneWithMoreThan10YearsExperience);

        // 12. parallelStream(): Process employees in parallel for efficiency
        log.info("Parallel Stream:");
        employees.parallelStream().forEach(employee -> log.info(employee.getName()));
    }
}
