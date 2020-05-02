package utils;

/**
 * @author miaomiaole
 * @date 2020/5/2 16:20
 * @DESCRIBE
 */
public class changeArray {

    public static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
