package DAO;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.Bank;
import org.example.Bill;
import org.example.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Transaction dao.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TransactionDAO extends AbstractDAO<Transaction>{


    /**
     * Add.
     *
     * @param transaction the transaction
     */
    @Override
    public void add(Transaction transaction) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "insert into transaction values ("+ transaction.getId() + "," + transaction.getType() +","
                    +transaction.getSender()+","+transaction.getReceiver()+","+transaction.getValue()+",'"+transaction.getDate()+"','"+ transaction.getTime()+"')";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("добавили транзакцию:" + transaction.toString());
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
    public List<Transaction> getAll() {
        Statement statement = null;
        Connection connection = null;
        List<Transaction> transactionList = new ArrayList<>();
        try{
            String sqlQuery = "select id, type, sender, receiver, value, date, time from transaction order by id";
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setType(resultSet.getInt("type"));
                transaction.setSender(resultSet.getInt("sender"));
                transaction.setReceiver(resultSet.getInt("receiver"));
                transaction.setValue(resultSet.getDouble("value"));
                transaction.setDate(LocalDate.parse(resultSet.getString("date")));
                transaction.setTime(LocalTime.parse(resultSet.getString("time")));
                transactionList.add(transaction);
            }
            System.out.println("транзакция: " + transactionList.toString());

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
        return transactionList;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Override
    public Transaction getById(int id) {
        Statement statement = null;
        Connection connection = null;
        Transaction transaction = new Transaction();
        try{
            String sqlQuery = "select id, type, sender, receiver, value, date, time from transaction where id =" + id;
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            resultSet.next();
            transaction.setId(resultSet.getInt("id"));
            transaction.setType(resultSet.getInt("type"));
            transaction.setSender(resultSet.getInt("sender"));
            transaction.setReceiver(resultSet.getInt("receiver"));
            transaction.setValue(resultSet.getDouble("value"));
            transaction.setDate(LocalDate.parse(resultSet.getString("date")));
            transaction.setTime(LocalTime.parse(resultSet.getString("time")));

            System.out.println("транзакция: " + transaction.toString());

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
        return transaction;
    }

    /**
     * Remove.
     *
     * @param transaction the transaction
     */
    @Override
    public void remove(Transaction transaction) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "delete from transaction where id=" + transaction.getId();;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("удалили транзакцию");
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
     * @param id          the id
     * @param transaction the transaction
     */
    @Override
    public void update(int id, Transaction transaction) {
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "update transaction set id="+ transaction.getId() + ",type=" + transaction.getType() +",sender="
                    +transaction.getSender()+",receiver="+transaction.getReceiver()+",value="+transaction.getValue()
                    +",date='"+transaction.getDate()+"',time='"+ transaction.getTime()+"' where id =" + id;
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            System.out.println("добавили транзакцию:" + transaction.toString());
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
     * Withdrawals.
     *
     * @param bill  the bill
     * @param value the value
     */
    public void withdrawals(Bill bill, double value){
        if(bill.getValue() < value){
            System.out.println("слишком мало средств");
            return;
        }

        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "update bill set value = value - " + value + " where id =" + bill.getId();
            String sqlQueryForId = "select max(id) from transaction";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlQueryForId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int i = resultSet.getInt(1);
            String sqlQueryForTransaction = "insert into transaction values  ("+ (i + 1)+", 1,"
                    + bill.getId() +"," + 0 +"," + value+",'" + LocalDate.now() + "','" + LocalTime.now() + "')";
            statement = connection.prepareStatement(sqlQueryForTransaction);
            statement.executeUpdate();


            DateTimeFormatter formatterForTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            PdfWriter pdfWriter = new PdfWriter("src//main//checks//check"+(i+1)+".pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            Table table = new Table(2);
            table.setMarginTop(20);
            table.setBorder(new DashedBorder(1));
            table.setWidth(250);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(new Cell(1, 2).add(new Paragraph("Bank check")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Receipt:")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(Integer.toString((i+1)))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(LocalDate.now().toString())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(formatterForTime.format(LocalTime.now()))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Transaction type: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("withdrawals")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Bank: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            Bank bank = new BankDAO().getById(bill.getBank());
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bank.getBank().toString())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Account: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bill.getId()+ "")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Value: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(value+ " BYN")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

            document.add(table);


            document.close();

            System.out.println("со счета было снято " + value);
        } catch (Exception e) {
            System.out.println("ошибка снятия");
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
     * Refill.
     *
     * @param bill  the bill
     * @param value the value
     *
     */
    public  void refill(Bill bill, double value){
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQuery = "update bill set value = value + " + value + " where id =" + bill.getId();
            String sqlQueryForId = "select max(id) from transaction";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlQueryForId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int i = resultSet.getInt(1);
            String sqlQueryForTransaction = "insert into transaction values  ("+ (i + 1)+", 2,"
                    + bill.getId() +"," + 0 +"," + value+",'" + LocalDate.now() + "','" + LocalTime.now() + "')";
            statement = connection.prepareStatement(sqlQueryForTransaction);
            statement.executeUpdate();
            DateTimeFormatter formatterForTime = DateTimeFormatter.ofPattern("HH:mm:ss");

            PdfWriter pdfWriter = new PdfWriter("src//main//checks//check"+(i+1)+".pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            Table table = new Table(2);
            table.setMarginTop(20);
            table.setBorder(new DashedBorder(1));
            table.setWidth(250);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(new Cell(1, 2).add(new Paragraph("Bank check")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Receipt:")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(Integer.toString((i+1)))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(LocalDate.now().toString())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(formatterForTime.format(LocalTime.now()))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Transaction type: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("refill")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Bank: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            Bank bank = new BankDAO().getById(bill.getBank());
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bank.getBank().toString())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Account: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bill.getId()+ "")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Value: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(value+ " BYN")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

            document.add(table);


            document.close();


            System.out.println("счет был пополнен на " + value);
        } catch (Exception e) {
            System.out.println("ошибка снятия");
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
     * Transaction.
     *
     * @param billSender   the bill sender
     * @param billReceiver the bill receiver
     * @param value        the value
     */
    public void transaction(Bill billSender, Bill billReceiver, double value){
        if(billSender.getValue() < value){
            System.out.println("слишком мало средств");
            return;
        }
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            String sqlQueryFrom = "update bill set value = value - " + value+ " where id =" + billSender.getId();
            String sqlQueryTo = "update bill set value = value + " + value+ " where id =" + billReceiver.getId();
            String sqlQueryForId = "select max(id) from transaction";
            connection = getConnection();
            statement = connection.prepareStatement(sqlQueryFrom);
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlQueryTo);
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlQueryForId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int i = resultSet.getInt(1);
            String sqlQueryForTransaction = "insert into transaction values  ("+ (i + 1)+", 3,"
                    + billSender.getId() +"," +  billReceiver.getId() +"," + value+",'"
                    + LocalDate.now() + "','" + LocalTime.now() + "')";
            statement = connection.prepareStatement(sqlQueryForTransaction);
            statement.executeUpdate();
            DateTimeFormatter formatterForTime = DateTimeFormatter.ofPattern("HH:mm:ss");

            PdfWriter pdfWriter = new PdfWriter("src//main//checks//check"+(i+1)+".pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            Table table = new Table(2);
            table.setMarginTop(20);
            table.setBorder(new DashedBorder(1));
            table.setWidth(250);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(new Cell(1, 2).add(new Paragraph("Bank check")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Receipt:")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(Integer.toString((i+1)))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(LocalDate.now().toString())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(formatterForTime.format(LocalTime.now()))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Transaction type: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("transaction")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Bank sender: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            Bank bank = new BankDAO().getById(billSender.getBank());
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bank.getBank().toString())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Bank receiver: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            bank = new BankDAO().getById(billReceiver.getBank());
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(bank.getBank().toString())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Account sender: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(billSender.getId()+ "")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Account receiver: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(billReceiver.getId()+ "")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph("Value: ")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,1)
                    .add(new Paragraph(value+ " BYN")).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

            document.add(table);
            document.close();


            System.out.println("был выполнен перевод на " + value);
        } catch (Exception e) {
            System.out.println("ошибка перевода");
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


    private String fixString(String string1, String string2){
        int temp = 32 - string1.length() - string2.length();
        String space = "";
        for(int i = 0; i < temp; i++) {
            space += " ";
        }
        String str = string1 + space + string2;
        System.out.println(str);
        return str;
    }



}
