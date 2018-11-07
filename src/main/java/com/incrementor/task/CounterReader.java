package com.incrementor.task;

import com.incrementor.CounterIncrementor;

import java.util.concurrent.BlockingQueue;

public class CounterReader implements Runnable {

    private Number count;

    public CounterReader(Number input){
        this.count = input;
    }

    @Override
    public void run() {

            while(true) {
                System.out.println("Reader thread:");

                System.out.println("Reader thread:" + count);



            }


    }
}
