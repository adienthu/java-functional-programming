package com.example.lambdas;

/**
 * Created by adityad on 8/7/17.
 */

public class Person implements Greeter{
    private final String mName;
    private final int mAge;

    public Person(String name, int age) {
        this.mName = name;
        this.mAge = age;
    }

    public Person() {
        mName = "A person";
        mAge = 1;
    }

    @Override
    public void greet(String s) {
        System.out.print("Hello " + s);
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }
}
