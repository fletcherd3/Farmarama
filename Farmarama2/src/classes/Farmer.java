package classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Farmer {

    private String name;
    private int age;

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

    public Boolean setAge(int newAge) {

      // If the input age is negative or more than the oldest person ever -> False
      if (newAge < 0 || newAge > 122) {
        return false;
      } else {
        return true;
      }
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

}
