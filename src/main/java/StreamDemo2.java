import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo2 {
    public static void main(String[] args) {
//        1. Find the First Non-Repeating Character in a String


        String input = "swiss";
        Character firstNonRepeatingCh = input.chars()   // remember this method because there is no other method to convert to stream of characters input.chars().mapToObj(c -> (char)c)
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c)==input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
        System.out.println("firstNonRepeatingCh = " + firstNonRepeatingCh);


//        2. Count Frequency of Elements in a List
        List<String> items = List.of("apple", "banana", "apple", "orange", "banana");

        Map<String, Long> freqMap = items.stream().collect(Collectors.groupingBy(
                item -> item,
                Collectors.counting()
        ));
        System.out.println(freqMap);

//        3. Find Top-N Highest Numbers in a List
        List<Integer> numbers = List.of(10, 20, 5, 30, 25);

        List<Integer> top2highest = numbers.stream().sorted(Comparator.reverseOrder()).limit(2).toList();
        System.out.println(top2highest);

//        4. Partition a List into Odd and Even Numbers
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 6);

        Map<Boolean, List<Integer>> partitionEvenOdd = numbers2.stream().collect(Collectors.partitioningBy(num -> num%2==0));
        System.out.println(partitionEvenOdd);

//        5. Flatten a List of Lists

        // in flat map function for every element it puts the result into final 1-D list of elements.
        // so for every list item inside nestedList we have to return the stream object.
        // if we return listItem.stream() it will put all elements into final list
        // if we want that we double every item in the list we simply do listItem.stream().map(el -> el*2)
        // ex: [1,2]  will be converted to [2,4] and then put into final list.
        // map function transforms the elements and return stream object
        List<List<Integer>> nestedList = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        List<Integer> flatList = nestedList.stream().flatMap(listItem -> listItem.stream().map(e -> e*2)).toList();
        System.out.println(flatList);

//        7. Sort a List of Custom Objects by Multiple Fields

        List<Person> people = List.of(new Person("John", 25), new Person("Alice", 30), new Person("John", 20));

        List<Person> sortedPeople = people.stream().sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge)).toList();
        sortedPeople.forEach(p -> System.out.println(p.getName() + " - " + p.getAge()));


//        9. Filter Strings Starting with a Specific Letter and Collect Them
        List<String> names = List.of("Alice", "Bob", "Charlie", "Alex", "Brian");

        List<String> namesStartingWithA = names.stream().filter(name -> name.startsWith("A")).toList();
        System.out.println(namesStartingWithA);

//        10. Find the Longest String in a List

        String maxLengthName = names.stream().max(Comparator.comparingInt(String::length)).orElse("");
        System.out.println(maxLengthName);
    }

}
