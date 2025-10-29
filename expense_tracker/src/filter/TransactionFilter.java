package filter;

import java.util.List;
import model.Transaction;

/**
 * Strategy interface for filtering a list of transactions.
 */
public interface TransactionFilter {
  /**
   * Filters the given transactions according to a strategy.
   * @param input list of transactions to filter
   * @return filtered list (never null)
   */
  List<Transaction> filter(List<Transaction> input);
}


