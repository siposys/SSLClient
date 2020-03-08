package Client;
//package to.noc.sslping;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.OutputStream;


/*
 *  Simple tool to test an SSL handshake to a remote server that does not assume that
 *  the remote end is an HTTP(S) server.
 */
public class TestClient {

    public static void main(String[] args) {
        
        try {

            //String hostname = args[0];
        	String hostname = "127.0.0.1";
            //int port = Integer.parseInt(args[1]);
        	int port=4443;
            System.out.println("About to connect to '" + hostname + "' on port " + port);

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);

            // Hostname verification is not done by default in Java with raw SSL connections.
            // The next 3 lines enable it.
           // SSLParameters sslParams = new SSLParameters();
           // sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            //sslsocket.setSSLParameters(sslParams);

            // we only send 1 byte, so don't buffer
            sslsocket.setTcpNoDelay(true);

            // Write a test byte to trigger the SSL handshake
            OutputStream out = sslsocket.getOutputStream();
            out.write(1);

            // If no exception happened, we connected successfully
            System.out.println("Successfully connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}