import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;


public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle filter apply button
    view.getApplyFilterBtn().addActionListener(e -> {
      String selected = (String) view.getFilterTypeCombo().getSelectedItem();
      String filterType = selected == null ? "none" : selected.toLowerCase();
      String rawValue = null;
      if ("amount".equals(filterType)) {
        String text = view.getFilterAmountField().getText();
        rawValue = text == null ? "" : text;
      } else if ("category".equals(filterType)) {
        rawValue = view.getFilterCategoryField().getText();
      }
      boolean ok = controller.applyFilter(filterType, rawValue);
      if (!ok) {
        JOptionPane.showMessageDialog(view, "Invalid filter input");
        view.toFront();
      }
    });

  }

}
