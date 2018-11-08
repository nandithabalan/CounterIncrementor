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
        int oldCount;
        boolean isMax = false;
        while(true) {
            boolean isUpdated = false;
            do {
                oldCount = counter.get();
                //log.info("Counter"+counter);
                if (oldCount == 1000000){
                    isMax = true;
                    break;
                }
                isUpdated = counter.compareAndSet(oldCount, oldCount + 1);

            }while(!isUpdated);

            if(isMax){
                break;
            }
        }
        return oldCount;

    }

    @Override
    public Integer call() {
        return increment();
    }
}
