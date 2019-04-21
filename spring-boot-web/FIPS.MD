keytool -keystore MyLuggage.p12 -storepass 123456 -storetype pkcs12
   -alias "......"
   -genkeypair -keyalg RSA -keysize typically_2048_or_3072 -sigalg SHA256withRSA

   -ext "KeyUsage=dataEncipherment,digitalSignature,keyEncipherment"
   -startdate ....
   -dname "......."
 
 https://docs.microsoft.com/en-us/sql/connect/jdbc/fips-mode?view=sql-server-2017
   
   java.lang.SecurityException: Algorithm not allowable in FIPS140 mode: PBE/PKCS12/SHA1/RC2/CBC/40
    at ......
    at java.security.KeyStore.load(Unknown Source)
	
	public boolean isFIPS() throws Exception {
    Provider jsse = Security.getProvider("SunJSSE");
    return jsse != null && jsse.getInfo().contains("FIPS");
}
https://csrc.nist.gov/projects/cryptographic-module-validation-program/validated-modules/search

===mf
https://docs.microfocus.com/SM/9.61/Codeless/Content/security/tasks/configure_Java_for_fips_mode.htm
security.provider.1=com.rsa.jsafe.provider.JsafeJCE
security.provider.5=com.sun.net.ssl.internal.ssl.Provider JsafeJCE
com.rsa.cryptoj.fips140initialmode=FIPS140_SSL_MODE
com.rsa.cryptoj.kat.strategy=on.load
com.rsa.crypto.default.random=HMACDRBG256
keystore.type=PKCS12

===IDM  https://www.ibm.com/support/knowledgecenter/SSSHTQ_8.1.0/com.ibm.netcool_OMNIbus.doc_8.1.0/omnibus/wip/install/task/omn_con_configuringjreforfips.html
 security.provider.1=com.ibm.fips.jsse.IBMJSSEFIPSProvider
security.provider.2=com.ibm.crypto.fips.provider.IBMJCEFIPS
security.provider.3=com.ibm.jsse2.IBMJSSEProvider2
security.provider.4=com.ibm.crypto.provider.IBMJCE
security.provider.5=com.ibm.security.jgss.IBMJGSSProvider
security.provider.6=com.ibm.security.cert.IBMCertPath
security.provider.7=com.ibm.security.sasl.IBMSASL
security.provider.8=com.ibm.xml.crypto.IBMXMLCryptoProvider
security.provider.9=com.ibm.xml.enc.IBMXMLEncProvider
security.provider.10=com.ibm.security.jgss.mech.spnego.IBMSPNEGO
security.provider.11=sun.security.provider.Sun
security.provider.12=com.ibm.security.cmskeystore.CMSProvider

Set the default key and trust manager factory algorithms for the javax.net.ssl package:

ssl.KeyManagerFactory.algorithm=IbmX509
ssl.TrustManagerFactory.algorithm=IbmX509

Set the default SSLSocketFactory and SSLServerSocketFactory provider implementations for the javax.net.ssl package:

ssl.SocketFactory.provider=com.ibm.jsse2.SSLSocketFactoryImpl
ssl.ServerSocketFactory.provider=com.ibm.jsse2.SSLServerSocketFactoryImpl