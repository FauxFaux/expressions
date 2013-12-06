package uk.co.benjiweber.expressions;

import org.junit.Test;

public class TryRun {
    @Test
    public void test() {
         new X().tryRun(X::yellow, "hello for X");
         new Y().tryRun(X::yellow, "hello for Y");
        new X2().tryRun(X::yellow, "hello for X2");
    }

    static interface OneArg<T, U> {
        void apply(T t, U param);
    }

    static interface Lots {
        default <T extends Lots, U> void tryRun(OneArg<T, U> apply, U param) {
            try {
                apply.apply((T) this, param);
            } catch (ClassCastException ex) {
            }
        }
    }

    static class X implements Lots {
        void yellow(String y) {
            System.out.println("in X: " + y);
        }
    }

    static class Y implements Lots {
    }

    static class X2 extends X {
        void yellow(String y) {
            System.out.println("in X2: " + y);
        }
    }
}