/*
    MethodTimer.java
    Method Timing Service Component
    Reference Number: 2
 */

package com.BillRift.timing;

import java.util.concurrent.TimeUnit;

public class MethodTimer {
    private long startTime;

    public MethodTimer() {
        startTime = System.nanoTime();
    }

    public boolean durationWithinLimit(long seconds) {
        long curTime = System.nanoTime();
        long duration = TimeUnit.SECONDS.convert(curTime - startTime, TimeUnit.NANOSECONDS);
        return duration <= seconds;
    }
}
