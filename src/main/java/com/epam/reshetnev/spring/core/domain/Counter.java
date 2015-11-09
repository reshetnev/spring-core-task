package com.epam.reshetnev.spring.core.domain;

import com.epam.reshetnev.spring.core.domain.enums.CounterType;

public class Counter {

    private Integer id;

    private CounterType counterType;

    private String keyName;

    private Integer valueCounter;

    public Counter(Integer id, CounterType counterType, String keyName, Integer valueCounter) {
        this.id = id;
        this.counterType = counterType;
        this.keyName = keyName;
        this.valueCounter = valueCounter;
    }

    public Counter(CounterType counterType, String keyName, Integer valueCounter) {
        this.counterType = counterType;
        this.keyName = keyName;
        this.valueCounter = valueCounter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CounterType getCounterType() {
        return counterType;
    }

    public void setCounterType(CounterType counterType) {
        this.counterType = counterType;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Integer getValueCounter() {
        return valueCounter;
    }

    public void setValueCounter(Integer valueCounter) {
        this.valueCounter = valueCounter;
    }

    @Override
    public String toString() {
        return "Counter [id=" + id + ", counterType=" + counterType + ", keyName=" + keyName + ", valueCounter="
                + valueCounter + "]";
    }

}
