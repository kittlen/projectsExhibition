package com.schoolProjects.utils;

import com.schoolProjects.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/7/23 15:00
 */
public class UploadUtil {
    private static String hostUriName=IpConfig.hostUriName;//服务器运行时的项目名,使用到新项目时需要修改
    /**
     * 获取当前服务器的ip地址 需要项目运行起来才能获取
     *
     * @return
     */
    public static String getHostAddress(HttpServletRequest request) {
        String hostAddress="";
        try {
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            hostAddress = address.getHostAddress();//192.168.0.121
            return hostAddress;
        } catch (Exception e) {
            hostAddress=request.getLocalAddr();//如果访问的地址为localhost 则获取到的地址为0:0:0:0:0:0:0:1
            return hostAddress;
        }
    }

    /**
     * 获取当前服务器的端口号
     * @param request
     * @return
     */
    public static int getLocalPort(HttpServletRequest request){
        int localPort = request.getLocalPort();
        return localPort;
    }

    /**
     * 上传多张图片
     *
     * @param files
     * @param request
     * @param path    图片摆放的位置
     * @return
     */
    public static Map<String, String> filesSave(MultipartFile[] files, HttpServletRequest request, String path) {
//        String hostAddress = getHostAddress(request);//获取服务器的ip地址
        String hostAddress = IpConfig.ipConfig;
        int localPort = getLocalPort(request);//获取端口号
        if("".equals(hostAddress)){
            return null;
        }
        String uriPath="http://"+hostAddress+":"+localPort+"/"+hostUriName+"/"+path;//文件所在uri地址
        //文件存放的位置
        String uploadPath = request.getServletContext().getRealPath("/" + path);
        Map<String, String> map = new HashMap<String, String>();
        File dir = new File(uploadPath);
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();
        }
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                int index=fileName.lastIndexOf("/");
                if(index!=-1){
                    fileName=fileName.substring(index+1);
                }
                File tempFile = new File(uploadPath, fileName);
                file.transferTo(tempFile);
                String thisUriPaht=uriPath+ "/" + file.getOriginalFilename();
                map.put(file.getOriginalFilename(), thisUriPaht);
            }
        } catch (Exception e) {
            return null;
        }
        return map;
    }

    /**
     * @param file
     * @param request
     * @param path    图片摆放的位置
     * @return 富文本编译器可以直接返回次对象即可
     */
    public static Result fileSave(MultipartFile file, HttpServletRequest request, String path) {
        Result result = new Result();
//        String hostAddress = getHostAddress(request);//获取服务器的ip地址
        String hostAddress = IpConfig.ipConfig;
        int localPort = getLocalPort(request);//获取端口号
        if("".equals(hostAddress)){
            result.setCode(Result.RESULT_ERROR);//其他代表失败
            result.setMsg("图片上传失败");
            return result;
        }
        String uriPath="http://"+hostAddress+":"+localPort+"/"+hostUriName+"/"+path;//文件所在uri地址
        //文件存放的位置
        String uploadPath = request.getServletContext().getRealPath("/" + path);
        File dir = new File(uploadPath);
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();
        }
        try {
            String fileName = file.getOriginalFilename();
            int index=fileName.lastIndexOf("\\");
            if(index!=-1){
                fileName=fileName.substring(index+1);
            }
            File tempFile = new File(uploadPath, fileName);
            file.transferTo(tempFile);
            Map<String, String> map = new HashMap<String, String>();
            uriPath=uriPath+ "/" + fileName;
            map.put("src", uriPath);
            map.put("fileName", fileName);
            result.setData(map);
            result.setCode(Result.RESULT_ZERO);//0代表成功
            return result;
        } catch (Exception e) {
            result.setCode(Result.RESULT_ERROR);//其他代表失败
            result.setMsg("图片上传失败");
            return result;
        }

    }

    /**
     * @param file
     * @param request
     * @param path    图片摆放的位置
     * @param imgName 图片名称,不需要带后缀
     * @return 富文本编译器可以直接返回次对象即可
     */
    public static Result fileSave(MultipartFile file, HttpServletRequest request, String path, String imgName) {
        Result result = new Result();
//        String hostAddress = getHostAddress(request);//获取服务器的ip地址
        String hostAddress = IpConfig.ipConfig;
        int localPort = getLocalPort(request);//获取端口号
        if("".equals(hostAddress)){
            result.setCode(Result.RESULT_ERROR);//其他代表失败
            result.setMsg("图片上传失败");
            return result;
        }
        String uriPath="http://"+hostAddress+":"+localPort+"/"+hostUriName+"/"+path;//文件所在uri地址
        //文件存放的位置
        String uploadPath = request.getServletContext().getRealPath("/" + path);
        File dir = new File(uploadPath);
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();
        }
        try {
            String fileName = file.getOriginalFilename();
            int index=fileName.lastIndexOf("\\");
            if(index!=-1){
                fileName=fileName.substring(index+1);
            }
            imgName=imgName+"."+fileName.substring(fileName.lastIndexOf(".")+1);
            File tempFile = new File(uploadPath, imgName);
            file.transferTo(tempFile);
            Map<String, String> map = new HashMap<String, String>();
            uriPath=uriPath+ "/" + imgName;
            map.put("src", uriPath);
            map.put("imgSourceName", fileName);
            map.put("imgName", imgName);
            result.setData(map);
            result.setCode(Result.RESULT_ZERO);//0代表成功
            return result;
        } catch (Exception e) {
            result.setCode(Result.RESULT_ERROR);//其他代表失败
            result.setMsg("图片上传失败");
            return result;
        }

    }

}
