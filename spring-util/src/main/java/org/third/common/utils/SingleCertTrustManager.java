package org.third.common.utils;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.third.common.entity.CommonConstants;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Huailong Tang(Jason) on 2018/1/2.
 */
public class SingleCertTrustManager implements X509TrustManager {

  private X509TrustManager trustManager;
  private X509Certificate[] cert;

  public SingleCertTrustManager(InputStream[] in) {
    init(in);
  }

  public SingleCertTrustManager(String certificates) {
    String[] crts = certificates.split(CommonConstants.DB_CERTIFICATE_DELIMITER);
    if(crts.length > 0) {
      InputStream[] inputStreams = Arrays.stream(crts)
          .map(crt -> new ByteArrayInputStream(crt.getBytes()))
          .toArray(InputStream[]::new);
      init(inputStreams);
    }
  }

  private void init(InputStream[] ins) {
    try {
      KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
      try {
        ks.load(null);
      } catch (Exception e) {

      }
      CertificateFactory cf = CertificateFactory.getInstance("X509");
      cert = new X509Certificate[ins.length];
      for(int i=0; i < ins.length; i++){
        cert[i] = (X509Certificate)cf.generateCertificate(ins[i]);
        ks.setCertificateEntry(UUID.randomUUID().toString(),cert[i]);
      }
      TrustManagerFactory tmf = TrustManagerFactory
          .getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(ks);
      for (TrustManager tm : tmf.getTrustManagers()) {
        if (tm instanceof X509TrustManager) {
          trustManager = (X509TrustManager) tm;
          break;
        }
        if (trustManager == null) {
          throw new RuntimeException("No X509TrustManager found");
        }
      }
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {
    trustManager.checkClientTrusted(x509Certificates,s);
  }

  @Override
  public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {
    trustManager.checkServerTrusted(x509Certificates,s);
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return cert;
  }
}
