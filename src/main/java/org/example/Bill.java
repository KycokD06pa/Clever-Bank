package org.example;

import lombok.*;

/**
 * The type Bill.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private int id;
    private int client;
    private int bank;
    private double value;
}
