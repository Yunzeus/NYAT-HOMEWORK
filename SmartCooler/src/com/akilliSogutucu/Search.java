package com.smartCooler;

public class Searchs {

    private static final int sure=100;

    public static void wait(int sure){
        try
        {
            Thread.sleep(sure);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void wait(){
        try
        {
            Thread.sleep(sure);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
