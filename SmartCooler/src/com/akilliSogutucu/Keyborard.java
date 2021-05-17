package com.smartCooler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyborard implements IKeyborard{

    @Override
    public String stringDataTake() {
        Scanner login = new Scanner(System.in);
        return login.nextLine();
    }

    @Override
    public int intDataTake() {
        Scanner login = new Scanner(System.in);

        int number = 0;
        boolean incorrectEntry;

        do {
            if (login.hasNextInt()){
                number = login.nextInt();
                incorrectEntry = false;
            }
            else {
                System.out.println("\nHATALI GİRİŞ YAPTINIZ");
                System.out.println("Lütfen sayı giriniz:");
                login.next();
                incorrectEntry = true;
            }
        } while (incorrectEntry);
        return number;
    }
}
