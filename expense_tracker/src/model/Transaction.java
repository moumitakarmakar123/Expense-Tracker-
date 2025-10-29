package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Immutable value object representing a single expense transaction.
 */
public class Transaction {

  private final double amount;
  private final String category;
  private final String timestamp;

  /**
   * Creates a new transaction with a generated timestamp.
   * @param amount transaction amount
   * @param category transaction category
   */
  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * @return the transaction amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * @return the transaction category
   */
  public String getCategory() {
    return category;
  }
  
  /**
   * @return formatted timestamp of creation (dd-MM-yyyy HH:mm)
   */
  public String getTimestamp() {
    return timestamp;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}
