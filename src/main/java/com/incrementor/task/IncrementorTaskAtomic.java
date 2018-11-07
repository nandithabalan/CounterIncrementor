package com.incrementor.task;


import com.incrementor.CounterIncrementor;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class IncrementorTaskAtomic  implements Callable<Integer>,  Incrementor {

    private static Logger log = Logger.getLogger(IncrementorTaskAtomic.class.getName());

    private AtomicInteger counter;

    public IncrementorTaskAtomic(AtomicInteger input) {
        this.counter = input;
    }



    @Override
    public Integer increment() {
        while(true) {
            final int oldCount = counter.get();

            if(oldCount == 1000000)
                break;
            counter.compareAndSet(oldCount, oldCount + 1);
            //log.info("Counter"+counter);
        }
        return counter.get();
    }

    @Override
    public Integer call() {
        return increment();
    }
}
