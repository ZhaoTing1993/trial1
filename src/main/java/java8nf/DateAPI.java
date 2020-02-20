package java8nf;

import javafx.util.converter.LocalTimeStringConverter;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateAPI {

    public static void main(String[] args) {
        local();
        zoned();
    }

    public static void local() {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    public static void zoned() {
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

        ZonedDateTime parisNow = ZonedDateTime.now(id);
        System.out.println("paris:"+parisNow);
        LocalDateTime localDateTime = parisNow.toLocalDateTime();
        System.out.println("format:"+localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("format:"+localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("format:"+localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("format:"+ LocalDateTime.parse("2018-02-02 19:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
}
