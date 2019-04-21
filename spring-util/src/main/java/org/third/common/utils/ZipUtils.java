package org.third.common.utils;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private ZipUtils(){};

    public static Boolean createZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos);
            return true;
        } catch (FileNotFoundException e) {
             System.out.println("创建ZIP文件失败FileNotFoundException1111111111");
             return false;
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                 System.out.println("创建ZIP文件失败IOException1111111");
                 return false;
            }

        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                for(File f:files){
                	if(!"offline_sync_tools.zip".equals(f.getName()) && !"offline_image_download".equals(f.getName())){
                		f.setExecutable(true);
                		 writeZip(f, parentPath, zos);
                	}

                }
            }else{
                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                     System.out.println("创建ZIP文件失败 FileNotFoundException");
                } catch (IOException e) {
                     System.out.println("创建ZIP文件失败IOException");
                }finally{
                    try {
                        if(fis!=null){
                            fis.close();
                        }
                    }catch(IOException e){
                         System.out.println("创建ZIP文件失败finallyIOException");
                    }
                }
            }
        }
    }


    public static boolean createZipWithPermission(String path, String zipPath, List<String> retainExecList, Boolean isUpdate) {
        boolean result = true;
        ArchiveOutputStream zipStream = null;
        try {
            zipStream = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, new FileOutputStream(new File(zipPath)));
            writeToZipWithPermission(new File(path), zipStream, retainExecList,isUpdate);
            zipStream.closeArchiveEntry();
            zipStream.finish();
            zipStream.flush();
            zipStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            IOUtils.closeQuietly(zipStream);
            return result;
        }

    }

    private static void writeToZipWithPermission(File file, ArchiveOutputStream zipStream, List<String> retainExecList,Boolean isUpdate) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                	if(!isUpdate){
                		if ("image-list.json".equals(f.getName())||"image-set.json".equals(f.getName()) || "downloadimages.sh".equals(f.getName()) || "jq".equals(f.getName())) {
                            writeToZipWithPermission(f, zipStream, retainExecList,isUpdate);
                        }
                	}else{
                		if ("image-list.json".equals(f.getName()) || "downloadimages.sh".equals(f.getName()) || "jq".equals(f.getName())||"deployments.json".equals(f.getName())) {
                            writeToZipWithPermission(f, zipStream, retainExecList,isUpdate);
                        }
                	}
                    
                }
            }else {
                ZipArchiveEntry archiveEntry = new ZipArchiveEntry(file.getPath());
                if (retainExecList.contains(file.getName())) {
                    archiveEntry.setUnixMode(0755);
                }
                zipStream.putArchiveEntry(archiveEntry);
                IOUtils.copy(new FileInputStream(file), zipStream);
            }
        }
    }

}