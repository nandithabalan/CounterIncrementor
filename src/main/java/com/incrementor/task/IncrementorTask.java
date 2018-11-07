package com.incrementor.task;


import com.incrementor.Counter;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class IncrementorTask implements Callable<Integer>, Incrementor {

    private static Logger log = Logger.getLogger(IncrementorTask.class.getName());


    //shared variable across all threads
    private Counter counter;

    public IncrementorTask(Counter input){
        this.counter = input;
    }



    @Override
    public Integer increment() {
        int count = counter.getCounter();
        while(true){
            count =  counter.incrementAndGet();
            if(count >= 1000000){
                break;
            }
            log.info("Count:"+count);
        }
        return count;
    }

    @Override
    public Integer call() {
        return increment();
    }





}
