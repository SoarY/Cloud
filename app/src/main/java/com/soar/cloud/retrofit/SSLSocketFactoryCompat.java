package com.soar.cloud.retrofit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * NAME：YONG_
 * Created at: 2019/2/18
 * Describe:
 */
public class SSLSocketFactoryCompat extends SSLSocketFactory {
    private static final String[] TLS_V12_ONLY = {"TLSv1.2"};

    private final SSLSocketFactory delegate;

    public SSLSocketFactoryCompat() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, null, null);
        delegate = sc.getSocketFactory();
    }

    public SSLSocketFactoryCompat(SSLSocketFactory delegate) {
        if (delegate == null) {
            throw new NullPointerException();
        }
        this.delegate = delegate;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    private Socket enableTls12(Socket socket) {
        if (socket instanceof SSLSocket)
            ((SSLSocket) socket).setEnabledProtocols(TLS_V12_ONLY);
        return socket;
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTls12(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return enableTls12(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return enableTls12(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return enableTls12(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTls12(delegate.createSocket(address, port, localAddress, localPort));
    }
}
