package tom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ProjectTest {

    public static void main(String[] args) {
        method01().accept(method().get());
        System.out.println("======================");
        method02(LocalDate::now).accept(null);
        System.out.println("====================");
        LocalDate date = method03((x) -> {  //确定入参x  实现acapt方法
            System.out.println(x);
        }).get();
        System.out.println("===================");
        System.out.println("这是判断结果:"+method04((x) -> x < 50 ? false : true).get());
        System.out.println("====================");
        System.out.println("这是计算结果:"+method05(() -> 100).apply(120));
    }

    /**
     * Supplier 创建者  实现 T get 方法(确定返回对象T)
     */
   private static Supplier<LocalDate> method (){
        return ()->LocalDate.now(); //Supplier 接口实现get方法
    }

    /**
     * 消费者  实现 accept(T t) 方法  确定传入参数
     */
    private static Consumer<LocalDate> method01(){
       return now->{
           System.out.println(now); //Consumer 实现 accept方法  单参
        };
    }


    private static Consumer<LocalDate> method02(Supplier<LocalDate> supplier){
       return x->{
           System.out.println(supplier.get());  //实现 accept方法 返回Consumer接口对象  确定参数 并且使用Consumer对象调用
       };
    }

    private static Supplier<LocalDate> method03(Consumer<LocalDate> consumer){
        consumer.accept(LocalDate.now());
        return LocalDate::now;
    }

   private static Supplier<Integer> method04(Predicate<Integer> predicate){
       Integer random = new Random().nextInt(100);
       System.out.println("获得随机数"+random);
       return ()-> {if(predicate.negate().test(random)){return random;}else {
           return 0;
       }};
   }
    /**
     * Function  入参 和 传参
     */
   private static Function<Integer, BigDecimal> method05(Supplier<Integer> supplier){
       System.out.println("这是入参对应T:"+supplier.get());
       return (x)->{
           System.out.println("对应applay:"+x);
           return new BigDecimal(supplier.get()).add(new BigDecimal(x));};  //实现apply方法
   }


}
