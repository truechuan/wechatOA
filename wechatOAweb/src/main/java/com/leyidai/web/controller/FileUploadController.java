package com.leyidai.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Value("#{systemConfigProperties[uploadHeadimgPath]}")
	private String uploadHeaderPicturePath;
	@Value("#{systemConfigProperties[uploadImageFolder]}")
	private String uploadImageFolder;
	
	public FileUploadController(){
		
	}
	 /** 
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/> 
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述) 
     */  
    @RequestMapping(value="/static/uploadHeaderPicture/{userid}")  
    public void uploadFiles(MultipartHttpServletRequest request,HttpServletResponse response,@PathVariable("userid")String userid) throws IOException{  
     	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//保存路径
		String paramFile = request.getParameter("paramFile");
		String fileName= String.valueOf(System.currentTimeMillis());
		if(null!=paramFile&&!"".equals(paramFile)){
			try {
				MultipartFile mf = request.getFile(paramFile);
				String taff = mf.getContentType().split("/")[1];
				fileName = fileName + "." + taff;
				if(null!=mf&&!"".equals(mf)){
					SaveFileFromInputStream(mf.getInputStream(),uploadHeaderPicturePath,fileName);
					out.write("ok-" + uploadImageFolder+"/"+fileName);
				}
			} catch (Exception e) {
				out.write("error");
				e.printStackTrace();
				log.debug("upload file error, {}", e.fillInStackTrace());
			}
		}	
	}
    
    //保存文件   1,文件   2，保存路径 3，文件名称
	@SuppressWarnings("unused")
	public String SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException{  
		  File file = new File(path);
		  if(!file.exists()){
			  file.mkdirs();
		  }
		  file = null;
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
        return  path + "/"+ filename;
	}
	
	/**
	 * 显示头像文件
	 * 
	 */
	@RequestMapping(value="/showHeadimg/{imgFilename}")  
	public void showImg(HttpServletRequest request,HttpServletResponse response,@PathVariable("imgFilename")String imgFilename) throws IOException{  
		String filename=uploadHeaderPicturePath+imgFilename;
		File file=new File(filename);
		if(!file.exists()){
			file=new File(uploadHeaderPicturePath+"default.png");
		}
		try{
			FileInputStream fis=new FileInputStream(file);
			StreamUtils.copy(fis, response.getOutputStream());
			fis.close();
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 显示图片
	 * @param request
	 * @param response
	 * @param imgFilename
	 * @throws IOException
	 * @author Sue
	 */
	@RequestMapping(value="/showImage/{imgFilename}/")  
	public void showImage(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("imgFilename")String imgFilename) throws IOException{  
		String filename=uploadImageFolder+imgFilename;
		log.debug("file name is "+imgFilename);
		File file=new File(filename);
		try{
			FileInputStream fis=new FileInputStream(file);
			StreamUtils.copy(fis, response.getOutputStream());
			fis.close();
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
   }

}
