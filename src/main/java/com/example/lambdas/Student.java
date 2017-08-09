package com.example.lambdas;

import com.example.lambdas.Person;

import java.util.Arrays;

/**
 * Created by adityad on 8/7/17.
 */

public class Student extends Person implements Named {
    @Override
    public void greet(String s) {
        String[] names = {s};
        Arrays.asList(names).forEach(super::greet);
    }

    public void showName() {
        System.out.println(getName());
    }
}
