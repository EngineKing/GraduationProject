package com.thesis.utils;

import com.sun.org.apache.regexp.internal.RE;
import com.thesis.exception.RRException;
import org.apache.poi.ss.usermodel.Workbook;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author LeiPeng
 * @Date 2020/2/27 - 9:15
 */
public class FileUtil {
    public static boolean exist(File file) {
        return null != file && file.exists();
    }

    public static void responseToFile(File file, HttpServletResponse response, HttpServletRequest request) {  //将文件发送到前端
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(file.getName(), request));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
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

    public static void responseToExcel(String fileName, Workbook workbook, HttpServletResponse response,
                                       HttpServletRequest request) {  //将文件发送到前端
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + getFileName(fileName, request) + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String fileName, HttpServletRequest request) {
        // 获得请求头中的user-agent
        String agent = request.getHeader("User-Agent");
        try {
            if (agent.contains("MSIE")) {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else if (agent.contains("Firefox")) {
                BASE64Encoder base64Encoder = new BASE64Encoder();
                fileName = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8"));
            } else {
                fileName = URLEncoder.encode(fileName, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RRException("文件名获取错误");
        }
        return fileName;
    }
}
