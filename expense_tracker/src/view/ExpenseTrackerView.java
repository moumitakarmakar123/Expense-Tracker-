package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

/**
 * Swing-based view for displaying transactions and capturing user input.
 */
public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JComboBox<String> filterTypeCombo;
  private JFormattedTextField filterAmountField;
  private JTextField filterCategoryField;
  private JButton applyFilterBtn;
  

  /**
   * Constructs the UI and initializes Swing components.
   */
  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Filter controls
    JLabel filterByLabel = new JLabel("Filter:");
    filterTypeCombo = new JComboBox<>(new String[]{"None", "Amount", "Category"});
    NumberFormat filterFormat = NumberFormat.getNumberInstance();
    filterAmountField = new JFormattedTextField(filterFormat);
    filterAmountField.setColumns(8);
    filterCategoryField = new JTextField(8);
    applyFilterBtn = new JButton("Apply Filter");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    JPanel filterPanel = new JPanel();
    filterPanel.add(filterByLabel);
    filterPanel.add(filterTypeCombo);
    filterPanel.add(filterAmountField);
    filterPanel.add(filterCategoryField);
    filterPanel.add(applyFilterBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    JPanel north = new JPanel(new BorderLayout());
    north.add(inputPanel, BorderLayout.NORTH);
    north.add(filterPanel, BorderLayout.SOUTH);
    add(north, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  /**
   * Rebuilds the table contents from the given transactions and appends a total row.
   * @param transactions transactions to render in the table
   */
  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  /**
   * @return button used to add a transaction
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  /**
   * @return the table model backing the transactions table
   */
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    /**
     * @return the transactions table component
     */
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * @return parsed amount from the amount field, or 0 if empty
   */
  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  /**
   * @param amountField field to set as the amount input
   */
  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  /**
   * @return raw text from the category field
   */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /**
   * @param categoryField field to set as the category input
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public JComboBox<String> getFilterTypeCombo() {
    return filterTypeCombo;
  }

  public JFormattedTextField getFilterAmountField() {
    return filterAmountField;
  }

  public JTextField getFilterCategoryField() {
    return filterCategoryField;
  }

  public JButton getApplyFilterBtn() {
    return applyFilterBtn;
  }
}
