package DAO;

import org.example.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;


/**
 * The type Bank dao.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BankDAO extends AbstractDAO<Bank> {

    /**
     * Add.
     *
     * @param bank the bank
     */
    @Override
    public void add(Bank bank) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "insert into bank values ('"+ bank.getBank() + "'," + bank.getId() + ")";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("добавили банк:" + bank.toString());
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
    public List<Bank> getAll() {
        Statement statement = null;
        Connection connection = null;
        List<Bank> bankList = new ArrayList<>();
        try{
            String sqlQuery = "select id, bank from bank order by id";
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                Bank bank = new Bank();
                bank.setId(resultSet.getInt("id"));
                bank.setBank(resultSet.getString("bank"));
                bankList.add(bank);
            }
            System.out.println("банки: " + bankList.toString());

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
        return bankList;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Override
    public Bank getById(int id) {
        PreparedStatement statement = null;
        Connection connection = null;
        Bank bank = new Bank();
        try{
            String sqlQuery = "select id, bank from bank where id =" + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            bank.setId(resultSet.getInt("id"));
            bank.setBank(resultSet.getString("bank"));
            System.out.println("банк: " + bank.toString());
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
        return bank;
    }

    /**
     * Remove.
     *
     * @param bank the bank
     */
    @Override
    public void remove(Bank bank) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "delete from bank where id=" + bank.getId();;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("удалили банк");
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
     * @param bank the bank
     */
    @Override
    public void update(int id, Bank bank) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "update bank set bank ="+ bank.getBank() + "', id =" + bank.getId() + " where id=" + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("обновили банк:" + bank.toString());
        } catch (Exception e) {
            System.out.println("ошибка обновили");
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
