import com.yada.sdk.packages.PackagingException;
import com.yada.sdk.packages.transaction.IMessage;
import com.yada.sdk.packages.transaction.IPacker;
import com.yada.sdk.packages.transaction.jpos.JposPacker;
import org.jpos.iso.ISOException;

import java.io.*;
import java.nio.ByteBuffer;

public class Package {

    public static void main(String[] args) throws ISOException, IOException, PackagingException {
        InputStream is = new FileInputStream(new File("8583gpos.xml"));
        IPacker packer = new JposPacker(11, is, "gis");
        File file = new File("message.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String data;
        int line = 1;
        while ((data = reader.readLine()) != null) {
            if (line % 2 == 0) {
                data = data.substring(4);
            }
            ByteBuffer buffer = ByteBuffer.wrap(HexTools.toByteArray(data));
            IMessage message = packer.unpack(buffer);
            StringBuilder sb = new StringBuilder()
                    .append(line).append("->")
                    .append(message.getFieldString(0)).append(":")
                    .append(message.getFieldString(3)).append(":")
                    .append(message.getFieldString(25)).append(":")
                    .append(message.getFieldString(60));
            System.out.println(sb.toString());

            line++;
        }
    }

}
