package com.leyidai.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;


@SuppressWarnings({ "serial", "deprecation" })
public class UploadServlet extends HttpServlet {

	private String uploadPath="e:/var/upload";
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        DiskFileUpload fu =new DiskFileUpload();
        fu.setSizeMax(10*1024*1024);   // 设置允许用户上传文件大小,单位:字节
        fu.setSizeThreshold(4096);   // 设置最多只允许在内存中存储的数据,单位:字节
        fu.setRepositoryPath(uploadPath); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
       
        String userid=request.getParameter("userid");
        //开始读取上传信息
        //int index=0;
        List fileItems =null;
        try {
                 fileItems = fu.parseRequest(request);
                  System.out.println("fileItems="+fileItems);
         } catch (Exception e) {
                 e.printStackTrace();
         }
                     
          
        Iterator iter = fileItems.iterator(); // 依次处理每个上传的文件
        while (iter.hasNext())
        {
            FileItem item = (FileItem)iter.next();// 忽略其他不是文件域的所有表单信息
            if (!item.isFormField())
            {
                String name = (item.getName()).replaceAll("\\\\", "/"); //获取上传文件名,包括路径
            
                name=name.substring(name.lastIndexOf("\\")+1);//从全路径中提取文件名
                long size = item.getSize();
                if((name==null||name.equals("")) && size==0)
                      continue;
                int point = name.lastIndexOf(".");
                File fNew=new File(uploadPath, userid+name.substring(point));
                try {
                         item.write(fNew);
                         item.delete();
                 } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                 }
            }
            else//取出不是文件域的所有表单信息
            {
                //String fieldvalue = item.getString();
                //如果包含中文应写为：(转为UTF-8编码)
                //String fieldvalue = new String(item.getString().getBytes(),"UTF-8");
            }
        }
      
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
       out.write("ok");
       out.close();
	}

}
