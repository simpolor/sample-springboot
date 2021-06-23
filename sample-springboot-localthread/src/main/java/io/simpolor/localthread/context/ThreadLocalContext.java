package io.simpolor.localthread.context;

/**
 * 자바 ThreadLocal 클래스는 오직 한 쓰레드에 의해 읽고 쓰여질 수 있는 변수를 생성할 수 있도록 한다.
 * 만일 두 쓰레드가 같은 코드를 실행하고 이 코드가 하나의 ThreadLocal 변수를 참조하더라도,
 * 이 두 쓰레드는 서로의 ThreadLocal 변수를 볼 수 없다. 글자 그대로 쓰레드의 지역변수이다.
 */
public class ThreadLocalContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
