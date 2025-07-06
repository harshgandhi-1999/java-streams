import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    static List<Employee> employees = Arrays.asList(
            new Employee(1, "Abraham", 29, "IT", "Mumbai", 20000, "Male"),
            new Employee(2, "Mary", 27, "Sales", "Chennai", 25000, "Female"),
            new Employee(3, "Joe", 28, "IT", "Chennai", 22000, "Male"),
            new Employee(4, "John", 29, "Sales", "Gurgaon", 29000, "Male"),
            new Employee(5, "Liza", 25, "Sales", "Bangalore", 32000, "Female"),
            new Employee(6, "Peter", 27, "Admin", "Mumbai", 31500, "Male"),
            new Employee(7, "Harry", 30, "Research", "Kochi", 21000, "Male")
    );

    public static void main(String[] args) {

//        Q1 Find list of employees whose name starts with alphabet A

        List<Employee> namesStartWithA = employees.stream().filter(employee -> employee.getName().startsWith("A")).toList();
        System.out.println("Employees whose name starts with A \n" + namesStartWithA);


//        Q2 Group The employees By Department Names

        Map<String, List<Employee>> groupByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames));
        System.out.println("Group employees by department names \n"+groupByDept);

//        Q3 Find the total count of employees using stream

        long count = employees.stream().count();
        System.out.println("Total Number of employees " + count);


//        Q4 Find the max age of employees
        Optional<Employee> maxAgeEmployee = employees.stream().max(Comparator.comparingInt(Employee::getAge));
//        int maxAge = employees.stream().mapToInt(Employee::getAge).max().getAsInt();
        // ** above max() function return OptionalInt **

        int age = maxAgeEmployee.get().getAge();
        System.out.println("Maximum age of employee "+ age);

//        Q5 Find all department names
        List<String> deptNamesList = employees.stream()
                .map(Employee::getDepartNames).distinct().toList();
        System.out.println(deptNamesList);

//        Q6 	Find the count of employee in each department
        Map<String, Long> employeeCountByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting()));
        System.out.println(employeeCountByDept);

//        Q7 Find the list of employees whose age is less than 30

        List<Employee> ageLessThanThirty = employees.stream().filter(employee -> employee.getAge() < 30).toList();
        System.out.println(ageLessThanThirty);


//        Q8  Find the average age of male and female employee

        Map<String, Double> avgAgeBasedOnGender = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge) ));

        System.out.println(avgAgeBasedOnGender);

        // Q9 Find the department who is having maximum number of employee
        Map<String, Long> deptCountMap = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting()));
        Map.Entry<String, Long> deptMaxCount = deptCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(deptMaxCount);

//        Q10 Find the Employee who stays in Delhi and sort them by their names

        List<Employee> employeesInDelhi = employees.stream().filter(employee -> employee.getAddress().equalsIgnoreCase("Delhi")).sorted(Comparator.comparing(Employee::getName)).toList();
        System.out.println(employeesInDelhi);


//        Q11 Find the average salary in all departments
        Map<String, Double> avgSalaryDeptWise = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartNames,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgSalaryDeptWise);


//       Q12 Find the highest salary in each department
        Map<String, Optional<Employee>> maxSalaryDeptWise = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartNames,
                Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

        System.out.println(maxSalaryDeptWise);

//        Q13 Find the list of employee and sort them by their salary

        List<Employee> sortBySalary = employees.stream().sorted(Comparator.comparing(Employee::getSalary)).toList();
        System.out.println(sortBySalary);

//        Q14 Find the employee who has second lowest salary

        Optional<Employee> optionalEmployee = employees.stream().sorted(Comparator.comparing(Employee::getSalary)).skip(1).findFirst();
        System.out.println(optionalEmployee.get());

//        Q15  Calculate the Total Salary of Employees in Each Department
        Map<String, Double> totalSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                Employee::getDepartNames,
                Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println(totalSalaryByDept);

    }


}
