package Lambdas;

import java.util.*;
import java.util.function.*;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,7,4,5));
        Consumer<List<Integer>> consumer = x -> Collections.sort(x);
        consumer.accept(list);
        System.out.println("List sorted: " + list);
        list = new ArrayList<>(Arrays.asList(1,2,7,4,5));
        consumer = Collections::sort; // Java knows which method to call based on the type and number of parameters.
        consumer.accept(list);
        System.out.println("List sorted: " + list);
    }

    public static void boundMR(){
        String name = "Mr. Joe Bloggs";
        Predicate<String> predicate = s -> name.startsWith(s);
        System.out.println("Mr. Joe Bloggs start with \"Mr.\"? " + predicate.test("Mr."));
        System.out.println("Mr. Joe Bloggs start with \"Ms.\"? " + predicate.test("Ms."));
        predicate = name::startsWith;
        System.out.println("Mr. Joe Bloggs start with \"Mr.\"? " + predicate.test("Mr."));
        System.out.println("Mr. Joe Bloggs start with \"Ms.\"? " + predicate.test("Ms."));
    }

    public static void unboundMR(){
        Predicate<String> predicate = x -> x.isEmpty();
        System.out.println("String \"\" is empty? " + predicate.test(""));
        System.out.println("String \"xyz\" is empty? " + predicate.test("xyz"));
        predicate = String::isEmpty;
        System.out.println("String \"\" is empty? " + predicate.test(""));
        System.out.println("String \"xyz\" is empty? " + predicate.test("xyz"));
        BiPredicate<String, String> biPredicate = (x,y) -> x.startsWith(y);
        System.out.println("Mr. Joe Bloggs start with \"Mr.\"? " + biPredicate.test("Mr. Joe Bloggs","Mr."));
        System.out.println("Mr. Joe Bloggs start with \"Ms.\"? " + biPredicate.test("Mr. Joe Bloggs","Ms."));
        biPredicate = String::startsWith;
        System.out.println("Mr. Joe Bloggs start with \"Mr.\"? " + biPredicate.test("Mr. Joe Bloggs","Mr."));
        System.out.println("Mr. Joe Bloggs start with \"Ms.\"? " + biPredicate.test("Mr. Joe Bloggs","Ms."));
    }

    public static void constructorMR(){
        Supplier<List<String>> supplier = () -> new ArrayList<>();
        List<String> list = supplier.get();
        list.add("Lambda");
        System.out.println("List: " + list);
        supplier = ArrayList::new;
        list = supplier.get();
        list.add("Lambda");
        System.out.println("List: " + list);
        Function<Integer, List<String>> function = x -> new ArrayList<>(x);
        list = function.apply(10);
        list.add("Lambda");
        System.out.println("List: " + list);
        function = ArrayList::new;
        list = function.apply(10);
        list.add("Lambda");
        System.out.println("List: " + list);
    }
}
