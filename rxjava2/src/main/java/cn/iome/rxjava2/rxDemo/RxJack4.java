package cn.iome.rxjava2.rxDemo;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by haoping on 17/4/5.
 * TODO
 */
public class RxJack4 {

    public static void main(String[] args) {
        rx1();
    }

    private static void rx1() {
        final User user = new User();
        final UserManager um = new UserManager() {
            @Override
            public Observable<User> getUser() {
                return Observable.fromCallable(new Callable<User>() {
                    @Override
                    public User call() throws Exception {
                        return user;
                    }
                });
            }

            @Override
            public Completable setName(final String name) {
                return Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        user.name = name;
                    }
                });
            }

            @Override
            public Completable setAge(final int age) {
                return Completable.fromRunnable(new Runnable() {
                    @Override
                    public void run() {
                        user.age = age;
                    }
                });
            }
        };

        um.getUser().subscribe(new Consumer<User>() {
            @Override
            public void accept(@NonNull User user) throws Exception {
                System.out.println(user.name);
            }
        });

        um.setName("heber").subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println(user.name);
            }
        });

        um.getUser().subscribe(new Consumer<User>() {
            @Override
            public void accept(@NonNull User user) throws Exception {
                System.out.println(user.name);
            }
        });


    }

    interface UserManager {
        Observable<User> getUser();

        Completable setName(String name);

        Completable setAge(int age);
    }


    private static class User {
        String name;
        int age;
    }

}
