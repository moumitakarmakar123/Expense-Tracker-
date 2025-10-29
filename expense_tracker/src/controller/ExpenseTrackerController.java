package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
/**
 * Controller that mediates between the View and the Model.
 *
 * <p>Responsible for orchestrating user actions, validating inputs, updating the
 * model, and requesting the view to refresh.</p>
 */
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

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
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
}


