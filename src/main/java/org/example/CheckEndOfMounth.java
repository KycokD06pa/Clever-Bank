package org.example;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * The type Check end of mounth.
 */
public class CheckEndOfMounth implements Runnable{


    /**
     * The constant time.
     */
    public static volatile boolean time = false;

    /**
     * Run.
     */
    @Override
    public void run() {

            YearMonth month = YearMonth.now();
            if (LocalDate.now().equals(month.atEndOfMonth())) {
                System.out.println("правда");
                if(AccrualOfInterest.block == false) {
                    time = true;
                }
            } else {
                AccrualOfInterest.block = false;
                System.out.println("нет");
            }
    }
}
