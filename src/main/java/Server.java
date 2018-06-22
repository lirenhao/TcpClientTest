import com.yada.sdk.net.IPackageProcessorFactory;
import com.yada.sdk.net.IPackageSplitterFactory;
import com.yada.sdk.net.TcpService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {
        IPackageSplitterFactory psf = new PackageSplitterFactory();
        IPackageProcessorFactory ppf = new PackageProcessorFactory();
        TcpService service = new TcpService("demo", psf, ppf, 1000);

        InetSocketAddress address = new InetSocketAddress("localhost", 4321);
        service.listen(address);
    }
}
