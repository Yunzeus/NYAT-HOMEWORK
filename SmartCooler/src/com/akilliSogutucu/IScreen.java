package com.smartCooler;

public interface IScreen {
    public void openMessage();
    public User userLoginScreen(IKeyborard keyborard);
    public void userConfirmedMessage(boolean confirmed);
    public void userLogOutScreen();
    public int userOptions(IKeyborard keyborard);
    public void writeMessage(String mesaj);
    public void viewTemperature(INetworkInterface networkInterface);
    public void turnCooler(INetworkInterface networkInterface);
    public void closeCooler(INetworkInterface networkInterface);
}
