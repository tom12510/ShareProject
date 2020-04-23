package tom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MyFunInterFaceImpl {
    //无参函数接口
   private List listSupplier(Supplier<List>supplier){
       System.out.println("入参前supplier接口实现方法"+supplier.get());
       List<Integer> objects = new ArrayList<>();
       for (int i = 0; i < 100; i++) {
           objects.addAll(Arrays.asList( new Integer[] {12,3,5,6,7,8,9,0,55,44,33}));
       }
       supplier.get().addAll(objects);
       System.out.println("listSupplier处理后方法 get实现方法+listSupplier方法:"+supplier);
       return objects;
   }

   private Supplier<String> stringSupplier (String t){
       return ()->{
           System.out.println("Supplier实现方法"+t);
           return t;  //Supplier实现方法必须有返回值
       };
   }

    public static void main(String[] args) {
        MyFunInterFaceImpl myFunInterFace = new MyFunInterFaceImpl();
        List<String> strings = new ArrayList<>();
        strings.add("TEST");
        List list = myFunInterFace.listSupplier(() -> strings);//Supplier中get实现方法 必须有返回值
        for (Object o : list) {
            System.out.println(o);
        }

        System.out.println("====================================================");
        Supplier<String> stringSupplier = myFunInterFace.stringSupplier("2223");
        System.out.println(stringSupplier.get());
    }

}
