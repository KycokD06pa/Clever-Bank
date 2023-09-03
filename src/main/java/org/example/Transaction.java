package org.example;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int id;
    private int type;
    private int sender;
    private int receiver;
    private double value;
    private LocalDate date;
    private LocalTime time;
}
