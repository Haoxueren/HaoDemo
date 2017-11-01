package com.haoxueren.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * 使用RxJava的map操作符优化嵌套的网络请求
 */
public class MapTest {

    public static void test() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
                // 请求网络
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000);
                emitter.onNext("response");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).map(new Function<Object, String>() {
            @Override
            public String apply(@NonNull Object Object) throws Exception {
                // 解析数据
                System.out.println(Thread.currentThread().getName());
                return "name";
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String name) throws Exception {
                // 嵌套请求
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                return "json2";
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String json2) throws Exception {
                // 解析数据
                System.out.println(Thread.currentThread().getName());
                return "android";
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String job) throws Exception {
                System.out.println(Thread.currentThread().getName());
                System.out.println(job);
            }
        });
    }


}
