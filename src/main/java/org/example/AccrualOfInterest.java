package org.example;

import DAO.BillDAO;
import DAO.TransactionDAO;

import java.io.IOException;
import java.util.List;


/**
 * The type Accrual of interest.
 */
public class AccrualOfInterest implements Runnable{

    /**
     * The constant block.
     */
    public static volatile boolean block = false;

    /**
     * Run.
     */
    @Override
    public void run() {
        System.out.println("run");
        if(block != false || !CheckEndOfMounth.time ){return;}
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String percent = System.getProperty("percent");
        double temp = Double.parseDouble(percent) / 100;
        List<Bill> billList = new BillDAO().getAll();
        for(Bill b: billList){
            new TransactionDAO().refill(b,b.getValue()*temp);
        }
        CheckEndOfMounth.time = false;
        block = true;
    }
}
