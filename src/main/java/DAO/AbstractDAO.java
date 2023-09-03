package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import lombok.*;


/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
@NoArgsConstructor
public abstract class AbstractDAO<T> {
    /**
     * Get connection connection.
     *
     * @return the connection
     */
    protected Connection getConnection(){
        String connectionString = "jdbc:postgresql://localhost:5432/clever";
        String userString = "postgres";
        String passwordString = "1234";
        Connection dbConnection = null;
        try{
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(connectionString,userString, passwordString);
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не загрузился");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Коннекшен не создан");
            e.printStackTrace();
        }
        return dbConnection;
    }

    /**
     * Add.
     *
     * @param t the t
     */
    public abstract void add(T t);

    /**
     * Gets all.
     *
     * @return the all
     */
    public abstract List<T> getAll();

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public abstract T getById(int id);

    /**
     * Remove.
     *
     * @param t the t
     */
    public abstract void remove(T t);

    /**
     * Update.
     *
     * @param id the id
     * @param t  the t
     */
    public abstract void update(int id, T t);

}
