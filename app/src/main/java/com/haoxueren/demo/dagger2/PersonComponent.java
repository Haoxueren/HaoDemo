package com.haoxueren.demo.dagger2;

import dagger.Component;
import dagger.Module;

/**
 * Created by Haoxueren on 2017/10/16.
 */
@Component(modules = PersonModule.class)
public interface PersonComponent {

    void inject(DaggerActivity activity);
}
