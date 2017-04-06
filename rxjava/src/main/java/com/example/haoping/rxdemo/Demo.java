package com.example.haoping.rxdemo;

/**
 * Created by haoping on 17/4/1.
 * TODO
 */
public class Demo {

    public static void main(String[] args) {
        new AsyncJob1() {
            @Override
            void onStart() {
                System.out.print("AsyncJob1-onStart\n");
            }
        }.onStart();

        new AsyncJob2() {
            @Override
            void onStart() {
                System.out.print("AsyncJob2-onStart1\n");
            }
        }.onStart();

        new AsyncJob2() {
            @Override
            void onStart() {
                System.out.print("AsyncJob2-onStart2\n");
            }
        }.getAsyncJob();

        new AsyncJob2() {
            @Override
            void onStart() {
                System.out.print("AsyncJob2-onStart3\n");
            }
        }.getAsyncJob().onStart();

        new AsyncJob3() {
            @Override
            void onStart() {
                System.out.print("AsyncJob3-onStart1\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob3-onStop1\n");
            }
        }.onStart();

        new AsyncJob3() {
            @Override
            void onStart() {
                System.out.print("AsyncJob3-onStart1\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob3-onStop1\n");
            }
        }.onStop();

        new AsyncJob4() {
            @Override
            void onStart() {
                System.out.print("AsyncJob4-onStart1\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob4-onStop1\n");
            }
        }.onStart();

        new AsyncJob4() {
            @Override
            void onStart() {
                System.out.print("AsyncJob4-onStart2\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob4-onStop2\n");
            }
        }.onStop();

        new AsyncJob4() {
            @Override
            void onStart() {
                System.out.print("AsyncJob4-onStart3\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob4-onStop3\n");
            }
        }.getAsyncJob4().onStart();

        new AsyncJob4() {
            @Override
            void onStart() {
                System.out.print("AsyncJob4-onStart4\n");
            }

            @Override
            void onStop() {
                System.out.print("AsyncJob4-onStop4\n");
            }
        }.getAsyncJob4().onStop();
    }

    static abstract class AsyncJob1{
        abstract void onStart();
    }

    static abstract class AsyncJob2{
        abstract void onStart();
        AsyncJob2 getAsyncJob(){
            return new AsyncJob2() {
                @Override
                void onStart() {
                    System.out.print("AsyncJob2-getAsyncJob-onStart\n");
                }
            };
        }
    }

    static abstract class AsyncJob3{
        abstract void onStart();
        abstract void onStop();
    }

    static abstract class AsyncJob4{
        abstract void onStart();
        abstract void onStop();
        AsyncJob4 getAsyncJob4(){
            return new AsyncJob4() {
                @Override
                void onStart() {
                    System.out.print("AsyncJob4-getAsyncJob-onStart\n");
                }

                @Override
                void onStop() {
                    System.out.print("AsyncJob4-getAsyncJob-onStop\n");
                }
            };
        }
    }
}
