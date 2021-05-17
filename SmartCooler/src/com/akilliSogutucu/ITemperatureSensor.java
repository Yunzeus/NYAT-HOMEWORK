package com.smartCooler;

public interface ITemperatureSensor extends ISubject, Runnable{
    public void temperatureRead();
}
