package orange.parser;


import orange.exception.OrangeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static orange.exception.ExceptionType.INCORRECT_DATE_AND_TIME_FORMAT;
import static orange.exception.ExceptionType.INVALID_DATE_OR_TIME;
import static orange.exception.ExceptionType.INVALID_DATE;
/**
 * Provides utility methods for parsing and formatting date and time values. This class handles
 * conversions between string representations and LocalDate/LocalDateTime objects.
 *
 * @see OrangeException
 */
public class DateParser {
    /**
     * Converts a string representation of a date and time into a LocalDateTime object.
     *
     * @param timeAndDate The date and time string in the format "yyyy-MM-dd HH:mm".
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws OrangeException If the input format is incorrect or if parsing fails.
     */
    public static LocalDateTime getDateTimeObject(String timeAndDate) throws OrangeException {
        String dateTimePattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(dateTimePattern);
        Matcher matcher = pattern.matcher(timeAndDate);
        if (!matcher.matches()) {
            throw new OrangeException(INCORRECT_DATE_AND_TIME_FORMAT);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(timeAndDate, formatter);
        } catch (DateTimeParseException e) {
            throw new OrangeException(INVALID_DATE_OR_TIME);
        }
    }

    /**
     * Converts a LocalDateTime object into a string representation with a different format.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return A formatted date-time string in the format "MMM dd yyyy HH:mm".
     */
    public static String getDifferentFormat(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Converts a string representation of a date into a LocalDate object.
     *
     * @param givenDate The date string in the format "yyyy-MM-dd".
     * @return A LocalDate object representing the parsed date.
     * @throws OrangeException If the input format is incorrect or if parsing fails.
     */
    public static LocalDate getDateObject(String givenDate) throws OrangeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(givenDate, formatter);
        } catch (DateTimeParseException e) {
            throw new OrangeException(INVALID_DATE);
        }
    }

    /**
     * Converts a LocalDate object into a string representation.
     *
     * @param localDate The LocalDate object to be formatted.
     * @return A formatted date string in the format "yyyy-MM-dd".
     */
    public static String getStringFromLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}
