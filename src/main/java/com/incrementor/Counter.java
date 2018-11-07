package com.incrementor;

public class Counter  {

    private volatile int counter;

    public int getCounter(){
        synchronized (this) {
            return counter;
        }

    }
    public int incrementAndGet() {
        synchronized (this) {
            ++counter;
        }
        return counter;
    }

    @Override
    public String toString(){
        return String.valueOf(counter);
    }
}
