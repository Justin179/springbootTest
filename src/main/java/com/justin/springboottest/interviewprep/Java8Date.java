package com.justin.springboottest.interviewprep;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.TimeZone;

public class Java8Date {
    public static void main(String[] args) {
        /*
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTimeFormatter.format(dateTime);
        System.out.println(String.format("dateTime format : %s", dateTimeStr));
         */

/*
        //一周后的日期
        LocalDate localDate = LocalDate.now();
        //方法1
        LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
        //方法2
        LocalDate after2 = localDate.plusWeeks(1);
        System.out.println("一周后日期：" + after);

        //算两个日期间隔多少天，计算间隔多少年，多少月
        LocalDate date1 = LocalDate.parse("2021-02-26");
        LocalDate date2 = LocalDate.parse("2021-12-23");
        Period period = Period.between(date1, date2);
        System.out.println("date1 到 date2 相隔："
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "天");
        //打印结果是 “date1 到 date2 相隔：0年9月27天”
        //这里period.getDays()得到的天是抛去年月以外的天数，并不是总天数
        //如果要获取纯粹的总天数应该用下面的方法
        long day = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date2 + "和" + date2 + "相差" + day + "天");
        //打印结果：2021-12-23和2021-12-23相差300天
 */
        /*
        LocalDate today = LocalDate.now();
        //获取当前月第一天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月最后一天
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        //取下一天：
        LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
        //当年最后一天
        LocalDate lastday = today.with(TemporalAdjusters.lastDayOfYear());
        //2021年最后一个周日，如果用Calendar是不得烦死。
        LocalDate lastMondayOf2021 = LocalDate.parse("2021-12-31").with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println(lastMondayOf2021);
        */

        /*
        //北京时间：Wed Jan 27 14:05:29 CST 2021
        Date date = new Date();
        //如果直接print会自动转成当前时区的时间
        System.out.println(date);
        //Wed Jan 27 14:05:29 CST 2021

        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //北京时区
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("毫秒数:" + date.getTime() + ", 北京时间:" + bjSdf.format(date));

        //东京时区
        SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tokyoSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));  // 设置东京时区
        System.out.println("毫秒数:" + date.getTime() + ", 东京时间:" + tokyoSdf.format(date));
        */

        //当前时区时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区时间: " + zonedDateTime);

        //东京时间
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
        ZonedDateTime tokyoTime = zonedDateTime.withZoneSameInstant(zoneId);
        System.out.println("东京时间: " + tokyoTime);

        // ZonedDateTime 转 LocalDateTime
        LocalDateTime localDateTime = tokyoTime.toLocalDateTime();
        System.out.println("东京时间转当地时间: " + localDateTime);

        //LocalDateTime 转 ZonedDateTime
        ZonedDateTime localZoned = localDateTime.atZone(ZoneId.systemDefault());
        System.out.println("本地时区时间: " + localZoned);


    }
}
