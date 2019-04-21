package org.third.common.utils;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

/**
 * Created by Huailong Tang(Jason) on 2017/9/21.
 */

/**
 * This util is used for build file path by append string
 */
public class PathBuilder {

  private StringBuilder stringBuilder;

  public PathBuilder(String basePath) {
    stringBuilder = new StringBuilder(basePath);
  }

  public PathBuilder addPath(String path){
    path = path.replaceFirst("/|\\\\","");
    stringBuilder.append("/").append(path);
    return this;
  }

  public String buildUnixPath(){
    return stringBuilder.toString();
  }

  public String buildWindowPath() {
    return FilenameUtils.separatorsToWindows(stringBuilder.toString());
  }
}
