package com.leyidai.admin.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * spring 异步上传
 * @author song
 *
 */
@Controller
public class FileUploadController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	@Value("#{systemConfigProperties[uploadfilerootfolder]}")
	private String uploadfilerootfolder;
	@Value("#{systemConfigProperties[uploadfilevitralpath]}")
	private String uploadfilevitralpath;
	
	public FileUploadController(){
		
	}
	 /** 
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/> 
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述) 
     */  
    @RequestMapping(value="/fileUpload")  
    public void addUser(MultipartHttpServletRequest request,HttpServletResponse response) throws IOException{  
    	
    	
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		String generationfileName = simpleFormat.format(new Date())+new Random().nextInt(1000);
		//保存路径
		String paramFile = request.getParameter("paramFile");
		String fileNameSuffix=null;
		String fileName= null;
		if(null!=paramFile&&!"".equals(paramFile)){
			try {
				MultipartFile mf = request.getFile(paramFile);
				fileName=mf.getOriginalFilename();
				if(null!=mf&&!"".equals(mf)){
					fileNameSuffix=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
					SaveFileFromInputStream(mf.getInputStream(),uploadfilerootfolder,generationfileName+"."+fileNameSuffix);
					log.debug("upload file {} success!", uploadfilerootfolder+generationfileName+fileNameSuffix);
					out.write("{'state':'0','fileName':'"+uploadfilevitralpath+""+generationfileName+"."+fileNameSuffix+"','paramFile':'"+paramFile+"'}");
				}
			} catch (Exception e) {
				out.write("{'state':'1'}");
				e.printStackTrace();
				log.debug("upload file error, {}", e.fillInStackTrace());
			}
		}	
	}
    //保存文件   1,文件   2，保存路径 3，文件名称
	@SuppressWarnings("unused")
	public void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException{      
      	  FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
      	  byte[] buffer =new byte[1024*1024];
      	  int bytesum = 0;
      	  int byteread = 0; 
      	  while ((byteread=stream.read(buffer))!=-1){
      	     bytesum+=byteread;
        	   fs.write(buffer,0,byteread);
     	      fs.flush();
       	 } 
       	 fs.close();
        	stream.close();      
	}
}
