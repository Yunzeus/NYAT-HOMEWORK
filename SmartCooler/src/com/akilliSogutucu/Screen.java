package com.smartCooler;

public class Screen implements IScreen {

    @Override
    public void openMessage() {
        System.out.println("Akıllı soğutucuya hoşgeldiniz");
        System.out.println("-----------------------------\n");
    }

    @Override
    public User userLoginScreen(IKeyborard keyborard) {
        System.out.println("Kullanıcı adını giriniz:");
        String userName = keyborard.stringDataTake();

        System.out.println("Şifre giriniz:");
        String password = keyborard.stringDataTake();

        return new User.Builder(userName, password).build();
    }

    @Override
    public void userConfirmedMessage(boolean confirmed) {
        this.writeMessage("");
        if (confirmed) this.writeMessage("--- Hoşgeldiniz! ---");
        else this.writeMessage("--- User sistemde kayitli değil!!! ---");
        this.writeMessage("");
    }

    @Override
    public void userLogOutScreen() {
        this.writeMessage("\n--- Hoşçakalın, yine bekleriz ---\n");
    }

    @Override
    public int userOptions(IKeyborard keyborard) {
        boolean optionWrong = false;
        int option;

        do {
            this.writeMessage("--- Kullanıcı Seçenekleri ---");
            this.writeMessage("1 - Sıcaklığı görüntüle");
            this.writeMessage("2 - Soğutucuyu aç");
            this.writeMessage("3 - Soğutucuyu kapat");
            this.writeMessage("0 - Çıkış");

            option = keyborard.intDataTake();

            if (option < 0 || option > 3) {
                this.writeMessage("\nHATALI BİR SEÇENEK GİRDİNİZ");
                this.writeMessage("Lütfen aşağıdaki seçeneklerden birini giriniz:");
                optionWrong = true;
            }
        } while (optionWrong);

        return option;
    }

    @Override
    public void writeMessage(String mesaj) {
        System.out.println(mesaj);
    }

    @Override
    public void viewTemperature(INetworkInterface networkInterface) {
        int temperature = networkInterface.temperatureOku();
        this.writeMessage("\n--- Son okunan sıcaklık : " + temperature +  " C ---\n");
    }

    @Override
    public void turnCooler(INetworkInterface networkInterface) {
        networkInterface.turnCooler();
        this.writeMessage("\n--- Soğutucu açıldı ---\n");
    }

    @Override
    public void closeCooler(INetworkInterface networkInterface) {
        networkInterface.closeCooler();
        this.writeMessage("\n--- Soğutucu kapatıldı ---\n");
    }
}
