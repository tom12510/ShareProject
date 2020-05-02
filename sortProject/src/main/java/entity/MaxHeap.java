package entity;

import lombok.Data;
import utils.changeArray;

/**
 * @author miaomiaole
 * @date 2020/5/2 16:02
 * @DESCRIBE 堆容器 等效于 List
 */
@Data
public class MaxHeap {
    private Integer[] data;
    private Integer count;
    private Integer capacity;

    public MaxHeap(Integer capacity) {
        this.data = new Integer[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public Integer size() {
        return count;
    }

    Boolean isEmpty() {
        return count == 0;
    }

    public void insert(Integer item) {
        //防止添加元素越界  c++中断言函数  防止程序报错 提前终止
        assert (this.count +1 <= capacity);
        data[this.count + 1] = item;
        this.count++;
        shifUp(this.count);
    }

    private void shifUp(Integer count) {
        while ( count>1 && data[count / 2] < data[count]) {
            changeArray.swap(data,data[count/2],data[count]);
             count/=2;
        }
    }

}
