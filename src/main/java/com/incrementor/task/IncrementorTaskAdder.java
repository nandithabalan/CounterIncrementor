package com.incrementor.task;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

public class IncrementorTaskAdder implements Incrementor, Callable<Integer> {
    private static Logger log = Logger.getLogger(IncrementorTaskAdder.class.getName());

    private LongAdder counter;

    public IncrementorTaskAdder(LongAdder count) {
        this.counter = count;
    }

    public Integer increment(){
        int count = counter.intValue();
        while(true) {
           counter.increment();
           count = counter.intValue();
           if(count >= 1000000){
               break;
           }
           log.info("Count"+count);
        }
        return count;

    }

    public Integer call() {
        return increment();

    }

}
