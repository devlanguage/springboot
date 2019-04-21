package org.third.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by Huailong Tang(Jason) on 2017/9/16.
 */
public class PemReader extends BufferedReader {
  private static final String BEGIN = "-----BEGIN ";
  private static final String END  = "-----END ";

  public PemReader(Reader reader) {
    super(reader);
  }

  public byte[] readPemObject() throws IOException{
    String var1;
    for(var1 = this.readLine(); var1!=null && !var1.startsWith("-----BEGIN "); var1 = this.readLine()) {
      ;
    }
    if(var1 != null) {
      var1 = var1.substring("-----BEGIN ".length());
      int var2 = var1.indexOf(45);
      String var3 = var1.substring(0,var2);
      if(var2 > 0) {
        return this.loadObject(var3);
      }
    }
    this.close();
    return null;
  }

  private byte[] loadObject(String var1) throws IOException {
    String var3 = "-----END " + var1;
    StringBuffer var4 = new StringBuffer();
    String var2;
    while ((var2 = this.readLine()) != null) {
      if(var2.indexOf(":") < 0) {
        if(var2.indexOf(var3) != -1) {
          break;
        }
        var4.append(var2.trim());
      }
    }
    if(var2 == null) {
      this.close();
      throw new IOException(var3 + " not found");
    }else {
      this.close();
      return Base64.getDecoder().decode(var4.toString());
    }
  }
}
