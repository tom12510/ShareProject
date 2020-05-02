import entity.MaxHeap;

import java.util.Random;

/**
 * @author miaomiaole
 * @date 2020/5/2 15:47
 * @DESCRIBE 堆排序  普通队列  优先队列  堆数据结构
 */
public class HeapSort {
    //在N个元素中选出前 M个元素    NlogM
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(100);
        for (int i = 0; i < 100; i++) {
            maxHeap.insert(new Random().nextInt(100));
        }
        System.out.println(maxHeap);
    }
}
