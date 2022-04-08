package cn.geny.health.bo;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/4 10:44
 */
public class Student extends Person{
    private String sid;
    private String num;

    public Student(int age, int gender, String sid, String num) {
        super(age, gender);
        this.sid = sid;
        this.num = num;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Student(){
        super();
    }
}
