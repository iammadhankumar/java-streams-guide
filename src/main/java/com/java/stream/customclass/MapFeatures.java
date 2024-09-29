package com.java.stream.customclass;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * The MapFeatures class demonstrates various functionalities of the Java Stream API
 * using a list of Employee objects. This class illustrates how to manipulate collections
 * and perform operations that involve creating maps, grouping data, counting occurrences,
 * and transforming collections into maps.
 * 
 * Author: MadhanKumar
 */
@Slf4j
public class MapFeatures {
    
    public static void main(String[] args) {
        // Create a list of employees with their details
        List<Employee> employees = Arrays.asList(
            new Employee("John Doe", 1001, 45000, "IT", "Software Engineer", 5, "Full-time", Arrays.asList("Java", "Python")),
            new Employee("Alice Smith", 1002, 55000, "HR", "HR Manager", 10, "Full-time", Arrays.asList("Communication", "Recruiting")),
            new Employee("Bob Johnson", 1003, 50000, "IT", "DevOps Engineer", 3, "Contract", Arrays.asList("DevOps", "Python")),
            new Employee("Mary Davis", 1004, 60000, "Finance", "Financial Analyst", 8, "Full-time", Arrays.asList("Accounting", "Management")),
            new Employee("David Brown", 1005, 75000, "Finance", "Finance Manager", 12, "Part-time", Arrays.asList("Management", "Leadership")),
            new Employee("Emily Clark", 1006, 48000, "IT", "Quality Analyst", 4, "Full-time", Arrays.asList("Testing", "Java")),
            new Employee("Michael Wilson", 1007, 52000, "IT", "System Administrator", 6, "Full-time", Arrays.asList("Linux", "Networking")),
            new Employee("Sarah Johnson", 1008, 60000, "HR", "Recruitment Specialist", 7, "Part-time", Arrays.asList("Interviewing", "Communication"))
        );

        // 1. Count employees by employment type
        Map<String, Long> employmentTypeCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getEmploymentType, Collectors.counting()));
        log.info("Count of employees by employment type: " + employmentTypeCount);

        // 2. Create a Map of employee ID and Employee object
        Map<Integer, Employee> employeeMap = employees.stream()
            .collect(Collectors.toMap(Employee::getId, employee -> employee));
        log.info("Employee Map (ID -> Employee): " + employeeMap);

        // 3. Group employees by their skills
        Map<String, List<Employee>> employeesBySkill = employees.stream()
            .flatMap(employee -> employee.getSkills().stream()
                .map(skill -> new AbstractMap.SimpleEntry<>(skill, employee)))
            .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
        log.info("Employees grouped by skills: " + employeesBySkill);

        // 4. Count employees by department
        Map<String, Long> departmentCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        log.info("Count of employees by department: " + departmentCount);

        // 5. Create a Map of employee names and salaries
        Map<String, Integer> employeeNameSalaryMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        log.info("Employee Name to Salary Map: " + employeeNameSalaryMap);

        // 6. Collect employee information as a map of names to details (ID and Job Title)
        Map<String, String> employeeDetailsMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, 
                employee -> "ID: " + employee.getId() + ", Job Title: " + employee.getJobTitle()));
        log.info("Employee Name to Details Map: " + employeeDetailsMap);

        // 7. Group employees by years of experience
        Map<Integer, List<Employee>> employeesByExperience = employees.stream()
            .collect(Collectors.groupingBy(Employee::getYearsOfExperience));
        log.info("Employees grouped by years of experience: " + employeesByExperience);

        // 8. Merge employees with the same job title into a list
        Map<String, List<Employee>> employeesByJobTitle = employees.stream()
            .collect(Collectors.groupingBy(Employee::getJobTitle));
        log.info("Employees grouped by job title: " + employeesByJobTitle);

        // 9. Filter employees by salary to create a map of names to salaries for those earning more than 50,000
        Map<String, Integer> highSalaryMap = employees.stream()
            .filter(employee -> employee.getSalary() > 50000)
            .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        log.info("Employees with salary > 50,000: " + highSalaryMap);

        // 10. Merging values when duplicate keys exist (if employees have the same name)
        Map<String, Integer> mergedEmployeeMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, Employee::getSalary, Integer::sum));
        log.info("Merged Employee Salary Map (handling duplicates): " + mergedEmployeeMap);

        // 11. Grouping employees by department and job title (composite key)
        Map<String, List<Employee>> compositeKeyMap = employees.stream()
            .collect(Collectors.groupingBy(employee -> employee.getDepartment() + " - " + employee.getJobTitle()));
        log.info("Employees grouped by Department and Job Title: " + compositeKeyMap);

        // 12. Combine two maps: Example of merging two separate employee salary maps
        Map<String, Integer> additionalSalaries = Map.of(
            "John Doe", 5000,
            "Alice Smith", 3000
        );
        Map<String, Integer> combinedSalaries = employeeNameSalaryMap.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, 
                Map.Entry::getValue, 
                Integer::sum, // Merge function
                () -> new java.util.HashMap<>(additionalSalaries)));
        log.info("Combined Salaries Map: " + combinedSalaries);

        // 13. Group employees by salary range
        Map<String, List<Employee>> salaryRangeMap = employees.stream()
            .collect(Collectors.groupingBy(employee -> {
                if (employee.getSalary() < 50000) return "Low";
                else if (employee.getSalary() < 60000) return "Medium";
                else return "High";
            }));
        log.info("Employees grouped by salary range: " + salaryRangeMap);

        // 14. Create a map of employee names with the length of their names
        Map<String, Integer> nameLengthMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, employee -> employee.getName().length()));
        log.info("Employee Name Length Map: " + nameLengthMap);

        // 15. Create a map of departments to the highest salary in that department
        Map<String, Integer> maxSalaryByDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, 
                Collectors.mapping(Employee::getSalary, Collectors.maxBy(Integer::compareTo)))
            ).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().orElse(0)));
        log.info("Max Salary by Department: " + maxSalaryByDepartment);

        // 16. Count unique skills across all employees
        Map<String, Long> uniqueSkillsCount = employees.stream()
            .flatMap(employee -> employee.getSkills().stream())
            .collect(Collectors.groupingBy(skill -> skill, Collectors.counting()));
        System.out.println("Unique Skills Count: " + uniqueSkillsCount);

        // 17. Create a map of job titles and the number of employees in each title
        Map<String, Long> jobTitleCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getJobTitle, Collectors.counting()));
        log.info("Number of Employees by Job Title: " + jobTitleCount);

        // 18. Create a map of employee names to the first skill they possess
        Map<String, String> firstSkillMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, 
                employee -> employee.getSkills().isEmpty() ? "No Skills" : employee.getSkills().get(0)));
        log.info("Employee Names to First Skill Map: " + firstSkillMap);

        // 19. Create a map of employment type to a list of employee names
        Map<String, List<String>> employeeNamesByEmploymentType = employees.stream()
            .collect(Collectors.groupingBy(Employee::getEmploymentType, 
                Collectors.mapping(Employee::getName, Collectors.toList())));
        log.info("Employee Names grouped by Employment Type: " + employeeNamesByEmploymentType);

        // 20. Create a map of employee names and the number of characters in each name
        Map<String, Integer> employeeNameCharsMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, 
                employee -> employee.getName().length()));
        log.info("Employee Name Characters Count: " + employeeNameCharsMap);

        // 21. Create a map of employee IDs to their salary increase (10%)
        Map<Integer, Double> salaryIncreaseMap = employees.stream()
            .collect(Collectors.toMap(Employee::getId, 
                employee -> employee.getSalary() * 1.10));
        log.info("Employee Salary Increase Map: " + salaryIncreaseMap);

        // 22. Count the number of employees in each department who earn above 50,000
        Map<String, Long> highEarnersByDepartment = employees.stream()
            .filter(employee -> employee.getSalary() > 50000)
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        log.info("Count of High Earners by Department: " + highEarnersByDepartment);

        // 23. Map of employee names to their skills as a comma-separated string
        Map<String, String> employeeSkillsMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, 
                employee -> String.join(", ", employee.getSkills())));
        log.info("Employee Skills Map: " + employeeSkillsMap);

        // 24. Map of employee names to their experience in years
        Map<String, Integer> employeeExperienceMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, Employee::getYearsOfExperience));
        log.info("Employee Experience Map: " + employeeExperienceMap);

        // 25. Create a summary of employee data as a list of strings
        List<String> employeeSummaryList = employees.stream()
            .map(employee -> employee.getName() + " - " + employee.getJobTitle() + " (Salary: " + employee.getSalary() + ")")
            .collect(Collectors.toList());
        log.info("Employee Summary List: " + employeeSummaryList);
    }
}


