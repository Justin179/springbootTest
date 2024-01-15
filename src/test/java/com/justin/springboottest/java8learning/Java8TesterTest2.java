package com.justin.springboottest.java8learning;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8TesterTest2 {
    @Test
    void sort(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Chris", 20));
        list.add(new Person("Linda", 10));
        list.add(new Person("Jack", 30));
        
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        list.sort(byAge);
        list.forEach(System.out::println);
    }
    
    @Test
    void sort2(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Chris", 20));
        list.add(new Person("Linda", 10));
        list.add(new Person("Jack", 30));

        list.sort((p1, p2) -> p1.getAge() - p2.getAge());
        list.forEach(System.out::println);
        System.out.println("--------");

        list.sort((p1, p2) -> p2.getAge() - p1.getAge());
        list.forEach(System.out::println);
    }

    @Test
    void sort3(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Chris", 20));
        list.add(new Person("Linda", 10));
        list.add(new Person("Jack", 30));
        Comparator<Person> comparing = Comparator.comparing(Person::getAge);
        list.sort(comparing);
        list.forEach(System.out::println);

        System.out.println("--------");

        list.sort(comparing.reversed());
        list.forEach(System.out::println);
    }
    
    @Test
    void sort4(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Chris", 20));
        list.add(new Person("Linda", 10));
        list.add(new Person("Jack", 30));
        list.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);
        System.out.println("----------");
        list.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(System.out::println);
    }

    @Test
    void lamndaFor(){
        List<String> strings = Arrays.asList("1", "2", "3");
        //Lambda foreach
        strings.forEach((s) -> System.out.println(s));
        //or
        strings.forEach(System.out::println);
        //map
        Map<Integer, String> map = new HashMap<>();
        map.forEach((k,v)->System.out.println(v));
    }

    @Test
    public void streamTest() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        //返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();

        //forEach遍历->打印元素
        strings.stream().forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);

        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());

        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(),strings.stream()).count();

        //注意 一个Stream只能操作一次，不能断开，否则会报错。
        Stream stream = strings.stream();
        //第一次使用
        stream.limit(2);
        //第二次使用
        stream.forEach(System.out::println);
        //报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        //但是可以这样, 连续使用
        stream.limit(2).forEach(System.out::println);
    }

    @Test
    public void laziness(){
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<Integer> stream = strings.stream().filter(new Predicate() {
            @Override
            public boolean test(Object o) {
                System.out.println("Predicate.test 执行");
                return true;
            }
        });

        System.out.println("count 执行");
        stream.count();
    }

    // for-each 用到的是多线程
    @Test
    public void parallelStreamTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 5, 4);
        numbers.parallelStream() .forEach(num->System.out.println(Thread.currentThread().getName()+">>"+num));
    }

    @Test
    void flatMap(){
            List<String[]> listOfArrays = Arrays.asList(
                    new String[]{"apple", "banana", "cherry"},
                    new String[]{"orange", "grape", "pear"},
                    new String[]{"kiwi", "melon", "pineapple"}
            );


            List<String[]> mapResult = listOfArrays.stream()
                    .map(array -> Arrays.stream(array).map(String::toUpperCase).toArray(String[]::new))
                    .collect(Collectors.toList());

            System.out.println("Using map:");
            System.out.println(mapResult);


            List<String> flatMapResult = listOfArrays.stream()
                    .flatMap(array -> Arrays.stream(array).map(String::toUpperCase))
                    .collect(Collectors.toList());

            System.out.println("Using flatMap:");
            System.out.println(flatMapResult);
    }

    @Test
    void time(){
        // Java 8 之前 转换都需要借助 SimpleDateFormat 类，而Java 8 之后只需要 LocalDate、LocalTime、LocalDateTime的 of 或 parse 方法
        LocalDate date = LocalDate.of(2021, 1, 26);
        LocalDate date1 = LocalDate.parse("2021-01-26");

        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        LocalDateTime dateTime1 = LocalDateTime.parse("2021-01-26 12:12:22");

        LocalTime time = LocalTime.of(12, 12, 22);
        LocalTime time1 = LocalTime.parse("12:12:22");
    }

//    class Person{
//        String firstName;
//        String lastName;
//        public Person(String firstName, String lastName) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//    }

    @Test
    void comparator(){
        Comparator<Person2> comparator = Comparator.comparing(p -> p.getFirstName());
        Person2 p1 = new Person2("kohn", "Doe");
        Person2 p2 = new Person2("Alice", "Wonderland");
        int compare = comparator.compare(p1, p2);// > 0
        System.out.println(compare);
//        comparator.reversed().compare(p1, p2);  // < 0
        // a b c d e f g h i j k l m n
        // 1 2 3 4 5 6 7 8 9
    }

    @Test
    void optional(){
        //of()：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent()：如果值存在返回true，否则返回false
        optional.isPresent();           // true
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        optional.get();                 // "bam"
        //orElse()：如果有值则将其返回，否则返回指定的其它值
        optional.orElse("fallback");    // "bam"
        //ifPresent()：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    @Test
    void reduce(){
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0). // Z 90, 意味左邊全大於90 = 左邊全是小寫字母
                reduce("", String::concat);
    }

    @Test
    void SequentialSort(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        //串行排序
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }
    
    @Test
    void ParallelSort(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        //并行排序
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis)); // 316
    }

    @Test
    void map(){
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // If the specified key is not already associated with a value (or is mapped to null)
            // associates it with the given value
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));//val0 val1 val2 val3 val4 val5 val6 val7 val8 val9
    }
    
    @Test
    void computeIfPresent(){
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // If the specified key is not already associated with a value (or is mapped to null)
            // associates it with the given value
            map.putIfAbsent(i, "val" + i);
        }

        /*
        computeIfPresent:
        If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
        If the remapping function returns null, the mapping is removed.
        If the remapping function itself throws an (unchecked) exception, the exception is rethrown, and the current mapping is left unchanged.
        The remapping function should not modify this map during computation.
         */

        // If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        // If the remapping function returns null, the mapping is removed.
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        // 已經存在的，不會去蓋
        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33
    }

    @Test
    void learnMapMerge(){
        // 創建第一個 Map
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 1);
        map1.put("orange", 3);
        map1.put("banana", 5);

        // 創建第二個 Map
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("apple", 2);
        map2.put("orange", 4);
        map2.put("grapes", 6);

        // 合併兩個 Map
        map2.forEach((key, value) ->
                map1.merge(key, value, (v1, v2) -> v1 + v2)
        );

        // 打印合併後的結果
        map1.forEach((key, value) ->
                System.out.println(key + ": " + value)
        );
    }
    
    @Test
    void merge(){
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // If the specified key is not already associated with a value (or is mapped to null)
            // associates it with the given value
            map.putIfAbsent(i, "val" + i);
        }

//        map.merge(9, "val9", (value, newValue) -> {
////            System.out.println(value +" & "+newValue);
//            return value.concat(newValue);
//        });
//        String s = map.get(9);// val9
//        System.out.println(s);
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        String s = map.get(9);// val9concat
        System.out.println(s);
        // Merge 做的事情是如果键名不存在则插入
    }
    
    @Test
    void clock(){
        Clock clock = Clock.systemDefaultZone();
        // Clock 类提供了访问当前日期和时间的方法，Clock 是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
        long millis = clock.millis();
        System.out.println(millis);//1552379579043

        // 某一个特定的时间点也可以使用 Instant 类来表示，Instant 类也可以用来创建旧版本的java.util.Date 对象。
        Instant instant = clock.instant();
        System.out.println(instant);
        Date legacyDate = Date.from(instant); //2019-03-12T08:46:42.588Z
        System.out.println(legacyDate);//Tue Mar 12 16:32:59 CST 2019
    }
    
    @Test
    void Timezones(){
        //输出所有区域标识符
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());// ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone2.getRules());// ZoneRules[currentStandardOffset=-03:00]
    }

    @Test
    void LocalTime(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin"); // +1
        ZoneId zone2 = ZoneId.of("Brazil/East"); // -3
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239
    }

    @Test
    void localtime2(){
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

    }

    @Test
    void LocalDate(){
        LocalDate today = LocalDate.now();//获取现在的日期
        System.out.println("今天的日期: "+today);//2019-03-12
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("明天的日期: "+tomorrow);//2019-03-13
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("昨天的日期: "+yesterday);//2019-03-11
        LocalDate independenceDay = LocalDate.of(2019, Month.MARCH, 12);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println("今天是周几:"+dayOfWeek);//TUESDAY
    }
    
    @Test
    void localdate2(){
        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");

        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        System.out.println(dt1); // 输出 2014-04-12T01:06:09
    }
    
    @Test
    void localdate3(){
        LocalDateTime rightNow=LocalDateTime.now();
        String date=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(rightNow);
        System.out.println(date);//2019-03-12T16:26:48.29
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        System.out.println(formatter.format(rightNow));//2019-03-12 16:26:48
    }

    @Test
    void LocalDateTime(){
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13
    }
    /**
     * 创建一个 Optional
     */
    @Test
    public void createOptionalTest() {
        // Optional 构造方式1 - of 传入的值不能为 null
        Optional<String> helloOption = Optional.of("hello");

        // Optional 构造方式2 - empty 一个空 optional
        Optional<String> emptyOptional = Optional.empty();

        // Optional 构造方式3 - ofNullable 支持传入 null 值的 optional
        Optional<String> nullOptional = Optional.ofNullable(null);
    }

    /**
     * 检查是否有值
     */
    @Test
    public void checkOptionalTest() {
        Optional<String> helloOptional = Optional.of("Hello");
        System.out.println(helloOptional.isPresent()); // true

        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.isPresent()); // false
    }

    /**
     * 如果有值，输出长度
     */
    @Test
    public void whenIsPresent() {
        // 如果没有值，获取默认值
        Optional<String> helloOptional = Optional.of("Hello");
        Optional<String> emptyOptional = Optional.empty();
        helloOptional.ifPresent(s -> System.out.println(s.length())); // 5
        emptyOptional.ifPresent(s -> System.out.println(s.length())); // 不會印
    }

    /**
     * 如果没有值，会抛异常
     */
    @Test
    public void getTest() {
        Optional<String> stringOptional = Optional.of("hello");
        System.out.println(stringOptional.get());
        // 如果没有值，会抛异常
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.get());
    }

    /**
     * 如果没有值，获取默认值
     */
    @Test
    public void whenIsNullGetTest() {
        // 如果没有值，获取默认值
        Optional<String> emptyOptional = Optional.empty();
        String orElse = emptyOptional.orElse("orElse default");
        System.out.println(orElse);
        String orElseGet = emptyOptional.orElseGet(() -> "orElseGet default");
        System.out.println(orElseGet);
    }

    /**
     * orElse 和 orElseGet 的区别
     */
    @Test
    public void orElseAndOrElseGetTest() {
        // 如果没有值，默认值
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("空Optional.orElse");
        String orElse = emptyOptional.orElse(getDefault());
        System.out.println("空Optional.orElseGet");
        String orElseGet = emptyOptional.orElseGet(() -> getDefault());
        System.out.println("空Optional.orElse结果："+orElse);
        System.out.println("空Optional.orElseGet结果："+orElseGet);
        System.out.println("--------------------------------");
        // 如果没有值，默认值
        Optional<String> stringOptional = Optional.of("hello");
        System.out.println("有值Optional.orElse");
        orElse = stringOptional.orElse(getDefault()); // 有呼叫
        System.out.println("有值Optional.orElseGet");
        orElseGet = stringOptional.orElseGet(() -> getDefault()); // 沒呼叫
        System.out.println("有值Optional.orElse结果："+orElse);
        System.out.println("有值Optional.orElseGet结果："+orElseGet);
    }

    public String getDefault() {
        System.out.println("   获取默认值中..run getDeafult method");
        return "hello";
    }

    /**
     * 如果没有值，抛出异常
     */
    @Test
    public void whenIsNullThrowExceTest() throws Exception {
        // 如果没有值，抛出异常
        Optional<String> emptyOptional = Optional.empty();
        String value = emptyOptional.orElseThrow(() -> new Exception("发现空值"));
        System.out.println(value);
    }

    @Test
    public void functionTest() {
        // filter 过滤
        Optional<Integer> optional123 = Optional.of(123);
        optional123.filter(num -> num == 123).ifPresent(num -> System.out.println(num));

        Optional<Integer> optional456 = Optional.of(456);
        optional456.filter(num -> num == 123).ifPresent(num -> System.out.println(num));

        // map 转换
        Optional<Integer> optional789 = Optional.of(789);
        optional789.map(String::valueOf).map(String::length).ifPresent(length -> System.out.println(length));
    }

    /**
     * 计算机
     */
    @Data
    class Computer {
        private Optional<SoundCard> soundCard;
    }
        /**
         * 声卡
         */
        @Data
        class SoundCard {
            private Optional<Usb> usb;
        }
            /**
             * USB
             */
            @Data
            class Usb {
                private String version;
            }

    /**
     * 电脑里【有可能】有声卡
     * 声卡【有可能】有USB接口
     */
    @Test
    public void optionalTest() {
        // 没有声卡，没有 Usb 的电脑
        Computer computerNoUsb = new Computer();
        computerNoUsb.setSoundCard(Optional.empty());
        // 获取 usb 版本
        Optional<Computer> computerOptional = Optional.ofNullable(computerNoUsb);
        String version = computerOptional.flatMap(Computer::getSoundCard).flatMap(SoundCard::getUsb)
                .map(Usb::getVersion).orElse("UNKNOWN");
        System.out.println(version);
        System.out.println("-----------------");

        // 如果有值，则输出
        SoundCard soundCard = new SoundCard();
        Usb usb = new Usb();
        usb.setVersion("2.0");
        soundCard.setUsb(Optional.ofNullable(usb));
        Optional<SoundCard> optionalSoundCard = Optional.ofNullable(soundCard);
        optionalSoundCard.ifPresent(System.out::println);
        // 如果有值，则输出
        if (optionalSoundCard.isPresent()) {
            System.out.println(optionalSoundCard.get());
        }

        // 输出没有值，则没有输出
        Optional<SoundCard> optionalSoundCardEmpty = Optional.ofNullable(null);
        optionalSoundCardEmpty.ifPresent(System.out::println);
        System.out.println("-----------------");

        // 筛选 Usb2.0
        optionalSoundCard.map(SoundCard::getUsb)
                .filter(usb1 -> "3.0".equals(usb1.map(Usb::getVersion)
                        .orElse("UBKNOW")))
                .ifPresent(System.out::println);
    }
    
    @Test
    void BiPredicate(){
        // 判断字符串的长度是否是指定长度
        BiPredicate<String, Integer> biFunction = (s, i) -> s.length() == i;
        System.out.println(biFunction.test("java", 3));
        System.out.println(biFunction.test("java", 4)); // t
        System.out.println(biFunction.test("www.wdbyte.com", 10));
        System.out.println(biFunction.test("www.wdbyte.com", 14)); // t
    }

    @Test
    void BiPredicateAnd(){
        BiPredicate<String, String> startPredicate = (s1, s2) -> s1.startsWith(s2);
        BiPredicate<String, String> endPredicate = (s1, s2) -> s1.endsWith(s2);

        boolean test = startPredicate.and(endPredicate).test("wdbyte", "w");
        System.out.println(test);
        boolean test1 = startPredicate.and(endPredicate).test("wsw", "w");
        System.out.println(test1); // t
    }

    @Test
    void Java8FunctionBiPredicateParams(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("牧羊犬", 1));
        list.add(new Dog("牧羊犬", 2));
        list.add(new Dog("哈士奇", 2));
        list.add(new Dog("田园犬", 3));

        // 定義BiPredicate行為
        // 筛选2岁的狗
        BiPredicate<String, Integer> age = (n, a) -> a == 2;
        // 筛选牧羊犬
        BiPredicate<String, Integer> name = (n, a) -> "牧羊犬".equals(n);
        // 筛选2岁的狗或者筛选牧羊犬
        BiPredicate<String, Integer> ageAndName = (n, a) -> "牧羊犬".equals(n) || a == 2;

        // List<Dog> list  &&  BiPredicate<String, Integer> age
//        System.out.println(filter(list, age));
//        System.out.println(filter(list, name));
        System.out.println(filter(list, ageAndName));
    }

    // 告訴JVM T為Dog子類; 此方法回傳List<T>
    <T extends Dog> List<T> filter(List<T> list, BiPredicate<String, Integer> biPredicate) {
        return list.stream()
                .filter(dog -> biPredicate.test(dog.getName(), dog.getAge()))
                .collect(Collectors.toList());
    }

    @Test
    void Java8BiFunction(){
        // 两个字符串长度和
        BiFunction<String, String, Integer> lengthBiFun = (s1, s2) -> s1.length() + s2.length();
        Integer length = lengthBiFun.apply("java", "www.byte.com");
        System.out.println(length);

        // x 的 y 次方
        BiFunction<Integer, Integer, Double> powBiFun = (i1, i2) -> Math.pow(i1, i2);
        Double pow = powBiFun.apply(2, 10);
        System.out.println(pow);
    }

    @Test
    void Java8BiFunctionAndThen(){
        // 两个字符串长度和
        BiFunction<String, String, Integer> lengthBiFun = (s1, s2) -> s1.length() + s2.length();
        Function<Integer, String> function = s -> "长度和:" + s;

        String result = lengthBiFun.andThen(function).apply("java", "www.byte.com");
        System.out.println(result);
    }

    @Test
    void Java8BiFunctionAndThen2(){
        String result = convert("java",
                "www.wdbyte.com",
                (a1, a2) -> a1.length() + a2.length(),
                r1 -> "长度和:" + r1);
        System.out.println(result);
    }

    // 告訴JVM有四個泛型，return 其中一個泛型R2
    <T1, T2, R1, R2> R2 convert(  T1 t1,
                                  T2 t2,
                                  BiFunction<T1, T2, R1> biFunction,
                                  Function<R1, R2> function) {
        return biFunction.andThen(function).apply(t1, t2);
    }

    @Test
    void Java8BiFunctionAndThen3(){
        String convert = convert(1, 2,
                (a1, a2) -> a1 + a2,
                r1 -> "和是:" + r1);
        System.out.println(convert);
    }



    @Test
    void JavaBiFunctionFactory(){
        System.out.println(dogFactory("牧羊犬", 1, Dog::new));
        System.out.println(dogFactory("哈士奇", 2, Dog::new));
    }

    // 告訴JVM 泛型R父類為狗
    <R extends Dog> Dog dogFactory(String name, Integer age, BiFunction<String, Integer, R> biFunction) {
        return biFunction.apply(name, age);
    }



    @Test
    void Java8Supplier(){
        Supplier<Integer> supplier = () -> new Random().nextInt(10);
        System.out.println(supplier.get());
        System.out.println(supplier.get());

        Supplier<LocalDateTime> supplier2 = LocalDateTime::now;
        System.out.println(supplier2.get());
        System.out.println(supplier2.get());
    }

    @Test
    void Java8SupplierFactory(){
        Dog dog1 = dogFactory(() -> new Dog("哈士奇"));
        Dog dog2 = dogFactory(() -> new Dog("牧羊犬"));
        System.out.println(dog1);
        System.out.println(dog2);
    }

    Dog dogFactory(Supplier<? extends Dog> supplier) {
        Dog dog = supplier.get();
        dog.setAge(1);
        return dog;
    }

    @Test
    void Java8SupplierInt(){
        IntSupplier intSupplier = () -> new Random().nextInt(10);
        System.out.println(intSupplier.getAsInt());
        System.out.println(intSupplier.getAsInt());
    }


    @Test
    void Java8PredicateTest(){
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("www.wdbyte.com"));
    }

    @Test
    void Java8PredicateFilter(){
        List<String> list = Arrays.asList("java", "node", "www.wdbyte.com");
        list = list.stream().filter(str -> str.length() == 4).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    void Java8PredicateAnd(){
        List<Integer> numberList = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> greaterThan5 = number -> number > 5;
        Predicate<Integer> lessThan9 = number -> number < 9;
        Predicate<Integer> filter = greaterThan5.and(lessThan9);

        numberList = numberList.stream().filter(filter).collect(Collectors.toList());
        System.out.println(numberList);

        List<Integer> numberList2 = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10);
        numberList2 = numberList2.stream().filter(x -> x > 5 && x < 9).collect(Collectors.toList());
        System.out.println(numberList2);
    }

    @Test
    void Java8PredicateNeagete(){
        List<Integer> numberList = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> greaterThan5 = number -> number > 5;

        numberList = numberList.stream().filter(greaterThan5.negate()).collect(Collectors.toList());
        System.out.println(numberList);
    }

    @Test
    void Java8PredicateOr(){
        List<Integer> numberList = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> lessThan5 = number -> number <= 5;
        Predicate<Integer> greaterThan8 = number -> number >= 9;

        numberList = numberList.stream().filter(lessThan5.or(greaterThan8)).collect(Collectors.toList());
        System.out.println(numberList);
    }

    @Test
    void Java8PredicateChain(){
        List<Integer> numberList = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> lessThan5 = number -> number <= 5;
        Predicate<Integer> greaterThan9 = number -> number >= 9;

        // 小于等于 5
        System.out.println(filter(numberList, lessThan5));
        // 大于 5
        System.out.println(filter(numberList, lessThan5.negate()));
        // 小于等于 5 或者大于等于 9
        System.out.println(filter(numberList, lessThan5.or(greaterThan9)));
        // ! (小于等于 5 AND 大于等于 9)
        System.out.println(filter(numberList, lessThan5.and(greaterThan9).negate()));
    }

    <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    @Test
    void Java8PredicateObject(){
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("哈士奇", 1));
        dogList.add(new Dog("牧羊犬", 2));
        dogList.add(new Dog("柯基", 3));
        dogList.add(new Dog("柴犬", 3));

        // 找到 3岁的狗
        System.out.println(filter2(dogList, dog -> dog.getAge().equals(3)));
        // 找到哈士奇信息
        Predicate<Dog> predicate = dog -> ("哈士奇").equals(dog.getName());
        System.out.println(filter2(dogList, predicate));
    }


    <T> List<T> filter2(List<T> list, Predicate<T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (T t : list) { // 符合條件的加入清單
            if (predicate.test(t)) { resultList.add(t); }
        }
        return resultList;
    }

    

    @Test
    void Java8Function(){
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        String result = toUpperCase.apply("www.wdbyte.com");
        System.out.println(result);
    }

    @Test
    void Java8FunctionLength(){
        Function<String, Integer> lengthFunction = str -> str.length();
        Integer length = lengthFunction.apply("www.wdbyte.com");
        System.out.println(length);
    }

    @Test
    void Java8FunctionAndThen(){
        Function<String, Integer> lengthFunction = str -> str.length();
        Function<Integer, Integer> doubleFunction = length -> length * 2;
        Integer doubleLength = lengthFunction.andThen(doubleFunction).apply("www.wdbyte.com");
        System.out.println(doubleLength);
    }

    @Test
    void Java8FunctionListToMap(){
        List<String> list = Arrays.asList("java", "nodejs", "wdbyte.com");

        // lambda 方式
        Function<String, Integer> lengthFunction = str -> str.length();
        Map<String, Integer> listToMap = listToMap(list, lengthFunction);
        System.out.println(listToMap);

        // 方法引用方式
        Map<String, Integer> listToMap2 = listToMap(list, String::length);
        System.out.println(listToMap2);
    }

    // 一開始的<T, R>就是在告訴JVM，這是一個泛型方法
    // T R = String, Integer
    <T, R> Map<T, R> listToMap(List<T> list, Function<T, R> function) {
        HashMap<T, R> hashMap = new HashMap<>();
        for (T t : list) {
            hashMap.put(t, function.apply(t));
        }
        return hashMap;
    }

    @Test
    void Java8FunctionString(){
        List<String> list = Arrays.asList("Java", "Nodejs", "Wdbyte.com");

        // 方法引用方式
        List<String> upperList = map(list, String::toUpperCase);
        List<String> lowerList = map(list, String::toLowerCase);
        System.out.println(upperList);
        System.out.println(lowerList);

        // Lambda 方式
        List<String> upperList2 = map(list, x -> x.toUpperCase());
        List<String> lowerList2 = map(list, x -> x.toLowerCase());
        System.out.println(upperList2);
        System.out.println(lowerList2);
    }

    // TR StringString
    <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> resultList = new ArrayList<>(list.size());
        for (T t : list) {
            resultList.add(function.apply(t));
        }
        return resultList;
    }

}
