package com.example.lambdas;

/**
 * Created by adityad on 8/7/17.
 */

public class Teacher implements Named, Named2 {
    @Override
    public String getName() {
        return Named2.super.getName();
    }

    public void showText() {
        System.out.println(Named.getText());
    }
}
