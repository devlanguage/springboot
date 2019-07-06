package org.third.spring.boot.hello.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    // 1.ModelAndView 这个也是用的最多的类型，通过构造函数可以指定返回页面的名称，
    // 也可以通过方法setViewName()来设置需要跳转的页面，例如如下的例子，这里面不但返回index页面同时，
    // 携带参数name过来。通过addObject()可以把你需要返回的参数值设置进去，返回给前端。

    @RequestMapping(value = "/home/page")
    @ResponseBody
    public ModelAndView goHome() {
        System.out.println("go to the home page!");
        ModelAndView mode = new ModelAndView();
        mode.addObject("name", "zhangsan");
        mode.setViewName("index");
        return mode;
    }

    @RequestMapping(value = "/home/mv")
    @ResponseBody
    public ModelAndView goHomeByModeAndview() {
        System.out.println("go to the home page!");
        ModelAndView mode = new ModelAndView("index");
        mode.addObject("name", "zhangsan");
        return mode;
    }

    // 2. String 类型，这个也是使用非常普遍的方法
    // 首先看这个方法，啥也没干，直接返回了字符串"index"，返回的字符串表示一个视图名称，直接返回到index.jsp的页面
    @RequestMapping(value = "/home")
    public String home() {
        System.out.println("redirect to home page!");
        return "index";
    }

    // 3. Model其实是一个接口,它的实现类是extendedModeMap类继承了ModelMap，具体可以参考modeMap
    @RequestMapping(value = "/home/res")
    // 但是如果添加了如下的参数,此时返回的就不是视图名称了而是作为字符串，index，直接显示在页面上面F
    @ResponseBody
    public String homeRes() {
        System.out.println("redirect to home page!");
        return "index";
    }

    // 4.Map对象，其实采用的就是key-value的形式，例如下面方法
    // 主要作用用于传递控制方法的处理数据到前端 ，有点类似request的域的作用，比如setAttribute的方法来设置。
    @RequestMapping(value = "/home/map")
    public Map<String, String> testMap() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "lisi");
        return map;
    }

    // 5. void的类型，即没有返回值，响应的页面就是请求的页面 响应的地址/home/void就是这个
    // 这里响应的view等同于请求的view，等同于返回null,等同于void类型。

    @RequestMapping(value = "/home/void")
    public void testVoid() {
        ModelAndView mode = new ModelAndView();
        mode.addObject("name", "wagnwu");
    }

    @RequestMapping(value = "/{fileName}.tar.gz", method = RequestMethod.GET)
    public void testFileDownload(String fileName, HttpServletResponse resp) throws IOException {
        System.out.println("fileName:" + fileName);
        File file = new File("C:/test.txt");

        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }    }

    // download file
    // 4.文件下载将文件写到输出流里：
    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse resp) throws IOException {
        System.out.println("fileName: testDownload?" );

        File file = new File("C:/test.txt");

        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /// upload file
    // 2.文件上传(后端java代码)支持多文件
    // Way1.使用MultipartHttpServletRequest来处理上传请求，然后将接收到的文件以流的形式写入到服务器文件中
    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("upload.jpg"));
        FileInputStream fs = (FileInputStream) multiReq.getFile("file").getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
    }

    // Way2.也可以这样来取得上传的file流：
    @RequestMapping("/fileUpload")
    public Map fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        Map result = new HashMap();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        String dateDir = df.format(new Date());// new Date()为获取当前系统时间
        String serviceName = file.getOriginalFilename();
        File tempFile = new File(dateDir + File.separator + serviceName);
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
                // "d:/"+file.getOriginalFilename() 指定目录
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result.put("msg", "上传失败," + e.getMessage());
                result.put("state", false);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result.put("msg", "上传失败," + e.getMessage());
                result.put("state", false);
                return result;
            }
            result.put("msg", "上传成功");
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            // file.save

            result.put("state", true);
            return result;
        } else {
            result.put("msg", "上传失败，因为文件是空的");
            result.put("state", false);
            return result;
        }
    }
}
