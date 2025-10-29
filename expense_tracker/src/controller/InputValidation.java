package controller;

import java.util.Arrays;

/**
 * Utility class providing basic input validation rules used by the controller.
 */
public class InputValidation {

  /**
   * Validates the transaction amount.
   * @param amount amount to validate
   * @return true if amount is within (0, 1000]; false otherwise
   */
  public static boolean isValidAmount(double amount) {
    
    // Check range
    if(amount >1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
  }

  /**
   * Validates the transaction category.
   * @param category category to validate
   * @return true if non-empty letters-only and one of the allowed categories
   */
  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}
