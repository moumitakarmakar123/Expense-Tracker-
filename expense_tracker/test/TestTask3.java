import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;

public class TestTask3 {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

  private double totalFromModel() {
    double total = 0.0;
    for (Transaction t : model.getTransactions()) {
      total += t.getAmount();
    }
    return total;
  }

  // 1) Add Valid Transaction
  @Test
  public void addValidTransaction_updatesModelAndTotalAndTable() {
    assertEquals(0, model.getTransactions().size());

    boolean ok = controller.addTransaction(50.00, "food");
    assertTrue(ok);

    assertEquals(1, model.getTransactions().size());
    assertEquals(50.00, totalFromModel(), 0.001);

    // Table should have 2 rows: 1 data + 1 total row
    assertEquals(2, view.getTableModel().getRowCount());
    assertEquals("Total", view.getTableModel().getValueAt(1, 0));
    assertEquals(50.00, (double) view.getTableModel().getValueAt(1, 3), 0.001);
  }

  // 2) Input Validation for Amount
  @Test
  public void invalidAmount_doesNotChangeModelOrTotal() {
    // Prepopulate with a valid transaction to ensure total remains unchanged
    assertTrue(controller.addTransaction(25.0, "food"));
    assertEquals(1, model.getTransactions().size());
    double before = totalFromModel();

    // Invalid amounts: 0, negative, > 1000
    assertFalse(controller.addTransaction(0.0, "food"));
    assertFalse(controller.addTransaction(-5.0, "food"));
    assertFalse(controller.addTransaction(1000.01, "food"));

    assertEquals(1, model.getTransactions().size());
    assertEquals(before, totalFromModel(), 0.001);
  }

  // 3) Input Validation for Category
  @Test
  public void invalidCategory_doesNotChangeModelOrTotal() {
    assertTrue(controller.addTransaction(10.0, "food"));
    assertEquals(1, model.getTransactions().size());
    double before = totalFromModel();

    // Invalid categories: null/empty/non-alpha/not in allowed list
    assertFalse(controller.addTransaction(5.0, ""));
    assertFalse(controller.addTransaction(5.0, "   "));
    assertFalse(controller.addTransaction(5.0, "123"));
    assertFalse(controller.addTransaction(5.0, "invalidcat"));

    assertEquals(1, model.getTransactions().size());
    assertEquals(before, totalFromModel(), 0.001);
  }

  // 4) Filter by Amount
  @Test
  public void filterByAmount_returnsOnlyMatchingAmounts() {
    assertTrue(controller.addTransaction(10.0, "food"));
    assertTrue(controller.addTransaction(20.0, "travel"));
    assertTrue(controller.addTransaction(10.0, "bills"));

    // Apply amount filter = 10
    boolean ok = controller.applyFilter("amount", "10");
    assertTrue(ok);

    // View table: rows = 2 data (amount 10) + 1 total row
    assertEquals(3, view.getTableModel().getRowCount());
    // First data row amount column index 1
    assertEquals(10.0, (double) view.getTableModel().getValueAt(0, 1), 0.001);
    assertEquals(10.0, (double) view.getTableModel().getValueAt(1, 1), 0.001);
    assertEquals("Total", view.getTableModel().getValueAt(2, 0));
    // Total should be 20 (two 10s)
    assertEquals(20.0, (double) view.getTableModel().getValueAt(2, 3), 0.001);
  }

  // 5) Filter by Category
  @Test
  public void filterByCategory_returnsOnlyMatchingCategory() {
    assertTrue(controller.addTransaction(5.0, "food"));
    assertTrue(controller.addTransaction(7.0, "travel"));
    assertTrue(controller.addTransaction(9.0, "food"));

    boolean ok = controller.applyFilter("category", "food");
    assertTrue(ok);

    // Expect 2 food rows + total row
    assertEquals(3, view.getTableModel().getRowCount());
    assertEquals("food", (String) view.getTableModel().getValueAt(0, 2));
    assertEquals("food", (String) view.getTableModel().getValueAt(1, 2));
    assertEquals("Total", view.getTableModel().getValueAt(2, 0));
    // Total should be 14 (5 + 9)
    assertEquals(14.0, (double) view.getTableModel().getValueAt(2, 3), 0.001);
  }
}


