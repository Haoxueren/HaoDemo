package com.haoxueren.demo.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Haoxueren on 2017/10/16.
 */
@Module
public class PersonModule {

    public String name;

    public PersonModule(String name) {
        this.name = name;
    }

    @Provides
    public String provideName() {
        return name;
    }

    @Provides
    public Person providePerson() {
        return new Person(name);
    }



}
