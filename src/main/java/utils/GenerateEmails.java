package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateEmails {
    public static String generateUniqueEmail() {
        String baseName = "Mohammad.Fahel";
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return baseName + timestamp + "@example.com";
    }
}
