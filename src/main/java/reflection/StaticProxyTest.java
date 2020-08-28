package reflection;

/**
 * Proxy Design Pattern: application on using interface
 * Static Proxy: proxy and proxy classes are determined during compilation
 *
 * Proxy, in short, both proxy and delegate classes implement the same interface,
 * proxy classes execute methods in delegate classes implemented from the same interface instead of delegate classes,
 * and proxy classes can do other appropriate processing before and after they execute methods instead of delegate classes.
 */

public class StaticProxyTest {

    public static void main(String[] args) {
        Server server  = new Server();
        ProxyServer proxyServer = new ProxyServer(server);
        proxyServer.browse();
    }
}

interface Network {
    public void browse();
}

// delegate class
class Server implements Network {
    @Override
    public void browse() {
        System.out.println("Server connection to network.");
    }
}

// proxy class
class ProxyServer implements Network {

    private Network network;

    public ProxyServer(Network network) {
        this.network = network;
    }

    public void check(){
        System.out.println("Check connections (Proxy Server).");
    }

    @Override
    public void browse() {
        check();
        network.browse();
    }
}



