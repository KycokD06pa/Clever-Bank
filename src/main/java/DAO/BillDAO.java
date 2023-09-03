package DAO;

import org.example.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


/**
 * The type Bill dao.
 */
@Data
@EqualsAndHashCode(callSuper=false)

public class BillDAO extends AbstractDAO<Bill>{

    /**
     * Add.
     *
     * @param bill the bill
     */
    @Override
    public void add(Bill bill) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "insert into bill values ("+ bill.getId() + "," + bill.getClient() + ","
                    + bill.getBank() + "," + bill.getValue()+ ")";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("добавили счет:" + bill.toString());
        } catch (Exception e) {
            System.out.println("ошибка добавления");
            throw new RuntimeException(e);
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @Override
    public List<Bill> getAll() {
        Statement statement = null;
        Connection connection = null;
        List<Bill> billList = new ArrayList<>();
        try{
            String sqlQuery = "select id, client, bank, value from bill order by id";
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getInt("id"));
                bill.setClient(resultSet.getInt("client"));
                bill.setBank(resultSet.getInt("bank"));
                bill.setValue(resultSet.getDouble("value"));
                billList.add(bill);
            }
            System.out.println("счета: " + billList.toString());

        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return billList;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Override
    public Bill getById(int id) {
        PreparedStatement statement = null;
        Connection connection = null;
        Bill bill = new Bill();
        try{
            String sqlQuery = "select id, client, bank, value from bill where id =" + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            bill.setId(resultSet.getInt("id"));
            bill.setClient(resultSet.getInt("client"));
            bill.setBank(resultSet.getInt("bank"));
            bill.setValue(resultSet.getDouble("value"));
            System.out.println("счет: " + bill.toString());
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return bill;
    }

    /**
     * Remove.
     *
     * @param bill the bill
     */
    @Override
    public void remove(Bill bill) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "delete from bill where id=" + bill.getId();;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("удалили счет");
        } catch (Exception e) {
            System.out.println("ошибка удаления");
            throw new RuntimeException(e);
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Update.
     *
     * @param id   the id
     * @param bill the bill
     */
    @Override
    public void update(int id, Bill bill) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "update bill set id = "+ bill.getId() + ",client = " + bill.getClient() + ",bank="
                    + bill.getBank() + ",value=" + bill.getValue()+ " where id = " + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("обновили счет:" + bill.toString());
        } catch (Exception e) {
            System.out.println("ошибка обновления");
            throw new RuntimeException(e);
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
