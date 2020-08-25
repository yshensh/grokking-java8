package reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    /*
    Class loaders are responsible for loading Java classes during runtime dynamically to the JVM
    */
    @Test
    public void test1() {

        // application or system class loader - loads our own files to classpath
        ClassLoader sysClassLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(sysClassLoader);

        // extension class loader - an extension of the standard core Java classes
        ClassLoader extClassLoader  = sysClassLoader.getParent();
        System.out.println(extClassLoader);

        // bootstrap / primordial class loader - the parent of all the others
        ClassLoader priClassLoader  = extClassLoader.getParent();
        // displays null in the output since the bootstrap class loader is written in native code,
        // not Java - so it doesn't show up as a Java class
        System.out.println(priClassLoader);

    }

    /*
    read properties file
     */
    @Test
    public void test2() throws Exception{
        Properties prop = new Properties();

        // method 1: specify specific path
        FileInputStream fs = new FileInputStream("src/main/resources/jdbc.properties");
        prop.load(fs);
        System.out.println("user: " + prop.getProperty("user") + ", password: " + prop.getProperty("password"));

        // method 2: use ClassLoader to read from default src/main/resources/
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        prop.load(is);
        System.out.println("user: " + prop.getProperty("user") + ", password: " + prop.getProperty("password"));
    }

}
