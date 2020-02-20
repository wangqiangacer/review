package com.jacken.algorithm.day01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


public class Person {

    private  String name;
    private  int  age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("person  finalize");
        super.finalize();
    }
}
