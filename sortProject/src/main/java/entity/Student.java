package entity;

import lombok.Data;

/**
 * @author miaomiaole
 * @date 2020/4/28 22:09
 * @DESCRIBE
 */
@Data
public class Student {
    private Integer age;
    private String name;
    private Integer source;

    public Student(Integer age, String name, Integer source) {
        this.age = age;
        this.name = name;
        this.source = source;
    }
}
