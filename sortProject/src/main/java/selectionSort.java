import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import utils.changeArray;

import java.util.Arrays;
import java.util.Random;

/**
 * @author miaomiaole
 * @date 2020/4/28 22:00
 * @DESCRIBE 选择排序  插入排序  冒泡排序  归并排序  O(n²)  O(n *log N) 的排序算法
 */
@Data
public class selectionSort {
    private Integer integerArr[];

    @Before
    public void init() {
        integerArr = new Integer[1000000];
        for (Integer i = 0; i < integerArr.length; i++) {
            int random = new Random().nextInt(3999999 - 2999 + 1) + 2999;
            integerArr[i] = random;
        }

    }

    // 选择排序  执行次数 n²
    private static void selectionSort(Integer[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            // 外循环取出一个数逐个比较内循环元素
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[min]) {  //逐个比较  比较次数 n²
                    min = j;
                }
            }
            //交换顺序  c=a  a = b  b =c 方式 int temp = arr[i];  arr[i] = arr[min];    arr[min] = temp;
            //交换顺序  A=A+B  B=A-B  A=A-B 方式
            changeArr(arr, i, min);
        }

    }

    private static void changeArr(Integer[] arr, int i, int min) {
       /* arr[i] = arr[i] + arr[min];
        arr[min] = arr[i] - arr[min];
        arr[i] = arr[i] - arr[min];*/
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }

    @Test
    //选择排序  执行时间复杂度  O²   10000个元素 132   100000个元素 21977
    public void selectionSortTest() {
        long start = System.currentTimeMillis();
        selectionSort(integerArr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    //插入排序  相邻元素比较交换位置 倒入排序
    public static void insertSort(Integer[] arr) {
        int length = arr.length;
       /*
         从1开始排序 倒序排序  比较外循环索引前一个元素 交换相邻元素位置
         for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                 //赋值的操作比判断操作费时 每次交换有三次赋值
                 changeArr(arr,arr[j],arr[j-1]);
            }
        }
        }*/
        // 执行次数 小于 O²
        for (int i = 1; i < length; i++) {
            Integer min = arr[i];  //使用临时变量
            int j;
            //第二层元素 提前终止
            for (j = i; j > 0 && arr[j - 1] > min; j--) {
                arr[j] = arr[j - 1];  // 交换位置 将下一较小的元素向前挪一位置  元素i放在临时变量 min中
            }
            arr[j] = min;  //  j位置 i-1
        }
    }

    @Test
    // 执行次数 小于 O²  10000个元素50   100000 个元素 7522  1000000(百万级别) 10min以上 执行次数 1E12
    public void insertSortTest() {
        long start = System.currentTimeMillis();
        insertSort(integerArr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    //冒泡排序 比较相邻的元素  执行次数 O²
    public static void bubbleSort(Integer[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            //复杂  O(N²)  O(1)  0索引比较前一个元素  满足条件交换位置
            //总是比较后一个相邻元素
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    changeArr(arr, j, j + 1);
                }
            }
        }
    }

    @Test
    //冒泡排序  执行时间O²  10000个元素 122  100000个元素 81502
    public void bubbleSortTest() {
        long start = System.currentTimeMillis();
        bubbleSort(integerArr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    /**
     * @param arr   排序数组
     * @param start 排序开始包含
     * @param end   排序结尾
     */
    public static void insertSort1(Integer arr[], int start, int end) {

        for (int i = start + 1; i <= end; i++) {
            Integer min = arr[i];
            int j;
            for (j = i; j > i && arr[j - 1] > min; j++) {
                arr[j] = arr[j - 1];
            }
            arr[j] = min;
        }
    }

    //O(N*logN) 排序算法
    //归并排序   使用递归进行分组排序----->递归的执行时间为 log级别
    //在索引 start 开始位置 到 end位置排序
    public static void mergeSort(Integer[] arr, Integer start, Integer end) {
        //if (start >= end) return;
        //当分组小于一定数量时 使用插入排序算法完成对子数组的排序
        if (end - start <= 200) {
            insertSort1(arr, start, end);
            return;
        }
        //寻找中间索引  整数
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid); //arr以排序
        mergeSort(arr, mid + 1, end); //arr已排序
        //每次进行递归 都把元素分组 当两个子数组的前元素比后元素大才进行归并操作
        //此判断使用于部分有序的排序
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, start, mid, end);  // 归并操作
        }
    }

    //将两组数组进行归并  arr[start...mid]和 arr[mid+1...end]  闭区间
    //arr为排序数组  归并操作
    private static void merge(Integer[] arr, Integer start, int mid, Integer end) {
        //定义临时数组变量 复制整个排序数组
        Integer[] aux = new Integer[end - start + 1];
        for (int i = start; i <= end; i++) {
            aux[i - start] = arr[i];
        }
        //两个子数组的开头位置(排好序的子数组)
        int leftIndex = start, rightIndex = mid + 1;
        //两个数组相同偏移比较  变量i为赋值数组索引位置  index为前半索引位置  index1为后半索引位置
        for (Integer i = start; i < end; i++) {
            //mid 防止索引越界
            if (leftIndex > mid) {
                arr[i] = aux[leftIndex - start];
                leftIndex++;
            } else if (rightIndex > end) {
                arr[i] = aux[i - start];
                rightIndex++;
            } else if (aux[leftIndex - start] < aux[rightIndex - start]) { //index-start 为要排序的偏移量
                arr[i] = aux[leftIndex - start];//比较大小 将偏移量索引元素赋值到arr排序索引中
                leftIndex++;
            } else {
                arr[i] = aux[rightIndex - start];
                rightIndex++;
            }
        }
    }

    @Test
    // 10000个元素 44    100000个元素   362    6000000个元素1430
    public void mergeSortTest() {
        long start = System.currentTimeMillis();
        mergeSort(integerArr, 0, integerArr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    @Test
    //Arrays工具类方法 10000个元素 110   1000000 个元素 591  使用的归并排序 同上
    public void javaArrSort() {
        long start = System.currentTimeMillis();
        Arrays.sort(integerArr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    //自底向上的归并排序 迭代  对链表排序优化
    public static void mergeSortBU(Integer[] arr) {
        int length = arr.length;
        int mid = length / 2;
        //使用 sz+=sz 迭代循环  1+2+4 形式增加迭代 循环要归并的数组
        for (int sz = 1; sz <= length; sz += sz) {
            for (int i = 0; i < length; i += sz + sz) { // 2sz~3sz-1
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, length - 1));
            }
        }
    }

    @Test
    // 10000个元素 11  100000 个元素  61
    public void mergeSortBUTest() {
        long start = System.currentTimeMillis();
        mergeSortBU(integerArr);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }

    //快速排序 使用递归方式 进行排序  QuickSort  [start,end]
    public static void quickSort(Integer[] arr, int start, int end) {
        if (start >= end) { //递归到底
            return;
        }
        // 中间值
        int p = partition(arr, start, end);
        quickSort(arr, start, p - 1);
        quickSort(arr, p + 1, end);
    }

    /**
     * @param arr        排序数组
     * @param leftIndex  子数组
     * @param rightIndex 子数组
     * @return int 返回p 使的 arr[leftIndex...p-1] <arr[p]   arr[p+1...rightIndex]> arr[p]
     */
    private static int partition(Integer[] arr, int leftIndex, int rightIndex) {
        //计算临时变量 temp 为partition应该在的位置
        Integer temp = arr[leftIndex];
        int partitonIndex = leftIndex;
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (arr[i] < temp) {
                changeArray.swap(arr,i,partitonIndex+1);
                partitonIndex++;
            }
        }
        changeArray.swap(arr,  partitonIndex,leftIndex);
        return partitonIndex;
    }

    @Test
    // 百万级别的速度  296
    public void quickSortTest() {
        long start = System.currentTimeMillis();
        quickSort(integerArr, 0, integerArr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start));
        System.out.println(StringUtils.join(integerArr, " "));
    }


}
