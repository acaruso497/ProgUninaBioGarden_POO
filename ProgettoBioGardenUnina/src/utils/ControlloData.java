package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ControlloData {
	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isDataValida(String dataStr) {
        try {
            LocalDate.parse(dataStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isPrimaDataMinore(String dataStr1, String dataStr2) {
        try {
            LocalDate data1 = LocalDate.parse(dataStr1, formatter);
            LocalDate data2 = LocalDate.parse(dataStr2, formatter);
            return data1.isBefore(data2);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
