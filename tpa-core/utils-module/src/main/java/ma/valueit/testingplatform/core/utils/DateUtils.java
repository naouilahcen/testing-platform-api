package ma.valueit.testingplatform.core.utils;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yelansari on 8/24/17.
 */
public class DateUtils {
    private static GregorianCalendar gc = new GregorianCalendar();

    private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSZZ";

    private static SimpleDateFormat dateISO8601Format = new SimpleDateFormat(ISO_8601_DATE_FORMAT);


    private static final List<Integer> month31Days = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));

    private static final List<Integer> month30Days = new ArrayList<>(Arrays.asList(4, 6, 9, 11));


    public static Date stringTODateISO8601(String v) throws ParseException {
        return dateISO8601Format.parse(v);
    }

    public static String toSimpleDateISO8601(Date v) throws ParseException {
        return dateISO8601Format.format(v);
    }

    public static String toSimpleDateFormatAn(Date d) {
        if (d != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(d);
        }

        return "";
    }

    public static String toSimpleDateTimeFormatAn(Date d) {
        if (d != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(d);
        }

        return "";
    }

    public static Integer getYear() {
        return getYear(getDate());
    }

    public static Integer getMonth() {
        return getMonth(getDate());
    }

    public static Integer getYear(Date d) {
        if (d == null) {
            d = getDate();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

        return Integer.parseInt(simpleDateFormat.format(d));
    }

    public static Integer getMonth(Date d) {
        if (d == null) {
            d = getDate();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        return Integer.parseInt(simpleDateFormat.format(d)) - 1;
    }

    public static String getSimpleDateTimeFormatAn() {
        Date now = DateUtils.getDate();

        String simpleDateTimeFormatFr = DateUtils.toSimpleDateFormat(now, "yyyy-MM-dd") + " at " + DateUtils.toSimpleDateFormat(now, "hh-mm-ss");

        return simpleDateTimeFormatFr;
    }


    public static String getSimpleDateTimeFormatFr() {
        Date now = DateUtils.getDate();

        String simpleDateTimeFormatFr = DateUtils.toSimpleDateFormat(now, "dd-MM-yyyy") + " à " + DateUtils.toSimpleDateFormat(now, "hh.mm.ss a");

        return simpleDateTimeFormatFr;
    }

    public static String toSimpleDateFormatFr(Date d) {
        if (d != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDateFormat.format(d);
        }

        return "";
    }

    public static String toSimpleDateFormat(Date d, String pattern) {
        if (d != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(d);
        }

        return "";
    }

    public static boolean isEmpty(Date d) {
        return d == null;
    }


    public static boolean isEmpty(DateTime d) {
        return d == null;
    }


    public static Date getRandomDate(int from, int to) {

        int year = NumberUtils.randBetween(from, to);

        gc.set(GregorianCalendar.YEAR, year);

        int dayOfYear = NumberUtils.randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));

        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        gc.set(GregorianCalendar.YEAR, year);
        gc.set(GregorianCalendar.MONTH, month);
        gc.set(GregorianCalendar.DAY_OF_MONTH, day);

        return gc.getTime();
    }

    public static Date getDate() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getDate(String string, String pattern, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(string) || StringUtils.isEmpty(pattern)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat(pattern, locale);

        Date date = format.parse(string);

        return date;
    }

    public static long getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static boolean isAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    public static boolean isAfterOrSame(Date date1, Date date2) {
        return isAfter(date1, date2) || isSame(date1, date2);
    }

    public static boolean isSame(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        }

        return false;
    }

    public static boolean isDateBefore(Date date1, Date date2) {
        Date d1 = getDateMinimum(date1);
        Date d2 = getDateMinimum(date2);

        return d1.before(d2);
    }

    public static boolean isDateAfter(Date date1, Date date2) {
        Date d1 = getDateMinimum(date1);
        Date d2 = getDateMinimum(date2);

        return d1.after(d2);
    }

    public static boolean isBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    public static Date addDays(Date date, int days) {
        gc.setTime(date);

        gc.add(Calendar.DATE, days);

        return gc.getTime();
    }

    public static Date addMonths(Date date, int months) {
        gc.setTime(date);

        gc.add(Calendar.MONTH, months);

        return gc.getTime();
    }

    public static Date addYears(Date date, int years) {
        gc.setTime(date);

        gc.add(Calendar.YEAR, years);

        return gc.getTime();
    }

    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        gc.set(gc.DAY_OF_MONTH, gc.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.MONTH, month);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);

        return gc.getTime();
    }

    public static Date getLastDayOfMonth(Integer year, Integer month) {
        gc.set(gc.DAY_OF_MONTH, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.MONTH, month);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);

        return gc.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 23);
        gc.set(Calendar.MINUTE, 59);
        gc.set(Calendar.SECOND, 59);
        gc.set(Calendar.MILLISECOND, 99);
        gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        return gc.getTime();
    }

    public static Long getDateDiffDays(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return null;
        }

        long diff = DateUtils.getDateMinimum(d1).getTime() - DateUtils.getDateMinimum(d2).getTime();

        long days = TimeUnit.MILLISECONDS.toDays(diff);

        return days;
    }

    public static DayOfWeek getDay(Date date) {
        gc.setTime(date);

        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 1:
                return DayOfWeek.SUNDAY;
            case 2:
                return DayOfWeek.MONDAY;
            case 3:
                return DayOfWeek.TUESDAY;
            case 4:
                return DayOfWeek.WEDNESDAY;
            case 5:
                return DayOfWeek.THURSDAY;
            case 6:
                return DayOfWeek.FRIDAY;
            default:
                return DayOfWeek.SATURDAY;
        }
    }

    public static Date dateTimeToDate(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.toDate();
    }

    public static Date setTime(final Date date, final int hourOfDay, final int minute, final int second, final int ms) {
        if (date == null) {
            return null;
        }

        final GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);

        gc.set(Calendar.HOUR_OF_DAY, hourOfDay);

        gc.set(Calendar.MINUTE, minute);

        gc.set(Calendar.SECOND, second);

        gc.set(Calendar.MILLISECOND, ms);

        return gc.getTime();
    }

    public static Date getDateMidnight(Date date) {
        return setTime(date, 23, 59, 59, 99);
    }

    public static Date getDateMinimum(Date date) {
        return setTime(date, 0, 0, 0, 0);
    }

    public static boolean isLastDayInTheMonth(DateTime date) {
        Date lastDateOfMonth = getLastDayOfMonth(date.toDate());
        DateTime _lastDateOfMonth = new DateTime(lastDateOfMonth);

        return _lastDateOfMonth.getDayOfMonth() == date.getDayOfMonth();
    }

    public static Date getTodayDateExactTime(String time) throws ParseException {
        Date now = getDate();

        if (StringUtils.isEmpty(time)) {
            return now;
        }

        String date = String.format("%s %s", toSimpleDateFormatAn(now), time);

        Date date1 = getDate(date, "yyyy-MM-dd HH:mm", Locale.ENGLISH);

        return date1;
    }
}
