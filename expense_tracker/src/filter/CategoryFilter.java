package filter;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

/**
 * Filters transactions by a specific category (case-insensitive).
 */
public class CategoryFilter implements TransactionFilter {

  private final String category;

  public CategoryFilter(String category) {
    this.category = category == null ? "" : category.trim();
  }

  @Override
  public List<Transaction> filter(List<Transaction> input) {
    List<Transaction> result = new ArrayList<>();
    if (input == null) {
      return result;
    }
    for (Transaction t : input) {
      if (t != null && t.getCategory() != null && t.getCategory().equalsIgnoreCase(category)) {
        result.add(t);
      }
    }
    return result;
  }
}


