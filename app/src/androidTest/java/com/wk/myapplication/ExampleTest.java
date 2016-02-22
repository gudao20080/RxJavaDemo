package com.wk.myapplication;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-01-26 09:32
 */
public class ExampleTest extends InstrumentationTestCase {

    private static final String TAG = "way";

    public void testRxJava() {
        List<Student> students = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Student student = new Student();
//            Course course = new Course("courseName " + i);
//            Course course1 = new Course("courseName_second " + i);
//            ArrayList<Course> courses = new ArrayList<>();
//            courses.add(course);
//            courses.add(course1);
//            student.setCourses(courses);
//        }
//
//        Observable.from(students)
//            .flatMap(new Func1<Student, Observable<Course>>() {
//                @Override
//                public Observable<Course> call(Student student) {
//                    return Observable.from(student.getCourses());
//                }
//            }).subscribe(new Subscriber<Course>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onNext(Course course) {
//                Log.d(TAG, "onNext: " + course.getCourseName());
//            }
//        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d(TAG, "call: 0");
            }
        }).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {

                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: ");
                    }
                };
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: 1");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 1");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: 1");

            }
        });

        Observable.just(1, 2, 34)
                    .map(new Func1<Integer, String>() {
                        @Override
                        public String call(Integer integer) {

                            return "s : " + integer;
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            Log.d(TAG, "call: " + s);
                        }
                    });

       Observable.from(students)
           .subscribeOn(Schedulers.newThread())
           .observeOn(Schedulers.io())
           .flatMap(new Func1<Student, Observable<Course>>() {
               @Override
               public Observable<Course> call(Student student) {
                   return Observable.from(student.getCourses());
               }
           }).subscribe(new Action1<Course>() {
           @Override
           public void call(Course course) {

           }
       });

        new Subscriber<Integer>(){
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }
        };


    }


    private Observable<Integer> getInt() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }

                subscriber.onNext(110);
                subscriber.onCompleted();
            }
        });

    }

    private List<Weather> getWeathers() {
        List<Weather> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Weather(i+"", + i + "   iii"));
        }
        return list;
    }


}  
