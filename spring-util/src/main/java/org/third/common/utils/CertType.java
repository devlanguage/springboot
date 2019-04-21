package org.third.common.utils;

/**
 * Created by Huailong Tang(Jason) on 2017/9/17.
 */

/**
 * store the certificate (JWT Token) status, only set once
 */
public class CertType {
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = this.type == null ? type:this.type;
  }
}
