package bytecode;

public class User {
    public User() {
    }

    public User(int age) {
        this.age = age;
    }

    private int age;
    private String name;
    private boolean isBoy;
    private int[] loves;
    private char code;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBoy() {
        return isBoy;
    }

    public void setBoy(boolean boy) {
        isBoy = boy;
    }

    public int[] getLoves() {
        return loves;
    }

    public void setLoves(int[] loves) {
        this.loves = loves;
    }

    public char getCode() {
        return code;
    }

    public void setCode(char code) {
        this.code = code;
    }

    public User changeUser(User user, char charCode) {

        int age = 10;
        age++;
        user.setAge(age);
        String name = "shixu";
        user.setName(name);
        user.setCode(charCode);
        return user;
    }
}
