package com.incrementor.task;

import java.util.concurrent.Callable;

public interface Incrementor extends Callable<Integer>{

    public Integer increment();

}
