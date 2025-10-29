package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
import filter.TransactionFilter;
import filter.CategoryFilter;
import filter.AmountFilter;
/**
 * Controller that mediates between the View and the Model.
 *
 * <p>Responsible for orchestrating user actions, validating inputs, updating the
 * model, and requesting the view to refresh.</p>
 */
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private TransactionFilter activeFilter;

  /**
   * Creates a controller with the given model and view.
   * @param model the domain model storing transactions
   * @param view the Swing view for displaying and accepting input
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  /**
   * Refresh the view using transactions currently in the model.
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();
    if (activeFilter != null) {
      transactions = activeFilter.filter(transactions);
    }

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * Attempts to add a new transaction to the model.
   * @param amount the transaction amount
   * @param category the transaction category (e.g., food, travel)
   * @return true if added successfully; false if validation fails
   */
  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    refresh();
    return true;
  }
  
  /**
   * Applies the requested filter. Only one filter can be active at a time.
   * @param filterType one of "none", "amount", "category"
   * @param rawValue the raw input value for the filter (ignored for none)
   * @return true if filter applied (or cleared) successfully; false if validation failed
   */
  public boolean applyFilter(String filterType, String rawValue) {
    if (filterType == null || filterType.equalsIgnoreCase("none")) {
      activeFilter = null;
      refresh();
      return true;
    }
    if (filterType.equalsIgnoreCase("amount")) {
      if (rawValue == null || rawValue.trim().isEmpty()) {
        return false;
      }
      try {
        double amount = Double.parseDouble(rawValue.trim());
        if (!InputValidation.isValidAmount(amount)) {
          return false;
        }
        activeFilter = new AmountFilter(amount);
        refresh();
        return true;
      } catch (NumberFormatException nfe) {
        return false;
      }
    }
    if (filterType.equalsIgnoreCase("category")) {
      if (!InputValidation.isValidCategory(rawValue)) {
        return false;
      }
      activeFilter = new CategoryFilter(rawValue.trim());
      refresh();
      return true;
    }
    return false;
  }
  
  // Other controller methods
}


