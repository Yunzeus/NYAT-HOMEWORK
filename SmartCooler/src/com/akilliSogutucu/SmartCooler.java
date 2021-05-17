package com.smartCooler;

public class SmartCooler {
    IScreen screen;
    IKeyborard keyborard;
    INetworkInterface networkInterface;
    ITemperatureSensor temperatureSensor;
    IPersonRepository personDatabase;
    ITemperatureRepository temperatureDatabase ;

    SmartCooler(){
        screen = new Screen();
        keyborard = new Keyborard();
        networkInterface = new NetworkInterface();
        personDatabase = new PersonRepositoryPostgreSql();
        temperatureDatabase  = new TemperatureRepositoryPostgreSql();
        temperatureSensor = new TemperatureSensor();

        temperatureSensor.attach(temperatureDatabase );
    }

    public void start(){
        screen.openMessage();
        User user;

        Thread run = new Thread(temperatureSensor);
        run.start();

        boolean checkOut = false;
        int option = 0;

        user = screen.userLoginScreen(keyborard);
        boolean userConfirmed = personDatabase.userVerify(user);
        screen.userConfirmedMessage(userConfirmed);
        do {
            if (userConfirmed){
                option = screen.userOptions(keyborard);
                checkOut = userOperations(option);
            }
            else {
                screen.writeMessage("Tekrar denemek ister misiniz? (E/H)");
                boolean repeatTry = keyborard.stringDataTake().equalsIgnoreCase("E");
                if (repeatTry){
                    user = screen.userLoginScreen(keyborard);
                    userConfirmed = personDatabase.userVerify(user);
                }
                else
                    checkOut = true;
            }
        }while (!checkOut);

        screen.userLogOutScreen();
        run.stop();
    }

    private boolean userOperations(int option){
        boolean checkOut = false;

        switch (option){
            case 0:
                checkOut = true;
                break;
            case 1:
                screen.viewTemperature(networkInterface);
                break;
            case 2:
                screen.turnCooler(networkInterface);
                break;
            case 3:
                screen.closeCooler(networkInterface);
                break;
        }

        return checkOut;
    }

}


