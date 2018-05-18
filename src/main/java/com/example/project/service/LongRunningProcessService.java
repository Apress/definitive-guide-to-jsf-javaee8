package com.example.project.service;

import java.util.function.Consumer;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class LongRunningProcessService {

    @Asynchronous
    public void asyncSubmit(Consumer<String> callback) {
    	long fiveSecondsLater = System.nanoTime() + (long) 5e9;

    	while (System.nanoTime() < fiveSecondsLater) {
			// Crunch.
		}

    	callback.accept("Long running process is finished!");
    }

}
