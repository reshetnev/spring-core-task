package com.epam.reshetnev.spring.core.util;

import java.util.Random;

public class Generator {

    public static Integer generateId() {
        Random r = new Random();
        return r.ints(1, Integer.MAX_VALUE).limit(1).findFirst().getAsInt();
    }
}
