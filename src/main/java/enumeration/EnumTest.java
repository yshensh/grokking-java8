package enumeration;

/**
 * 一、枚举类的使用
 * 1.枚举类的理解：类的对象只有有限个，确定的。我们称此类为枚举类
 * 2.当需要定义一组常量时，强烈建议使用枚举类
 * 3.如果枚举类中只有一个对象，则可以作为单例模式的实现方式。
 *
 * 二、如何定义枚举类
 * 方式一：JDK 5.0之前，自定义枚举类
 * 方式二：JDK 5.0，使用enum关键字定义枚举类
 *
 * 三、Enum类中的常用方法：
 *    values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
 *    valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalArgumentException。
 *    toString()：返回当前枚举类对象常量的名称
 *
 */

public class EnumTest {
    public static void main(String[] args) {
        System.out.println("**************** 自定义枚举类 ****************");
        OldSeason spring = OldSeason.SPRING;
        System.out.println(spring);
        System.out.println(OldSeason.class.getSuperclass());
        System.out.println("**************** 使用enum关键字定义枚举类 ****************");
        NewSeason summer = NewSeason.SUMMER;
        System.out.println(summer);
        System.out.println(NewSeason.class.getSuperclass());
        NewSeason[] values = NewSeason.values();
        for (NewSeason value : values) {
            System.out.println(value);
        }
    }
}

//方式一：JDK 5.0之前，自定义枚举类: 定义的枚举类默认继承于java.lang.Object类
class OldSeason {
    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private OldSeason(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象：public static final的
    public static final OldSeason SPRING = new OldSeason("Spring","warm");
    public static final OldSeason SUMMER = new OldSeason("Summer","hot");
    public static final OldSeason AUTUMN = new OldSeason("Autumn","cool");
    public static final OldSeason WINTER = new OldSeason("Winter","cold");

    //4.获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}


// 方式二：JDK 5.0 之后，使用enum关键字定义枚举类: 定义的枚举类默认继承于java.lang.Enum类
enum NewSeason{
    //0.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SPRING("Spring", "warm"),
    SUMMER("Summer", "hot"),
    AUTUMN("Autumn", "cool"),
    WINTER("Winter", "cold");

    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private NewSeason(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}

