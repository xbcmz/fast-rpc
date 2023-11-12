package com.fast.annotation;

import lombok.SneakyThrows;

class TestSneakyThrows {
    @SneakyThrows
    public void doSomething() {
        throw new Exception("Something went wrong");
    }


    /**
     * @SneakyThrows注解的作用是在方法中自动地将受查异常转换为非受查异常（RuntimeException或者其子类）并抛出
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        TestSneakyThrows testSneakyThrows = new TestSneakyThrows();
        testSneakyThrows.doSomething();
    }
}