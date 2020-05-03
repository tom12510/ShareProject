package tom;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author miaomiaole
 * @date 2020/5/3 11:56
 * @DESCRIBE 策略模式  使用optional类对变量进行非空判断
 */
public class emptyTest {

    private Integer integer1 = 10;

    private LocalDate localDate = LocalDate.now();
    private String strNull = null;
    private String strNull1 = null;

    private String strSprace = "  ";
    private String strSprace1 = "  ";
    private String strSprace3 = strSprace1;



    @Test
    public void optionalNullTest() {
        //使用optional判断 NEP
        //java.lang.NullPointerException
        Optional<String> strNull = Optional.of(this.strNull);

    }

    @Test
    public void optionalNullTest1(){
        //使用optional判断 NEP
        Optional<String> strNull = Optional.ofNullable(this.strNull);
        Optional<String> strNull1 = Optional.ofNullable(this.strNull1);
        //容器Optional中存入null 判断为true
        System.out.println("ofNullable方法加入容器判断:" + (strNull == strNull1));

        Optional<String> strSprace = Optional.ofNullable(this.strSprace);
        Optional<String> strSprace1 = Optional.ofNullable(this.strSprace1);
        //容器Optional中存入两个相同变量的值  为false
        System.out.println("ofNullable方法加入容器判断:" + (strSprace == strSprace1));
        // 容器对象引用相同变量为false
        Optional<String> strSprace3 = Optional.ofNullable(this.strSprace3);
        System.out.println("ofNullable方法加入容器判断:" + (strSprace3 == strSprace1));
        //使用equals判断 true
        System.out.println("ofNullable方法加入容器使用equals判断:" + (strSprace3.equals(strSprace1)));
        //true
        System.out.println("ofNullable方法加入容器使用equals判断:" + (strSprace.equals(strSprace)));
        //empty()方法的使用   false    Optional.empty()返回为Optional容器 内容为null
        System.out.println("empty()方法的使用:"+Optional.empty() == null);
        //true
        System.out.println("empty()方法的使用:"+(Optional.ofNullable(null) == Optional.empty()));

    }

    @Test
    public void isPresentTest(){
        Optional<Integer> integer = Optional.ofNullable(1);
        Optional<Integer> integer1= Optional.ofNullable(this.integer1);
        Optional<Integer> integer2 = Optional.ofNullable(null);
        System.out.println(integer.isPresent());
        System.out.println(integer2.isPresent());

        //ifPresent 参数为消费者 Consumer 使用
        //ifPresent(Consumer consumer)对象不是null 调用 consumer中方法实现accept方法 操作Option中参数
        integer1.ifPresent((x)->{
            System.out.println("如果不为空则打印:"+x);
        });
        integer2.ifPresent((x)->{
            System.out.println("如果为空则不执行");
        });
    }

    @Test
    public void orElseTest(){
        Optional optional = Optional.ofNullable(localDate);
        Optional<String> strNull = Optional.ofNullable(this.strNull);
        //optional中的值为null则返回 orElse()中参数
        System.out.println(optional.orElse(1));
        System.out.println(strNull.orElse("为空返回的值"));

        //orElseGet(Supplier supplier) 中返回生产者对象
        Object o = Optional.ofNullable(null).orElseGet(LocalDate::now);
        System.out.println("使用ofNullable方法返回的参数"+o);

        //异常处理 orElseThrow()  使用 Supplier supplier 生产对象
        Optional<Integer> integer = Optional.ofNullable(1 / 0);
          //TODO
    }

    @Test
    public void filterTest(){
        Integer[] integers = new Integer[100];
        for (int i = 0; i < 60; i++) {
            integers[0] = 1+i;
        }
        Optional integerOptional = Optional.ofNullable(integers);
        Optional<Integer> option1 = Optional.ofNullable(null);
        //filter 对Optional容器中的元素过滤
        //Optional optional = integerOptional.filter();


    }
}
