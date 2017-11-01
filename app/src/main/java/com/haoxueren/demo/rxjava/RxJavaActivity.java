package com.haoxueren.demo.rxjava;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.haoxueren.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);


        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(1);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        // 第二个被观察者
        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
                emitter.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Object>() {
            @Override
            public Object apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer + "：" + integer2;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("accept：" + o);
            }
        });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("doOnNextConsumer");
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subscribeConsumer");
            }
        });

    }

    private void startWith(Observable<Integer> observable1, Observable<Integer> observable2) {
        observable2.startWith(observable1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private void concat() {
        // 第一个被观察者
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        // 第二个被观察者
        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
                emitter.onComplete();
            }
        });

        // 连接两个被观察者
        Observable.concat(observable1, observable2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private void concatJust() {
        Observable<Integer> just1 = Observable.just(1, 2, 3);
        Observable<Integer> just2 = Observable.just(4, 5, 6);
        Observable.concat(just1, just2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private void toList() {
        Observable.just(1, 2, 3).toList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> list) throws Exception {
                System.out.println(list);
            }
        });
    }

    private void map() {
        Observable.just(10, 20, 30).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return String.format(Locale.CHINA, "%d%%", integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    private void interval() {
        int initialDelay = 1;
        int intervalPeriod = 3;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        Scheduler scheduler = Schedulers.computation();
        Observable.interval(initialDelay, intervalPeriod, timeUnit, scheduler)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long l) throws Exception {
                        System.out.println(l);
                    }
                });
    }

    private void fromIterable() {
        List<String> list = new ArrayList<>();
        list.add("RxJava");
        list.add("Retrofit");
        list.add("GreenDao");
        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    private void fromArray() {
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        Observable.fromArray(integers).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    private void just() {
        Observable.just("java", "kotlin", "python").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    // 通过Observable.create()创建一个被观察者
    private void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("emitter.onNext01");
                emitter.onNext("emitter.onNext02");
                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public void map(View view) {
        MapTest.test();
    }
}
