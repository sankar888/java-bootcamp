package sankar.learn.io;

import com.sun.xml.internal.stream.StaxErrorReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private ServerSocketChannel channel;

    public Server(InetSocketAddress bindTo) throws IOException {
        channel = ServerSocketChannel.open();
        channel.bind(bindTo);
    }
    public static void main(String[] args) throws IOException {
        //todo: check args
        //will listen on localhost , 7070 port
        Server s = new Server(new InetSocketAddress("localhost", 7070));
        s.start();
    }

    private void start() throws IOException {
        SocketChannel conn = null;
        try {
            conn = channel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(conn.read(buffer) != -1) {
                buffer.flip();
            }
        } finally {
            if (null != conn) {
                conn.close();
            }
        }
    }
}
