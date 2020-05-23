package classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Farmer {

    private String name;
    private int age;

    
    /**
     * Sets the farmers name to the given name if it is appropriate.
     * Return true if its is appropriate, otherwise return false.
     * @param newName String
     * @return - Boolean
     */
    public Boolean setName(String newName) {

      // If the name is out of the allowed range
      if (newName.length() < 3 || newName.length() > 15) {
        return false;
      }

      Pattern pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(newName);

      // If the name contains illegal characters
      if (matcher.find()) {
        return false;
      }

      name = newName;
      return true;
    }


    /**
     * Sets the age of the Farmer if a valid age is given.
     * @param newAge - int
     * @return - Boolean
     */
    public Boolean setAge(int newAge) {

      // If the input age is negative or more than the oldest person ever -> False
      if (newAge < 0 || newAge > 122) {
        return false;
      } else {
          age = newAge;
        return true;
      }
    }

    /**
     * Getter for farmer name
     * @return - String
     */
    public String getName() {
      return name;
    }

    /**
     * Getter for farmer age
     * @return - int
     */
    public int getAge() {
      return age;
    }

}
