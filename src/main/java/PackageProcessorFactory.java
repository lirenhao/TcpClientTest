import com.yada.sdk.net.DataTransceiversContext;
import com.yada.sdk.net.IPackageProcessor;
import com.yada.sdk.net.IPackageProcessorFactory;
import com.yada.sdk.packages.PackagingException;
import com.yada.sdk.packages.transaction.IMessage;
import com.yada.sdk.packages.transaction.IPacker;
import com.yada.sdk.packages.transaction.jpos.JposPacker;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class PackageProcessorFactory implements IPackageProcessorFactory {
    @Override
    public IPackageProcessor create() {
        return new IPackageProcessor() {
            @Override
            public void proc(DataTransceiversContext context) {
                try {
                    System.out.println("req:" + ISOUtil.byte2hex(context.getData().array()));
                    context.getDataTransceivers().send(context.getData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
