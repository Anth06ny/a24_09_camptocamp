package org.example.a24_09_camptocamp.exo;

import java.util.concurrent.atomic.AtomicInteger;

public class BallotBoxBean {

    private final AtomicInteger number = new AtomicInteger(0);

    public void add1VoiceAndWait() {
        System.out.println(Thread.currentThread().getName());
        try{Thread.sleep(3000);} catch(Exception e){}
        number.incrementAndGet();
    }

    public int current() {
        return number.get();
    }
}