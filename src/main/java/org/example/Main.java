package org.example;

import DAO.BillDAO;
import DAO.ClientDAO;
import DAO.TransactionDAO;

import java.io.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new CheckEndOfMounth(), 0, 30, TimeUnit.SECONDS);
        ScheduledExecutorService scheduler2 = Executors.newSingleThreadScheduledExecutor();
        scheduler2.scheduleAtFixedRate(new AccrualOfInterest(), 0, 30, TimeUnit.SECONDS);

// место для написаня вашего кода
//метод тест для тестового знакомства с программой
        testWork();
//
//

        if(bufferedReader.readLine().equals("0")){
            scheduler.close();
            scheduler2.close();
        }
        System.out.println("over");
        Thread.currentThread().interrupt();
    }


    /**
     * Test work.
     */
    public static void testWork(){
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            System.out.println("Для создания счета в банке введите свою фамилию и инициалы:");
            String str = bufferedReader.readLine();
            List<Client> clientList = new ClientDAO().getAll();
            int idClient = clientList.get(clientList.size()-1).getId() + 1;
            Client client = new Client(idClient, str);
            new ClientDAO().add(client);
            List<Bill> billList = new BillDAO().getAll();
            int id = billList.get(billList.size()-1).getId() + 1;
            System.out.println("ваш счет: " + id );
            Bill bill = new Bill(id,idClient , 1, 0);
            new BillDAO().add(bill);
            System.out.println("Ваш счет создан! Для дальнейшей работы давайте пополним счет");
            String str2 = bufferedReader.readLine();
            double d = Double.parseDouble(str2);
            new TransactionDAO().refill(bill, d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

