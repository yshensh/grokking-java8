package lambda;

/**
 * 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口。
 * 我们可以在一个接口上使用 @FunctionalInterface 注解，这样做可以检查它是否是一个函数式接口。
 */

@FunctionalInterface
public interface MyFunctionalInterface {
    void method();
}
