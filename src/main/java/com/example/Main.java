package com.example;

import com.example.lambdas.Foo;
import com.example.lambdas.Person;
import com.example.lambdas.Student;
import com.example.lambdas.Teacher;
import com.example.lambdas.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * References:
 * http://www.drdobbs.com/jvm/lambda-expressions-in-java-8/240166764
 * https://dzone.com/articles/functional-programming-java-8
 * https://dzone.com/articles/dipping-into-java-8-streams
 *
 */
public class Main {

    public static void main(String[] args) {
        learnLambdas();
        learnFunctions();
        learnStreams();
    }

    private static void learnLambdas() {
        Foo foo = new Foo();
        foo.m1();
        foo.m2();
        foo.m3();

        Student s1 = new Student();
        s1.greet("Arun");

        Foo.repeatMessage("Hello", 1000);

        Student s2 = new Student();
        s2.showName();

        Teacher t1 = new Teacher();
        System.out.println(t1.getName());

        Teacher t2 = new Teacher();
        t2.showText();
    }

    private static void learnFunctions() {
        Function<Integer, Integer> add1 = x -> x + 1;
        int result = add1.apply(2);
        System.out.println(result);

        Function<Integer, Integer> mul3 = x -> x * 3;
        result = mul3.apply(4);
        System.out.println(result);

        Function<Integer, Integer> add1ThenMul3 = y -> mul3.apply(add1.apply(y));
        result = add1ThenMul3.apply(4);
        System.out.println(result);

        Function<Integer, Integer> anotherAdd1ThenMul3 = mul3.compose(add1);
        result = anotherAdd1ThenMul3.apply(4);
        System.out.println(result);

        // A BinaryOperator represents an operation upon two operands of the same type,
        // producing a result of the same type as the operands
        BinaryOperator<Function<Integer, Integer>> compose = (f, g) -> x -> g.apply(f.apply(x));
        result = compose.apply(add1, mul3).apply(4);
        System.out.println(result);

        Function<Integer, Integer> add1_2 = Utils::add1;
        result = add1_2.apply(2);
        System.out.println(result);

        UnaryOperator<Integer> add1_3 = Utils::add1;
        result = add1_3.apply(2);
        System.out.println(result);

        // To avoid incurring costs of boxing to and unboxing from Integer, use IntUnaryOperator
        IntUnaryOperator add1_4 = Utils::add1;
        result = add1_4.applyAsInt(2);
        System.out.println(result);
    }

    private static void learnStreams() {
        List<Person> persons = Arrays.asList(
                new Person("Dan", 22),
                new Person("Anup", 23),
                new Person("Sid", 10),
                new Person("Guru", 50));

        List<Integer> ages = persons.stream().map(Person::getAge).collect(toList());
        ages.forEach(System.out::println);

        System.out.println("\n");

        List<Integer> sortedAges = persons.stream().sorted( (p1, p2) -> {
            if (p1.getAge() > p2.getAge())
                return 1;
            else if (p1.getAge() < p2.getAge())
                return -1;
            else
                return 0;
        }).map(Person::getAge).collect(Collectors.toList());
        sortedAges.forEach(System.out::println);

        System.out.println("\n");

        List<Integer> sortedAges2 = persons.stream().sorted(Comparator.comparing(Person::getAge)).map(Person::getAge).collect(toList());
        sortedAges2.forEach(System.out::println);

        System.out.println("\n");

        Function<Person, Integer> getAgeFunc = Person::getAge;
        int age = getAgeFunc.apply(persons.get(0));
        System.out.println(age);

        System.out.println("\n");

        List<String> namesAbove18 = persons.stream().filter(p -> p.getAge() >= 18).map(Person::getName).collect(toList());
        namesAbove18.forEach(System.out::println);

        System.out.println("\n");

        boolean areAllAbove18 = persons.stream().allMatch(p -> p.getAge() >= 18);
        System.out.println(areAllAbove18);

        System.out.println("\n");

        boolean anyNameEndsWithU = persons.parallelStream().anyMatch(p -> p.getName().toLowerCase().endsWith("u"));
        System.out.println(anyNameEndsWithU);

        System.out.println("\n");

        List<String> namesAbove18WithLimit = persons.stream().filter(p -> p.getAge() >= 18).limit(2).map(Person::getName).collect(toList());
        namesAbove18WithLimit.forEach(System.out::println);

        System.out.println("\n");

        List<String> sortedNames = persons.stream().sorted(Comparator.comparing(Person::getName)).map(Person::getName).collect(Collectors.toList());
        sortedNames.forEach(System.out::println);
    }
}