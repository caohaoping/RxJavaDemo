package cn.iome.rxjava2.rxDemo;

/**
 * Created by haoping on 17/4/5.
 * TODO
 */
public class RxJack3 {

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
            public void setName(final String name, final Listener listener) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (name != null) {
                            user.name = name;
                            listener.onSuccess();
                        } else {
                            listener.onFailed(new Exception("error"));
                        }
                    }
                }).start();
            }

            @Override
            public void setAge(int age, Listener listener) {

            }
        };
        System.out.println(um.getUser().name);

        um.setName("Jane Doe", new UserManager.Listener() {
            @Override
            public void onSuccess() {
                System.out.println(um.getUser().name);
            }

            @Override
            public void onFailed(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    interface UserManager {

        interface Listener {
            void onSuccess();

            void onFailed(Exception e);
        }

        User getUser();

        void setName(String name, Listener listener);

        void setAge(int age, Listener listener);
    }


    private static class User {
        String name;
        int age;
    }

}
