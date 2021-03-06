package com.company;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Proveedor implements Runnable {

    private final Almacen almacen;

    public Proveedor(Almacen almacen){
        this.almacen = almacen;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Random random = new Random();
                Thread.sleep(2000);
                almacen.addStock(random.nextInt(3)+1);
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}
