/*The MIT License (MIT)

Copyright (c) <2013> <Keven "Varonth" Schulz>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package SSLHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;


public class SingleCertificateSSLSocketFactoryFactory {
    
    protected SSLContext sslCon;
    
    public SingleCertificateSSLSocketFactoryFactory(InputStream certStream, String keyName ,String certFactoryType, String trustManagerType) 
     throws CertificateException, NoSuchAlgorithmException,
     KeyStoreException, IOException, KeyManagementException{
        CertificateFactory cf = CertificateFactory.getInstance(certFactoryType);
        Collection c = cf.generateCertificates(certStream);
        certStream.reset();
        Certificate[] certs = new Certificate[c.toArray().length];
        if (c.size() == 1) {
            Certificate cert = cf.generateCertificate(certStream);
            certs[0] = cert;
        }
        else {
            certs = (Certificate[])c.toArray();
        }
        TrustManagerFactory tf = TrustManagerFactory.getInstance(trustManagerType);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null);
        for (int i = 0; i<certs.length;i++) {
            ks.setCertificateEntry(keyName+i, certs[i]);
        }
        tf.init(ks);
        TrustManager[] tm = tf.getTrustManagers();
        this.sslCon = SSLContext.getInstance("SSL");
        sslCon.init(null, tm, new SecureRandom());        
    }
    
    public SSLSocketFactory getSSLSocketFactory() {
        return this.sslCon.getSocketFactory();
    }
    
}
