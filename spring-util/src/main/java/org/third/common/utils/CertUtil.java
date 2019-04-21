package org.third.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.third.common.entity.CommonConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Huailong Tang(Jason) on 2017/10/19.
 */
public class CertUtil {

  public static final Logger logger = LoggerFactory.getLogger(CertUtil.class);

  /**
   * convert x509 crt to jks TrustStore
   * @param crtFolder x509 certificates folder
   * @return TrustStore file
   */
  public static File convertCrtToJks(File crtFolder) {
   String folderFile = crtFolder.getAbsolutePath();
   FileInputStream crtFileStream = null;
   FileOutputStream jksFileStream = null;
   try {
     FileUtils.deleteQuietly(new File(folderFile+File.separator+ CommonConstants.DB_ORACLE_JKS_NAME));
     KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
     char[] password = "changeit".toCharArray();
     keystore.load(null, password);
     CertificateFactory cf = CertificateFactory.getInstance("X.509");

     Arrays.stream(crtFolder.listFiles())
         .filter(file -> "crt".equals(FilenameUtils.getExtension(file.getName())))
         .forEach(file -> {
           logger.info("adding  "+ file.getName() +" to truststore");
           InputStream tmpInputStream = null;
           try {
             tmpInputStream = new FileInputStream(file);
             Certificate certificate = cf.generateCertificate(tmpInputStream);
             keystore.setCertificateEntry(file.getName(), certificate);
           }catch (Exception e){
             logger.error(e.getMessage());
           }finally {
             IOUtils.closeQuietly(tmpInputStream);
           }
         });
     jksFileStream= new FileOutputStream(folderFile+File.separator+ CommonConstants.DB_ORACLE_JKS_NAME);
     keystore.store(jksFileStream, password);
   }catch (Exception e) {

   }finally {
     IOUtils.closeQuietly(crtFileStream);
     IOUtils.closeQuietly(jksFileStream);
     return new File(folderFile+File.separator+ CommonConstants.DB_ORACLE_JKS_NAME);
   }
 }

 public static String readCertificateContent(String path) {
   File crtConfigFolder = new File(path);
   String content = "";
   if(crtConfigFolder.exists() && crtConfigFolder.isDirectory()) {
     try {
       return Arrays.stream(crtConfigFolder.listFiles())
           .filter(crtFile -> "crt".equals(FilenameUtils.getExtension(crtFile.getName())))
           .map(LambdaExceptionUtil
               .rethrowFunction(crtFile -> FileUtils.readFileToString(crtFile, "UTF-8")))
           .collect(Collectors.joining(CommonConstants.DB_CERTIFICATE_DELIMITER));
     }catch (Exception e) {
       logger.error("read database certificates file and convert to string fail , reason {}",e.getMessage());
     }
   }
   return content;
 }

 public static void deleteQuietlyJksFile(File crtFolder){
    FileUtils.deleteQuietly(new File(crtFolder.getAbsolutePath()+File.separator+ CommonConstants.DB_ORACLE_JKS_NAME));
 }


}
