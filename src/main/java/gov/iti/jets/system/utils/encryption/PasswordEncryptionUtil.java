package gov.iti.jets.system.utils.encryption;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptionUtil {

    /*
      In your Servlet, get the plain password from the user and call the encryptPassword method to encrypt it.
      String plainPassword = request.getParameter("password");
      String encryptedPassword = PasswordEncryptionUtil.encryptPassword(plainPassword);
    */

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    /*
      String plainPassword = request.getParameter("password");
      String hashedPassword = // retrieve hashed password from database using username
      boolean isValid = PasswordEncryptionUtil.checkPassword(plainPassword, hashedPassword);
      if (isValid) {
       // login successful
      } else {
       // login failed
     }
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
