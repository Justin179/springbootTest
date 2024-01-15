package com.justin.springboottest.java8learning;

import com.justin.springboottest.java8.lambda.VoidMethodWithTwoParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Java8TesterTest {

    @Test
    void test1(){
        int adder = 5;
        Arrays.asList(1, 2, 3, 4, 5).forEach(e -> System.out.println(e + adder));
    }

    String[] strArr;
    @BeforeEach
    void init(){
        strArr = new String[]{"a", "b", "c"};
    }

    @Test
    void 使用Collection中的方法和Arrays(){
        // 先轉成List，再轉成Stream
        List<String> list = Arrays.asList(strArr);
        Stream<String> stream = list.stream();
        // 直接轉成Stream
        Stream<String> stream1 = Arrays.stream(strArr);
    }

    @Test
    void 使用Stream中提供的静态方法(){
        Stream<String> stream2 = Stream.of(strArr);
        Stream<Double> stream3 = Stream.generate(Math::random);
        Stream<Object> stream4 = Stream.empty();
        Stream.iterate(1, i -> i++);
    }
    
    @Test
    void concat(){
        Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5, 6)).
                forEach(System.out::println);
    }
    
    @Test
    void distinct(){
        Stream<String> stream = Stream.of("a", "a", "b", "c");
        stream.distinct().forEach(System.out::println);
    }
    
    @Test
    void limit(){
        Stream<String> stream = Stream.of("a", "a", "b", "c");
        stream.limit(2).forEach(System.out::println);
    }
    
    @Test
    void skip(){
        Stream<String> stream = Stream.of("a", "a", "b", "c");
        stream.skip(2).forEach(System.out::println);
    }
    
    @Test
    void peek(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }
    
    @Test
    void methodName(){
        Stream<Integer> stream = Stream.of(3, 2, 1);
        stream.sorted(Integer::compareTo).forEach(System.out::println);
    }
    
    @Test
    void match(){
        Stream<Integer> stream = Stream.of(3, 2, 1);
        boolean match = stream.allMatch(integer -> integer > 5);
        System.out.println(match);
    }
    
    @Test
    void count(){
        Stream<String> stream = Stream.of("3", "2", "1");
        long count = stream.filter(str -> str.isEmpty()).count();
        System.out.println(count);
    }
    
    @Test
    void minMax(){
        Stream<Integer> stream = Stream.of(3, 2, 1);
        System.out.println(stream.max(Integer::compareTo).get());
    }
    
    @Test
    void forEach(){
        Stream.of(5, 4, 3, 2, 1)
                .sorted()
                .forEach(System.out::println);
        // 打印结果
        // 1，2，3,4,5
    }

    @Test
    void createOptional(){
//创建Optional
        Optional<Object> optional = Optional.empty();
        Optional<Object> optional1 = Optional.ofNullable(null);
        Optional<String> optional2 = Optional.of(null);
    }
    
    @Test
    void time(){
        Clock clock = Clock.systemUTC(); // 格林威治時間
        Instant instant = clock.instant();
        System.out.println(instant.toString());

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime.toString());
    }

    @Test
    public void streamDemo(){
        List<String> nameList = Arrays.asList("Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter");
        // 1. 筛选出名字长度为4的
        // 2. 名字前面拼接 This is
        // 3. 遍历输出
        nameList.stream()
                .filter(name -> name.length() == 4)
                .map(name -> "This is "+name)
                .forEach(name -> System.out.println(name));
    }

    // 很清楚的例子
    @Test
    void LambdaRunnableExample() throws InterruptedException {
        // 使用 Lambda 之前
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("JDK8 之前的线程创建");
            }
        };
        new Thread(runnable).start();

        // 使用 Lambda 之后
        Runnable runnable1Jdk8 = () -> System.out.println("JDK8 之后的线程创建");
        new Thread(runnable1Jdk8).start();
        // 更加紧凑的方式
        new Thread(() -> System.out.println("JDK8 之后的线程创建")).start();
    }


    @FunctionalInterface
    public interface FunctionInterfaceDemo {
        void say(String name, int age);
    }
    @Test
    void LambdaFIDemo(){
        FunctionInterfaceDemo demo = (name, age) -> System.out.println(name + "__" + age);
        demo.say("Justin",42);
    }

    @Test
    void InputStream(){
        try (InputStream fis = new FileInputStream("input.txt")) {
            System.out.println("Number of remaining bytes:" + fis.available());
            int content;
            long skip = fis.skip(2);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void InputStream2() throws IOException {
        // 新建一个 BufferedInputStream 对象
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input.txt"));
        // 读取文件的内容并复制到 String 对象中
        String result = new String(bufferedInputStream.readAllBytes());
        System.out.println(result);
    }

    @Test
    void OutputStream() throws IOException {
        try (FileOutputStream output = new FileOutputStream("output.txt")) {
            byte[] array = "JavaGuide".getBytes();
            output.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void FileReader() throws FileNotFoundException {
        try (FileReader fileReader = new FileReader("input2.txt");) {
            int content;
            long skip = fileReader.skip(3);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fileReader.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void FileWriter(){
        try (Writer output = new FileWriter("output2.txt")) {
            output.write("你好，我是Guide。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void copy_pdf_to_another_pdf_buffer_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Mockito3CheatSheet.pdf"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Mockito3CheatSheet-副本.pdf"))) {
            int content;
            while ((content = bis.read()) != -1) {
                bos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒"); // 68 毫秒
    }

    @Test
    void copy_pdf_to_another_pdf_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("Mockito3CheatSheet.pdf");
             FileOutputStream fos = new FileOutputStream("Mockito3CheatSheet-副本.pdf")) {
            int content;
            while ((content = fis.read()) != -1) {
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒"); // 10791 毫秒
    }

    @Test
    void copy_pdf_to_another_pdf_with_byte_array_buffer_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Mockito3CheatSheet.pdf"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Mockito3CheatSheet-副本.pdf"))) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒"); // 6
    }

    @Test
    void copy_pdf_to_another_pdf_with_byte_array_stream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("Mockito3CheatSheet.pdf");
             FileOutputStream fos = new FileOutputStream("Mockito3CheatSheet-副本.pdf")) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒"); // 6
    }

    /*
    RandomAccessFile 中有一个文件指针用来表示下一个将要被写入或者读取的字节所处的位置。
    我们可以通过 RandomAccessFile 的 seek(long pos) 方法来设置文件指针的偏移量（距文件开头 pos 个字节处）。
    如果想要获取文件指针当前的位置的话，可以使用 getFilePointer() 方法。
     */
    @Test
    void RandomAccessFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("input3.txt"), "rw");
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
        // 指针当前偏移量为 6
        randomAccessFile.seek(6);
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
        // 从偏移量 7 的位置开始往后写入字节数据
        randomAccessFile.write(new byte[]{'H', 'I', 'J', 'K'});
        // 指针当前偏移量为 0，回到起始位置
        randomAccessFile.seek(0);
        System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer() + ",当前读取到的字符" + (char) randomAccessFile.read() + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
    }

    /**
     * 新的遍历方式
     */
    @Test
    public void foreachTest() {
        List<String> skills = Arrays.asList("java", "golang", "c++", "c", "python");

        // 使用 Lambda 之前
        for (String skill : skills) {
            System.out.print(skill+",");
        }
        System.out.println();

        // 使用 Lambda 之后
        // 方式1,forEach+lambda
        skills.forEach((skill) -> System.out.print(skill+","));
        System.out.println();
        // 方式2，forEach+方法引用
        skills.forEach(System.out::print);
    }

    @Test
    public void streamTest() {
        List<String> skills = Arrays.asList("java", "golang", "c++", "c", "python", "java");
        // Jdk8 之前
        for (String skill : skills) {
            System.out.print(skill + ",");
        }
        System.out.println();

        // Jdk8 之后-去重遍历
        skills.stream().distinct().forEach(skill -> System.out.print(skill + ","));
        System.out.println();
        // Jdk8 之后-去重遍历
        skills.stream().distinct().forEach(System.out::print);
        System.out.println();
        // Jdk8 之后-去重，过滤掉 ptyhon 再遍历
        skills.stream().distinct().filter(skill -> skill != "python").forEach(skill -> System.out.print(skill + ","));
        System.out.println();
        // Jdk8 之后转字符串
        String skillString = String.join(",", skills);
        System.out.println(skillString);
    }

    /**
     * 数据转换
     */
    @Test
    public void mapTest() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        // 数据转换
        numList.stream().map(num -> num * num).forEach(num -> System.out.print(num + ","));

        System.out.println();

        // 数据收集
        Set<Integer> numSet = numList.stream().map(num -> num * num).collect(Collectors.toSet());
        numSet.forEach(num -> System.out.print(num + ","));
    }

    /**
     * 数学计算测试
     */
    @Test
    public void mapMathTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最小值：" + stats.getMin());
        System.out.println("最大值：" + stats.getMax());
        System.out.println("个数：" + stats.getCount());
        System.out.println("和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
        // 求和的另一种方式
        Integer integer = list.stream().reduce((sum, cost) -> sum + cost).get();
        System.out.println(integer);
    }

    @Test
    public void toUpperCaseDemo() {
        List<String> nameList = Arrays.asList("Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter");
        List<String> upperCaseNameList = nameList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperCaseNameList.forEach(name -> System.out.println(name + ","));
    }

    @Test
    public void createStream() throws FileNotFoundException {
        List<String> nameList = Arrays.asList("Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter");
        String[] nameArr = {"Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter"};

        // 集合获取 Stream 流
        Stream<String> nameListStream = nameList.stream();
        // 集合获取并行 Stream 流
        Stream<String> nameListStream2 = nameList.parallelStream();
        // 数组获取 Stream 流
        Stream<String> nameArrStream = Stream.of(nameArr);
        // 数组获取 Stream 流
        Stream<String> nameArrStream1 = Arrays.stream(nameArr);

        // 文件流获取 Stream 流
        BufferedReader bufferedReader = new BufferedReader(new FileReader("HELP.md"));
        Stream<String> linesStream = bufferedReader.lines();

        // 从静态方法获取流操作
        IntStream rangeStream = IntStream.range(1, 10);
        rangeStream.limit(10).forEach(num -> System.out.print(num+","));

        System.out.println();
        IntStream intStream = IntStream.of(1, 2, 3, 3, 4);
        intStream.forEach(num -> System.out.print(num+","));
    }

    @Test
    public void mapTest2() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 映射成 2倍数字
        List<Integer> collect = numberList.stream()
                .map(number -> number * 2)
                .collect(Collectors.toList());
        collect.forEach(number -> System.out.print(number + ","));
        System.out.println();

        numberList.stream()
                .map(number -> "数字 " + number + ",")
                .forEach(number -> System.out.println(number));
    }

    /**
     * flatmap把对象扁平化
     */
    @Test
    public void flatMapTest() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> collect = inputStream
                .flatMap((childList) -> childList.stream()) // 将每个列表转换为其各自的流，然后将这些流合并为一个流
                .collect(Collectors.toList()); // 最后，使用 collect 方法将所有提取的元素收集到一个新的列表中。
        collect.forEach(number -> System.out.print(number + ","));
    }

    @Test
    void FlatMapExample(){
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        // FlatMap 用于将多个集合合并成一个集合
        // flatMap 方法接收一个函数，该函数将每个列表转换为其各自的流，然后将这些流合并为一个流。最后，使用 collect 方法将所有提取的元素收集到一个新的列表中。
        List<Integer> flattenedList = nestedList.stream()
                .flatMap(List::stream) // 使用 flatMap 提取内部列表的元素
                .collect(Collectors.toList());

        System.out.println(flattenedList);
    }

    @Test
    public void filterTest() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = numberList.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        collect.forEach(number -> System.out.print(number + ","));
    }






    @Test
    public void findFirstTest(){
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> firstNumber = numberList.stream()
                .findFirst();
        System.out.println(firstNumber.orElse(-1));
    }

    /**
     * Stream 转换为其他数据结构
     */
    @Test
    public void collectTest() {
        List<Integer> numberList = Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5);
        // to array
        Integer[] toArray = numberList.stream()
                .toArray(Integer[]::new);
        // to List
        List<Integer> integerList = numberList.stream()
                .collect(Collectors.toList());
        // to set
        Set<Integer> integerSet = numberList.stream()
                .collect(Collectors.toSet());
        System.out.println(integerSet);
        // to string
        String toString = numberList.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining()).toString();
        System.out.println(toString);
        // to string split by ,
        String toStringbJoin = numberList.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(",")).toString();
        System.out.println(toStringbJoin);
    }

    @Test
    public void limitOrSkipTest() {
        // 生成自己的随机数流
        List<Integer> ageList = Arrays.asList(11, 22, 13, 14, 25, 26);
        ageList.stream()
                .limit(3)
                .forEach(age -> System.out.print(age+","));
        System.out.println();

        ageList.stream()
                .skip(3)
                .forEach(age -> System.out.print(age+","));
    }

    @Test
    public void mathTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        IntSummaryStatistics stats = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最小值：" + stats.getMin());
        System.out.println("最大值：" + stats.getMax());
        System.out.println("个数：" + stats.getCount());
        System.out.println("和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
    }

    @Test
    public void groupByTest() {
        List<Integer> ageList = Arrays.asList(11, 22, 13, 14, 25, 26);
        Map<String, List<Integer>> ageGrouyByMap = ageList.stream()
                .collect(Collectors.groupingBy(age -> String.valueOf(age / 10)));
        ageGrouyByMap.forEach((k, v) -> {
            System.out.println("年龄" + k + "0多岁的有：" + v);
        });
    }
    
    @Test
    void groupByTest2(){
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25),
                new Person("David", 30)
        );

        // 使用 Collectors.groupingBy 根据年龄分组人员
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // 输出分组结果
        peopleByAge.forEach((age, listOfPeople) -> {
            System.out.println("Age: " + age);
            System.out.println("People: " + listOfPeople);
        });
    }



    @Test
    public void partitioningByTest() {
        List<Integer> ageList = Arrays.asList(11, 22, 13, 14, 25, 26);
        Map<Boolean, List<Integer>> ageMap = ageList.stream()
                .collect(Collectors.partitioningBy(age -> age > 18));
        System.out.println("未成年人：" + ageMap.get(false));
        System.out.println("成年人：" + ageMap.get(true));
    }

    @Test
    public void generateTest(){
        // 生成自己的随机数流
        Random random = new Random();
        Stream<Integer> generateRandom = Stream.generate(random::nextInt);
        generateRandom.limit(5).forEach(System.out::println);
        // 生成自己的 UUID 流
        Stream<UUID> generate = Stream.generate(UUID::randomUUID);
        generate.limit(5).forEach(System.out::println);
    }

    @Test
    public void lazyTest() {
        // 生成自己的随机数流
        List<Integer> numberLIst = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 找出偶数
        Stream<Integer> integerStream = numberLIst.stream()
                .filter(number -> {
                    int temp = number % 2;
                    if (temp == 0 ){
                        System.out.println(number);
                    }
                    return temp == 0;
                });

        System.out.println("分割线");
        List<Integer> collect = integerStream.collect(Collectors.toList());
    }

    /**
     * 并行计算
     */
    @Test
    public void main() {
        // 生成自己的随机数流，取一千万个随机数
        Random random = new Random();
        Stream<Integer> generateRandom = Stream.generate(random::nextInt);
        List<Integer> numberList = generateRandom.limit(10000000).collect(Collectors.toList());

        // 串行 - 把一千万个随机数，每个随机数 * 2 ，然后求和
        long start = System.currentTimeMillis();
        int sum = numberList.stream()
                .map(number -> number * 2)
                .mapToInt(x -> x)
                .sum();
        long end = System.currentTimeMillis();
        System.out.println("串行耗时："+(end - start)+"ms，和是:"+sum);

        // 并行 - 把一千万个随机数，每个随机数 * 2 ，然后求和
        start = System.currentTimeMillis();
        sum = numberList.parallelStream()
                .map(number -> number * 2)
                .mapToInt(x -> x)
                .sum();
        end = System.currentTimeMillis();
        System.out.println("并行耗时："+(end - start)+"ms，和是:"+sum);
    }

    @Test
    public void simpleTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int[] factor = new int[] { 2 };
        Stream<Integer> stream = numbers.stream()
                .map(e -> e * factor[0]);
        factor[0] = 0;
        stream.forEach(System.out::println);
    }


    @Test
    void listToMap(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("牧羊犬", 1));
        list.add(new Dog("哈士奇", 2));
        list.add(new Dog("田园犬", 3));

        // to map,key dog name,value ,dog age
        Map<String, Integer> dogMap = list.stream()
                .collect(Collectors.toMap(Dog::getName, Dog::getAge));

        System.out.println(dogMap);
    }

    @Test
    void listToMap2(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("牧羊犬", 1));
        list.add(new Dog("哈士奇", 2));
        list.add(new Dog("田园犬", 3));

        // to map,key dog name,value ,dog age
        Map<String, Dog> dogMap = list.stream()
                .collect(Collectors.toMap(Dog::getName, dog -> dog));
        System.out.println(dogMap);
    }

    @Test
    void listToMap3(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("牧羊犬", 1));
        list.add(new Dog("牧羊犬", 2));
        list.add(new Dog("哈士奇", 2));
        list.add(new Dog("田园犬", 3));

        // to map,key dog name,value ,dog age
//        Map<String, Integer> dogMap = list.stream()
//                .collect(Collectors.toMap(Dog::getName, Dog::getAge));
        // java.lang.IllegalStateException: Duplicate key 牧羊犬 (attempted merging values 1 and 2)

        // 解決
        // to map,key dog name,value ,dog age
        // 传入第三个参数，当 Key 冲突时，选择使用新值。
        Map<String, Integer> dogMap = list.stream()
                .collect(Collectors.toMap(Dog::getName, Dog::getAge, (oldData, newData) -> newData));
        System.out.println(dogMap);
    }

    @Test
    void listToMap4(){
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("牧羊犬", 1));
        list.add(new Dog("牧羊犬", 2));
        list.add(new Dog("哈士奇", 2));
        list.add(new Dog("田园犬", 3));

        // 传入第四个参数，指定 Map 类型
        Map<String, Integer> dogMap = list.stream()
                .collect(Collectors.toMap(Dog::getName, Dog::getAge,
                        (oldData, newData) -> newData, ConcurrentHashMap::new));
        System.out.println(dogMap.getClass());
    }
    
    @Test
    void listForEach(){
        List<String> list = Arrays.asList("java", "nodejs", "c++", "wdbyte.com");
        // 方法引用
        list.forEach(System.out::println);
        System.out.println("-------------");
        // lambda
        list.forEach(s -> System.out.println(s));
    }
    
    @Test
    void mapForEach(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("java", "JAVA");
        hashMap.put("nodejs", "NODEJS");
        hashMap.put("c++", "C++");
        hashMap.put("wdbyte", "WDBYTE.COM");
        hashMap.put(null, "OTHER");
        hashMap.forEach((k, v) -> System.out.println(k + ":\t" + v));
    }
    
    @Test
    void mapForEach2(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("java", "JAVA");
        hashMap.put("nodejs", "NODEJS");
        hashMap.put("c++", "C++");
        hashMap.put("wdbyte", "WDBYTE.COM");
        hashMap.put(null, "OTHER");
        hashMap.forEach((k, v) -> {
            if (k != null) {
                System.out.println(k + ":\t" + v);
            }
        });
    }
    
    @Test
    void arrayForEach(){
        String[] array = {"java", "nodejs", "wdbyte.com"};
        Arrays.stream(array).forEach(System.out::println); // array.stream -> IDE會幫你
    }
    
    @Test
    void arrayForEach2(){
        Stream<String> stream = Stream.of("java", "nodejs", "c++", "wdbyte.com");
        stream.parallel().forEach(System.out::println);
    }
    
    @Test
    void arrayForEachWzOrder(){
        Stream<String> stream = Stream.of("java", "nodejs", "c++", "wdbyte.com");
        stream.parallel().forEachOrdered(System.out::println);
    }

    // 查看 List 和 Stream 的 forEach 方法，发现它们都是接受一个 Consumer<? super T> 类型参数。
    @Test
    void listnStreamForEach(){
        List<String> list = Arrays.asList("java", "nodejs", "wdbyte.com");
        Stream<String> stream = Stream.of("java", "nodejs", "wdbyte.com");

        Consumer<String> consumer = System.out::println;

        list.forEach(consumer);
        stream.forEach(consumer);
    }

    /**
     * 时间获取
     */
    @Test
    public void nowTimeTest() {
        // 当前精确时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前精确时间：" + now);
        System.out.println("当前精确时间：" + now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + " " + now.getHour() + "-" + now.getMinute() + "-" + now.getSecond());

        // 获取当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println("当前日期：" + localDate);
        System.out.println("当前日期：" + localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth());

        // 获取当天时间
        LocalTime localTime = LocalTime.now();
        System.out.println("当天时间：" + localTime);
        System.out.println("当天时间：" + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());

        // 有时区的当前精确时间
        ZonedDateTime nowZone = LocalDateTime.now().atZone(ZoneId.systemDefault());
        System.out.println("当前精确时间（有时区）：" + nowZone);
        System.out.println("当前精确时间（有时区）：" + nowZone.getYear() + "-" + nowZone.getMonthValue() + "-" + nowZone.getDayOfMonth() + " " + nowZone.getHour() + "-" + nowZone.getMinute() + "-" + nowZone.getSecond());
    }




    /**
     * 时间创建
     */
    @Test
    public void createTime() {
        LocalDateTime ofTime = LocalDateTime.of(2019, 10, 1, 8, 8, 8);
        System.out.println("当前精确时间：" + ofTime);

        LocalDate localDate = LocalDate.of(2019, 10, 01);
        System.out.println("当前日期：" + localDate);

        LocalTime localTime = LocalTime.of(12, 01, 01);
        System.out.println("当天时间：" + localTime);
    }

    /**
     * 日期转换
     */
    @Test
    public void convertTimeTest() {
        LocalDateTime parseTime = LocalDateTime.parse("2019-10-01T22:22:22.222");
        System.out.println("字符串时间转换：" + parseTime);

        LocalDate formatted = LocalDate.parse("20190101", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("字符串时间转换-指定格式：" + formatted);

        // Date 转换成 LocalDateTime
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("Date 转换成 LocalDateTime：" + LocalDateTime.ofInstant(date.toInstant(), zoneId));

        // LocalDateTime 转换成 Date
        LocalDateTime localDateTime = LocalDateTime.now();
        Date toDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime 转换成 Date：" + toDate);

        // 当前时间转时间戳
        long epochMilli = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("当前时间转时间戳：" + epochMilli);
        // 时间戳转换成时间
        LocalDateTime epochMilliTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
        System.out.println("时间戳转换成时间：" + epochMilliTime);
    }

    /**
     * 日期格式化
     */
    @Test
    public void formatTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);
        System.out.println("格式化后：" + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("格式化后：" + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("格式化后：" + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("格式化后：" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 时间比较
     */
    @Test
    public void diffTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yestory = now.minusDays(1);
        System.out.println(now + "在" + yestory + "之后吗?" + now.isAfter(yestory));
        System.out.println(now + "在" + yestory + "之前吗?" + now.isBefore(yestory));

        // 时间差
        long day = yestory.until(now, ChronoUnit.DAYS);
        long month = yestory.until(now, ChronoUnit.MONTHS);
        long hours = yestory.until(now, ChronoUnit.HOURS);
        long minutes = yestory.until(now, ChronoUnit.MINUTES);
        System.out.println("相差月份" + month);
        System.out.println("相差天数" + day);
        System.out.println("相差小时" + hours);
        System.out.println("相差分钟" + minutes);

        // 距离JDK 14 发布还有多少天？
        LocalDate jdk14 = LocalDate.of(2020, 3, 17);
        LocalDate nowDate = LocalDate.now();
        System.out.println("距离JDK 14 发布还有：" + nowDate.until(jdk14, ChronoUnit.DAYS) + "天");
    }

    /**
     * 日期加减
     */
    @Test
    public void calcTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间："+now);
        LocalDateTime plusTime = now.plusMonths(1).plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1);
        System.out.println("增加1月1天1小时1分钟1秒时间后：" + plusTime);
        LocalDateTime minusTime = now.minusMonths(2);
        System.out.println("减少2个月时间后：" + minusTime);
    }

    @Test
    void timeExtra(){
    //    LocalDateTime 本月第一天
        // 当前精确时间
        LocalDateTime now = LocalDateTime.now();
        // 方法1
        LocalDateTime firstDay = now.withDayOfMonth(1);
        System.out.println("本月第一天：" + firstDay);
        // 方法2
        firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天：" + firstDay);
        // LocalDateTime 本月最后一天
        // 当前精确时间
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime lastDay = now1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月最后一天:" + lastDay);
        // LocalDateTime 当天最后一秒
        // 当前精确时间
        LocalDateTime now2 = LocalDateTime.now();
        // 方法1
        LocalDateTime lastSecondOfDay1 = now2.withHour(23).withMinute(59).withSecond(59);
        System.out.println("当天最后一秒：" + lastSecondOfDay1);
        // 方法2
        LocalDateTime lastSecondOfDay2 = LocalDateTime.now().with(LocalTime.MAX);
        System.out.println("当天最后一秒：" + lastSecondOfDay2);
        //    是否闰年
        // 当前精确时间
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println("今年是否闰年：" + Year.isLeap(now3.getYear()));
    }




































































}









































































