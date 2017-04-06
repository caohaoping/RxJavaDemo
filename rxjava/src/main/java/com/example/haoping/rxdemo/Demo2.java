package com.example.haoping.rxdemo;

/**
 * Created by haoping on 17/4/1.
 * TODO
 */
public class Demo2 {
    public static void main(String[] args){
        new Cat() {
            @Override
            void eat() {
                System.out.print("eat1\n");
            }
        }.eat();

        new Cat() {
            @Override
            void eat() {
                System.out.print("eat2\n");
            }
        }.getCat().eat();

        new Dog() {
            @Override
            void eat() {
                System.out.print("eat3\n");
            }
        }.getDog().eat();
    }

    abstract static class Cat{
        abstract void eat();
        Cat getCat(){
            return new Cat() {
                @Override
                void eat() {
                    System.out.print("eat\n");
                }
            };
        }
    }

    abstract static class Dog{
        abstract void eat();
        Dog getDog(){
            final Dog source = this;
            return new Dog() {
                @Override
                void eat() {
                    source.eat();
                }
            };
        }
    }
}
