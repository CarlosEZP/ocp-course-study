package Lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {
    @FunctionalInterface
    interface Retriable<T>{
        T retrieve();
    }
    @FunctionalInterface
    interface Printable<T>{
        void print(String s);
    }
    @FunctionalInterface
    interface Evaluate<T>{
        boolean isNegative(Integer i);
    }
    @FunctionalInterface
    interface Functionable<T, R>{
        R applyThis(T t);
    }

    public static class Person{
        private Integer age;
        private String name;
        private Double height;

        public Person(String name, Integer age, Double height){
            this.name = name;
            this.age = age;
            this.height = height;
        }
        public Integer getAge(){
            return this.age;
        }
        public String getName(){
            return this.name;
        }
        public Double getHeight(){
            return this.height;
        }
        private static List<Person> getPeople() {
            List<Person> result = new ArrayList<>();
            result.add(new Person("Mike", 33, 1.8));
            result.add(new Person("Mary", 25, 1.4));
            result.add(new Person("Alan", 34, 1.7));
            result.add(new Person("Zoe", 30, 1.5));
            return result;
        }
        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", height=" + height +
                    '}';
        }
    }

    public static void main(String[] args) {
        consumer();
        supplier();
        predicate();
        function();
        System.out.println("5 = ");
        List<Person> listPeople = Person.getPeople();
        System.out.println("6 = ");
        sortAge(listPeople);
        listPeople.forEach(System.out::println);
        System.out.println("7 = ");
        sortName(listPeople);
        listPeople.forEach(System.out::println);
        System.out.println("8 = ");
        sortHeight(listPeople);
        listPeople.forEach(System.out::println);
    }

    public static void consumer(){
        Printable<String> printable = x -> System.out.println(x);
        printable.print("1-a = Printable lambda!");
        Consumer<String> consumer = System.out::println;
        consumer.accept("1-b = Printable lambda!");
    }

    public static void supplier(){
        Retriable<Integer> retriable = () -> 77;
        System.out.println("2-a = " + retriable.retrieve());
        Supplier<Integer> supplier = () -> 77;
        System.out.println("2-b = " + supplier.get());
    }

    public static void predicate(){
        Evaluate<Integer> evaluate = x -> x < 0;
        System.out.println("3-a = " + evaluate.isNegative(1));
        System.out.println("3-a = " + evaluate.isNegative(-1));
        Predicate<Integer> predicate = x -> x < 0;
        System.out.println("3-b = " + predicate.test(1));
        System.out.println("3-b = " + predicate.test(-1));
        Predicate<Integer> predicateEven = x-> x % 2 == 0;
        System.out.println("3-c = " + check(4,predicateEven));
        System.out.println("3-c = " + check(7,predicateEven));
        Predicate<String> predicateBeginsWith = s -> s.startsWith("Mr.");
        System.out.println("3-c = " + check("Mr. Joe Bloggs",predicateBeginsWith));
        System.out.println("3-c = " + check("Ms. Ann Bloggs",predicateBeginsWith));
        Predicate<Person> predicateIsAdult = x -> x.getAge() >= 18;
        System.out.println("3-c = " + check(new Person("Mike",33,1.8),predicateIsAdult));
        System.out.println("3-c = " + check(new Person("Ann",13,1.4),predicateIsAdult));
    }

    public static void function(){
        Functionable<Integer, String> functionable = x -> "Number is: " + x;
        System.out.println("4-a = " + functionable.applyThis(25));
        Function<Integer, String> function = x -> "Number is: " + x;
        System.out.println("4-b = " + function.apply(25));
    }

    public static void sortAge(List<Person> personList){
        personList.sort(Comparator.comparing(Person::getAge));
    }

    public static void sortName(List<Person> personList){
        personList.sort(Comparator.comparing(Person::getName));
    }

    public static void sortHeight(List<Person> personList){
        personList.sort(Comparator.comparing(Person::getHeight));
    }

    public static <T> boolean check(T generic, Predicate<T> predicate){
        return predicate.test(generic);
    }
}