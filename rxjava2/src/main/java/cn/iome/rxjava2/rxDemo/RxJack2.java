package cn.iome.rxjava2.rxDemo;

/**
 * Created by haoping on 17/4/5.
 * TODO
 */
public class RxJack2 {

    public static void main(String[] args) {
        rx1();
    }

    private static void rx1() {
        final User user = new User();
        final UserManager um = new UserManager() {
            @Override
            public User getUser() {
                return user;
            }

            @Override
            public void setName(final String name, final Runnable callback) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user.name = name;
                        if(user.name != null){
                            callback.run();
                        }
                    }
                }).start();

            }

            @Override
            public void setAge(int age, Runnable callback) {

            }
        };
        System.out.println(um.getUser().name);

        um.setName("Jane Doe", new Runnable() {
            @Override
            public void run() {
                System.out.println(um.getUser().name);
            }
        });
    }

    interface UserManager {
        User getUser();

        void setName(String name, Runnable callback);

        void setAge(int age, Runnable callback);
    }


    private static class User {
        String name;
        int age;
    }

}
