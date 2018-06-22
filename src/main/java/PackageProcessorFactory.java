import com.yada.sdk.net.DataTransceiversContext;
import com.yada.sdk.net.IPackageProcessor;
import com.yada.sdk.net.IPackageProcessorFactory;
import com.yada.sdk.packages.PackagingException;
import com.yada.sdk.packages.transaction.IMessage;
import com.yada.sdk.packages.transaction.IPacker;
import com.yada.sdk.packages.transaction.jpos.JposPacker;
import org.jpos.iso.ISOException;

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
                ByteBuffer buffer = context.getData();
                try {
                    InputStream is = new FileInputStream(new File("8583gpos.xml"));
                    IPacker packer = new JposPacker(11, is, "gis");
                    IMessage message = packer.unpack(buffer);
                    System.out.println(message.getFieldString(0));

                    buffer.flip();
                    context.getDataTransceivers().send(buffer);
                } catch (InterruptedException | FileNotFoundException | ISOException | PackagingException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
