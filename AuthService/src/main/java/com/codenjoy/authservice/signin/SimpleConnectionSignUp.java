package com.codenjoy.authservice.signin;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleConnectionSignUp implements ConnectionSignUp {
    private final AtomicLong userIdSequence = new AtomicLong();

    @Override
    public String execute(Connection<?> connection) {
        return Long.toString(userIdSequence.incrementAndGet());
    }
}
