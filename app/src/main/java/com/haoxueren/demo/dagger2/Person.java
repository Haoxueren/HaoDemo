package com.haoxueren.demo.dagger2;

import javax.inject.Inject;

/**
 * Created by Haoxueren on 2017/10/16.
 */
public class Person {

    public String name;

    @Inject
    public Person(String name) {
        this.name = name;
    }
}
