import java.util.Arrays;
import java.util.List;

class User {
    private int id;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
/**
 * 请按照给出数据，找出
 * 偶数ID
 * 年龄大于24
 * 用户名转为大写
 * 用户名字母倒排序
 * 只输出一个
 * 用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "asd", 23);
        User u2 = new User(12, "bab", 24);
        User u3 = new User(13, "abc", 22);
        User u4 = new User(14, "bcd", 28);
        User u5 = new User(16, "cde", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream().filter(t->{
            return t.getId()%2==0;
        }).filter(t->{
            return t.getAge()>24;
        }).map(f->{
            return f.getUserName().toUpperCase();
        }).sorted((o1,o2)->{
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);
    }
}
