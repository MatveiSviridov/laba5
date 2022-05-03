package com.laba5;

public class laba5 {
    public static void main (String[] args) {
        SomeBean sb = (new Injector()).inject(new SomeBean());
        sb.foo();

    }
}
