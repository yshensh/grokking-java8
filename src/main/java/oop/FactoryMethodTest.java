package oop;

/**
 * Factory Method Design Pattern: application on using interface
 *
 * Defining a separate factory method (in interface) for creating an object to create the object
 *
 */

public class FactoryMethodTest {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}


interface Sender {
    public void Send();
}


class MailSender implements Sender{
    @Override
    public void Send() {
        System.out.println("this is mail sender!");
    }
}


class SmsSender implements Sender{
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}


interface Provider {
    public Sender produce();
}


class SendMailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}


class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
