package reflection;

@MyAnnotation(value="hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface{
    private String name;
    public int age;
    public int id;

    public Person() {

    }

    @MyAnnotation(value="abc")
    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation) {
        System.out.println("Nation: " + nation);
        return nation;
    }

    private static void showDesc() {
        System.out.println("description");
    }

    public String display(String hobby) {
        return hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("person");
    }
}
