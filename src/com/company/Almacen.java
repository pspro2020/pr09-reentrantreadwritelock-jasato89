package com.company;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {

    private ArrayList<Integer> products = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Almacen(ArrayList<Integer> products) {
        this.products = products;
    }

    public void addStock(int productID) {
        writeLock.lock();
        try {
            products.add(productID);
            System.out.printf("%s - Producto %d añadido\n", LocalTime.now().format(dateFormat), productID);
        } finally {
            writeLock.unlock();
        }
    }

    public void checkStock (int productID) {
        readLock.lock();
        try {

            System.out.printf("%s - Producto %d - Stock: %d\n", LocalTime.now().format(dateFormat), productID, totalStock(productID));


        } finally {
            readLock.unlock();
        }

    }

    private int totalStock(int productID) {
        int totalStockOf = 0;
        for (int product : products) {
            if (product == productID) {
                totalStockOf++;
            }
        }
            return totalStockOf;

        }
}
