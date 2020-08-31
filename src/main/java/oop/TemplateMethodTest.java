package oop;

/**
 *
 * 抽象类的应用，模版方法的设计模式
 */

public class TemplateMethodTest {
    public static void main(String[] args) {
        SubTemplate t = new SubTemplate();
        t.spendTime();
    }
}

abstract class Template{

    // calculate the runtime of code block
    public void spendTime() {
        long start = System.currentTimeMillis();

        this.code(); // 不确定的部分，易变的部分

        long end = System.currentTimeMillis();

        System.out.println("Duration: " + (end - start));
    }

    public abstract void code();
}

class SubTemplate extends Template{
    @Override
    public void code() {
        for (int i = 2; i <= 1000; i++) {
            boolean isFlag = true;
            for(int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j == 0) {
                    isFlag = false;
                    break;
                }
            }
            if(isFlag){
                System.out.println(i);
            }
        }
    }
}