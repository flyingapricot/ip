package orange.parser;
import orange.exception.OrangeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static orange.exception.ExceptionType.*;


//Converts strings into date objects
//and date objects into strings
public class DateParser {
        public static LocalDateTime getDateTimeObject(String timeAndDate) throws OrangeException {
            //Accepts date in the format of yyyy-mm-dd time
            // Regex for matching date and time in "yyyy-mm-dd HH:mm" format
            String dateTimePattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";

            Pattern pattern = Pattern.compile(dateTimePattern);
            LocalDateTime dateTime = null;

            // Check if the input matches the pattern
            Matcher matcher = pattern.matcher(timeAndDate);
            if(!matcher.matches()) throw new OrangeException(INCORRECT_DATE_AND_TIME_FORMAT);

            try {
                // Attempt to parse the input as a date-time in "yyyy-MM-dd HH:mm" format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                dateTime = LocalDateTime.parse(timeAndDate, formatter);
            } catch (DateTimeParseException e) {
                throw new OrangeException(INVALID_DATE_OR_TIME);
            }
                return dateTime;
        }

        public static String getDifferentFormat(LocalDateTime dateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

            // Convert LocalDateTime to String using the formatter
            return dateTime.format(formatter);
        }


        // Convert String To LocalDate
        public static LocalDate getDateObject(String givenDate) throws OrangeException {
            // Define the format you expect the date to be in
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format as needed

            try {
                // Try parsing the date string using the formatter
                return LocalDate.parse(givenDate, formatter);
            } catch (DateTimeParseException e) {
                // Handle invalid date format
                throw new OrangeException(INVALID_DATE);
            }
        }

        //Convert LocalDate to String
        public static String getStringFromLocalDate(LocalDate localDate) {
            // Define the format pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convert LocalDate to String using the formatter
            return localDate.format(formatter);
        }
}
