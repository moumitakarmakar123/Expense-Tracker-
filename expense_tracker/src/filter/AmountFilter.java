package filter;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

/**
 * Filters transactions by exact amount.
 */
public class AmountFilter implements TransactionFilter {

  private final double amount;

  public AmountFilter(double amount) {
    this.amount = amount;
  }

  @Override
  public List<Transaction> filter(List<Transaction> input) {
    List<Transaction> result = new ArrayList<>();
    if (input == null) {
      return result;
    }
    for (Transaction t : input) {
      if (t != null && t.getAmount() == amount) {
        result.add(t);
      }
    }
    return result;
  }
}


