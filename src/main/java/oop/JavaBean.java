package oop;

/**
 * JavaBeans are classes that encapsulate many objects into a single object (the bean)
 *  - serializable
 *  - a zero-argument constructor
 *  - allow access to properties using getting and setter methods
 * The name "Bean" was  given to encompass this standard, which aims to create reusable software components for Java.
 *
 */

public class JavaBean {

    private int id;
    private String name;

    public JavaBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
