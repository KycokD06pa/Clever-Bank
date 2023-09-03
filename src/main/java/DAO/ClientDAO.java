package DAO;

import org.example.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * The type Client dao.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ClientDAO extends AbstractDAO<Client>{

    /**
     * Add.
     *
     * @param client the client
     */
    @Override
    public void add(Client client)   {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "insert into client values ('"+ client.getClientName() + "'," + client.getId() + ")";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("добавили пользователя:" + client.toString());
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
    public List<Client> getAll() {

        Statement statement = null;
        Connection connection = null;
        List<Client> clientList = new ArrayList<>();
        try{
            String sqlQuery = "select id, client from client order by id";
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setClientName(resultSet.getString("client"));
                clientList.add(client);
            }
            System.out.println("пользователи: " + clientList.toString());

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
        return clientList;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Override
    public Client getById(int id) {
        PreparedStatement statement = null;
        Connection connection = null;
        Client client = new Client();
        try{
            String sqlQuery = "select id, client from client where id =" + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            client.setId(resultSet.getInt("id"));
            client.setClientName(resultSet.getString("client"));
            System.out.println("пользователь: " + client.toString());
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
        return client;
    }

    /**
     * Remove.
     *
     * @param client the client
     */
    @Override
    public void remove(Client client) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "delete from client where id=" + client.getId();;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("удалили пользователя");
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
     * @param id     the id
     * @param client the client
     */
    @Override
    public void update(int id, Client client) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = " update client set client='"+ client.getClientName() + "',id=" + client.getId() + " where id = "+ id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("обновили пользователя:" + client.toString());
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
