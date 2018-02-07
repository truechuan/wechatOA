package com.leyidai.admin.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public class CreateSimpleExcelToDisk {
	
	private static String excelTemplatePath;
	
	public static String getExcelTemplatePath() {
		return excelTemplatePath;
	}

	public static void setExcelTemplatePath(String excelTemplatePath) {
		CreateSimpleExcelToDisk.excelTemplatePath = excelTemplatePath;
	}


	private static final Logger log = LoggerFactory
			.getLogger(CreateSimpleExcelToDisk.class);
	private static List<excelOrder> listRecords;
	// 表名
	private static String sheetTitle;
	// 表头
	private static String[] headers;

	public static String[] getHeaders() {
		return headers;
	}

	public static void setHeaders(String[] headers) {
		CreateSimpleExcelToDisk.headers = headers;
	}

	public static String getSheetTitle() {
		return sheetTitle;
	}

	public static void setSheetTitle(String sheetTitle) {
		CreateSimpleExcelToDisk.sheetTitle = sheetTitle;
	}

	public static List<excelOrder> getListRecords() {
		return listRecords;
	}

	public static void setListRecords(List<excelOrder> listRecords) {
		CreateSimpleExcelToDisk.listRecords = listRecords;
	}

	public static void getWorkBook(String fPath) throws Exception {
		// excel模板路径
		log.info(excelTemplatePath);
		File fi = new File(excelTemplatePath);
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
		// 读取excel模板
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// 第一步，创建一个webbook，对应一个Excel文件
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFRow row;
		// 第五步，写入实体数据 这些数据从数据库得到，
		List<excelOrder> list = CreateSimpleExcelToDisk.getListRecords();
		// // 遍历集合数据，产生数据行
		// 遍历集合数据，产生数据行
		Iterator<excelOrder> it = list.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object t = it.next(); // 这里不要使用泛型强制转换
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// 这个分支用于生成序号，不适用模型传过来的值
				if (i == 0) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					cell.setCellValue(index);
				} else {
					Field field = fields[i];
					String fieldName = field.getName();
					String getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					try {
						Class tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName,
								new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
						// 判断值的类型后进行强制类型转换
						String textValue = "";

						if (value == null || "".equals(value)
								|| "0".equals(value)) {
							value = "";
						} else {
							HSSFCell cell = row.createCell(i);
							cell.setCellStyle(style);
							if (value instanceof Integer) {
								int intValue = (Integer) value;
								cell.setCellValue(intValue);
							} else if (value instanceof Float) {
								float fValue = (Float) value;
								cell.setCellValue(fValue + "");
							} else if (value instanceof Double) {
								double dValue = (Double) value;
								cell.setCellValue(dValue + "");
							} else if (value instanceof Long) {
								long longValue = (Long) value;
								cell.setCellValue(longValue);
							}
							if (value instanceof Boolean) {
								boolean bValue = (Boolean) value;
								textValue = "男";
								if (!bValue) {
									textValue = "女";
								}
							} else if (value instanceof Date) {
								Date date = (Date) value;
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								textValue = sdf.format(date);
							}
							else if(fieldName=="status")
							{
								Integer val=(Integer)value;
								log.info(val+"val");
								log.info(value+"value");
								switch (val) {
								case 1:
									textValue = "未通过";
									break;
								case 2:
									textValue = "审核通过";
									break;
								case 3:
									textValue = "审核前取消";
									break;
								case 4:
									textValue = "办结";
									break;
								case 5:
									textValue = "爽约";
									break;
								case 6:
									textValue = "补正";
									break;
								case 7:
									textValue = "未通过";
									break;
								case 8:
									textValue = "审核后取消";
									break;
								case 9:
									textValue = "失败";
									break;
								default:
									break;
								}
								
							}
							else {
								// 其它数据类型都当作字符串简单处理
								textValue = value.toString();
							}
							// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
							if (textValue != null) {
								Pattern p = Pattern
										.compile("^//d+(//.//d+)?{1}");
								Matcher matcher = p.matcher(textValue);
								if (matcher.matches()) {
									// 是数字当作double处理
									cell.setCellValue(Double
											.parseDouble(textValue));
								} else {
									HSSFRichTextString richString = new HSSFRichTextString(
											textValue);
									HSSFFont font3 = wb.createFont();
									font3.setColor(HSSFColor.BLUE.index);
									richString.applyFont(font3);
									cell.setCellValue(richString);
								}
							}
						}

					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} finally {
						// 清理资源
					}
				}
			}
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(fPath);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static boolean downLoadFile(String filePath,
	        HttpServletResponse response, String fileName, String fileType)
	        throws Exception {
	        File file = new File(filePath);  //根据文件路径获得File文件
	        //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
	        if("pdf".equals(fileType)){
	           response.setContentType("application/pdf;charset=GBK");
	        }else if("xls".equals(fileType)){
	           response.setContentType("application/msexcel;charset=GBK");
	        }else if("doc".equals(fileType)){
	           response.setContentType("application/msword;charset=GBK");
	        }
	        //文件名
	        response.setHeader("Content-Disposition", "attachment;filename=\""
	            + new String(fileName.getBytes(), "ISO8859-1") + "\"");
	        response.setContentLength((int) file.length());
	        byte[] buffer = new byte[4096];// 缓冲区
	        BufferedOutputStream output = null;
	        BufferedInputStream input = null;
	        try {
	          output = new BufferedOutputStream(response.getOutputStream());
	          input = new BufferedInputStream(new FileInputStream(file));
	          int n = -1;
	          //遍历，开始下载
	          while ((n = input.read(buffer, 0, 4096)) > -1) {
	             output.write(buffer, 0, n);
	          }
	          output.flush();   //不可少
	          response.flushBuffer();//不可少
	        } catch (Exception e) {
	          //异常自己捕捉       
	        } finally {
	           //关闭流，不可少
	           if (input != null)
	                input.close();
	           if (output != null)
	                output.close();
	        }
	       return false;
	    }
	
}