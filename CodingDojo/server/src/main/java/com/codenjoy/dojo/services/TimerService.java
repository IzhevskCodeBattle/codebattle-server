package com.codenjoy.dojo.services;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerService implements Runnable, ApplicationListener {
    private static Logger logger = LoggerFactory.getLogger(TimerService.class);

    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> future;

    @Autowired
    private PlayerService playerService;

    private volatile boolean paused;
    private long period;
    private AtomicBoolean initialized = new AtomicBoolean(false);

    public void init() {
        // to prevent calling twice on child web context initialized events
        if (initialized.compareAndSet(false, true)) {
            logger.info("Initialize timer with period " + period);

            executor = new ScheduledThreadPoolExecutor(1);
            schedule();
        }
    }

    /**
     * Start timer only after context is initialized
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            init();
        }
    }

    private void schedule() {
        future = executor.scheduleAtFixedRate(this, period, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        if (paused) {
            return;
        }

        try {
            playerService.tick();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while processing next step", e);
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public void changePeriod(long period) {
        changeGamePeriod(period, true);
    }

    public void changePeriodGracefully(long period) {
        changeGamePeriod(period, false);
    }

    private void changeGamePeriod(long period, boolean mayInterrupting) {
        this.period = period;

        if (period > 0){
            if (future != null){
                future.cancel(mayInterrupting);
            }
            schedule();
        }
    }

    public long getPeriod() {
        return period;
    }
}
