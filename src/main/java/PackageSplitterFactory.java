import com.yada.sdk.net.IPackageSplitter;
import com.yada.sdk.net.IPackageSplitterFactory;

import java.nio.ByteBuffer;

public class PackageSplitterFactory implements IPackageSplitterFactory {
    @Override
    public IPackageSplitter create() {
        return new IPackageSplitter() {
            @Override
            public ByteBuffer pack(ByteBuffer newData) {
                ByteBuffer buffer = ByteBuffer.allocate(newData.remaining());
                buffer.put(newData);
                buffer.flip();
                return buffer;
            }

            @Override
            public ByteBuffer getPackage(ByteBuffer newData) {
                ByteBuffer buffer = ByteBuffer.allocate(newData.remaining());
                buffer.put(newData);
                buffer.flip();
                return buffer;
            }
        };
    }
}
