package com.smartCooler;

public class NetworkInterface implements INetworkInterface {
    ITemperatureRepository temperatureDatabase  = new TemperatureRepositoryPostgreSql();
    IEyleyici eyleyici = new Eyleyici();

    @Override
    public int temperatureOku() {
        return temperatureDatabase .endTemperatureBring();
    }

    @Override
    public void turnCooler() {
        eyleyici.turnCooler();
    }

    @Override
    public void closeCooler() {
        eyleyici.closeCooler();
    }
}
