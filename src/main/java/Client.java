import com.yada.sdk.net.IPackageSplitterFactory;
import com.yada.sdk.net.TcpClient;
import com.yada.sdk.packages.PackagingException;
import com.yada.sdk.packages.transaction.IMessage;
import com.yada.sdk.packages.transaction.jpos.JposPacker;
import org.jpos.iso.ISOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class Client {

    public static void main(String[] args) throws IOException, ISOException, PackagingException {
        InetSocketAddress address = new InetSocketAddress("localhost", 4321);
        IPackageSplitterFactory psf = new PackageSplitterFactory();
        TcpClient client = new TcpClient(address, psf, 0);

        client.open();

        InputStream is = new FileInputStream(new File("8583gpos.xml"));
        JposPacker packer = new JposPacker(11, is, "gis");

        String data = "60006582A261010003040106207020068108C082191662109470000000130000000000000000110067490520000100080104170230363134303934343832393837363030323432353130343736373031313030303030363730323039319F3303E049C8950508000810009F3704ED5D0FEA9F1E0832383939373334359F101307000103602010010A010000000000642959439F26086088555B13AA10B19F3602046382027C00DF310520000000009F1A0207029A03180614001300000043951500001600004300674806143935424541363739";
        byte[] bytes = HexTools.toByteArray(data);
        ByteBuffer reqBuffer = ByteBuffer.allocate(bytes.length).put(bytes);
        reqBuffer.flip();
        IMessage req = packer.unpack(reqBuffer);
        System.out.println(req.toString());

        ByteBuffer respBuffer = client.send(packer.pack(req));
        IMessage resp = packer.unpack(respBuffer);
        System.out.println(resp.toString());

        client.close();
    }
}
