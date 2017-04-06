package cn.iome.rxjava2.rxDemo;

/**
 * Created by haoping on 17/4/5.
 * TODO
 */
public class RxJack {

    public static void main(String[] args){
        rx1();
    }

    private static void rx1() {
        final User user = new User();
        UserManager um = new UserManager() {
            @Override
            public User getUser() {
                return user;
            }

            @Override
            public void setName(String name) {
                user.name = name;
            }

            @Override
            public void setAge(int age) {
                user.age = age;
            }
        };
        System.out.println(um.getUser().name);

        um.setName("Jane Doe");
        System.out.println(um.getUser().name);
    }

    interface UserManager {
        User getUser();
        void setName(String name);
        void setAge(int age);
    }

    private static class User{
        String name;
        int age;
    }

}
