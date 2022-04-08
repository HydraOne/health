package cn.geny.health.bo;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/4 10:43
 */
public class Person {
    private int age;
    private int gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Person(int age, int gender) {
        this.age = age;
        this.gender = gender;
    }

    public Person() {

    }
}
