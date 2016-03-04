package com.wk.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "way";
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.btn_go)
    Button mBtnGo;
    @Bind(R.id.btn_do_from)
    Button mBtnDoFrom;
    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.rv)
    RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                setImageView(iv);
//                doFlatMap();

            }
        });

        SharedPreferences.Editor edit = getSharedPreferences("", MODE_PRIVATE).edit();
    }

    @OnClick(R.id.btn_do_from)
    public void doFrom() {
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable.from(words)
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    Log.d(TAG, "call: " + s);

                }
            });
    }

    @OnClick(R.id.btn_do_subject)
    public void doSubject() {
        PublishSubject<Student> publishSubject = PublishSubject.create();
        Subscription subscription = publishSubject.subscribe(new Observer<Student>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Student student) {
                Log.d(TAG, "onNext: " + student.toString());
            }
        });
        Student student = new Student();
        student.setName("WangKai");
        student.setSex("male");
        publishSubject.onNext(student);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hehe");

                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "doOnCompleted call: ");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        });

    }

    public void doBehaviorSubject() {
        //BehaviorSubject会首先向他的订阅者发送截至订阅前最新的一个数据对象（或初始值）,然后正常发送订阅后的数据流。
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        //ReplaySubject会缓存它所订阅的所有数据,向任意一个订阅它的观察者重发:
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        //当Observable完成时AsyncSubject只会发布最后一个数据给已经订阅的每一个观察者。
        AsyncSubject<String> asyncSubject = AsyncSubject.create();


    }


    private Observable<AppInfo> getApps() {
        Observable<AppInfo> observable = Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo info : resolveInfos) {
                    ActivityInfo activityInfo = info.activityInfo;

                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(new AppInfo("1", 0, "hehe"));
                }
            }
        });

        observable.toSortedList()
            .subscribe(new Observer<List<AppInfo>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(List<AppInfo> appInfos) {
                    Log.d(TAG, "onNext: ");
                }
            });
        return observable;


    }

    @OnClick(R.id.btn_do_range)
    public void doRange() {
        Observable.range(1, 10).subscribe(new Observer<Integer>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        });

    }


    @OnClick(R.id.btn_do_interval)
    public void doInterval() {

        String[] words = {"Hello", "Hi", "Aloha"};
        long[] nums = {1, 2, 3, 4, 5};

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(new Observer<Long>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Long aLong) {
                    Log.d(TAG, "onNext: " + aLong);
                }
            });

    }

    @OnClick(R.id.btn_do_filter)
    public void doFilter() {                    //过滤
        Observable.interval(1, TimeUnit.SECONDS)
            .filter(new Func1<Long, Boolean>() {
                @Override
                public Boolean call(Long aLong) {
                    return aLong == 5;
                }
            })
            .subscribe(new Observer<Long>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Long aLong) {
                    Log.d(TAG, "onNext: " + aLong);
                }
            });

    }

    @OnClick(R.id.btn_do_take)
    public void doTake() {                    //选取序列中的元素
        Integer[] nums = {1, 2, 3, 4, 5};
        Observable.from(nums)
//            .take(3)
            .takeLast(3)
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    Log.d(TAG, "call: " + integer);
                }
            });
    }

    @OnClick(R.id.btn_do_first)
    public void doFirst() {                    //只发射第一个
        Integer[] nums = {1, 2, 3, 4, 5};
        Observable.from(nums)
//            .take(3)
            .takeLast(3)
            .takeFirst(new Func1<Integer, Boolean>() {
                @Override
                public Boolean call(Integer integer) {
                    Log.d(TAG, "Func1 call: " + integer);
                    return true;
                }
            })
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    Log.d(TAG, "Action1 call: " + integer);
                }
            });

        Observable<GroupedObservable<String, Integer>> observable = Observable.from(nums)
            .groupBy(new Func1<Integer, String>() {
                @Override
                public String call(Integer integer) {
                    return null;
                }
            });

        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {

            }
        });
    }

    public void setImageView(final ImageView iv) {
        final int imageResId = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();
                subscriber.onNext(imageResId);
                Log.d(TAG, "call: threadId= " + Thread.currentThread().getId());
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: " + e.getMessage());
                    Log.d(TAG, "call: threadId= " + Thread.currentThread().getId());
                }

                @Override
                public void onNext(Integer integer) {
                    iv.setImageResource(integer);
                    Log.d(TAG, "call: threadId= " + Thread.currentThread().getId());
                }
            });


    }

    private void doFlatMap() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            Course course = new Course("courseName " + i);
            Course course1 = new Course("courseName_second " + i);
            ArrayList<Course> courses = new ArrayList<>();
            courses.add(course);
            courses.add(course1);
            student.setCourses(courses);
            students.add(student);
        }

        Observable.from(students)
            .flatMap(new Func1<Student, Observable<Course>>() {
                @Override
                public Observable<Course> call(Student student) {
                    return Observable.from(student.getCourses());
                }
            }).subscribe(new Subscriber<Course>() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "flat onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "onNext: " + course.getCourseName());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
