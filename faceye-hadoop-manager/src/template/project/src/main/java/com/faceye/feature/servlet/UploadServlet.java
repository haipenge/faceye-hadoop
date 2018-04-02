package com.faceye.feature.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;

import com.faceye.feature.service.impl.BeanContextUtil;
import com.faceye.feature.upload.Path;
import com.faceye.feature.upload.Upload;
import com.faceye.feature.upload.UploadResult;

public class UploadServlet extends HttpServlet {

	/**
	 * word 文件预览图
	 * /work/server/document/pic/20140817
	 */
	private static final long serialVersionUID = -598317371461082707L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
			// File file = new File(request.getRealPath("/")+"imgs/"+request.getParameter("getfile"));
//			File file = new File(this.getUploadPath() + "/" + request.getParameter("getfile"));
			String getfile=request.getParameter("getfile");
			File file=new File(Path.getPathByFilename(getfile));
			if (file.exists()) {
				int bytes = 0;
				ServletOutputStream op = response.getOutputStream();
				response.setContentType(getMimeType(file));
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
				byte[] bbuf = new byte[1024];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, bytes);
				}
				in.close();
				op.flush();
				op.close();
			}
		} else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
//			File file = new File(request.getRealPath("/") + "imgs/" + request.getParameter("delfile"));
			String delfile=request.getParameter("delfile");
			File file=new File(Path.getPathByFilename(delfile));
			if (file.exists()) {
				file.delete(); // TODO:check and report success
			}
			PrintWriter writer = response.getWriter();
			response.setContentType("text/json");
			writer.write("{\"url\":\"success\"}");
			//从库中删除某一文件
//			DocFileService docFileService=BeanContextUtil.getBean(DocFileService.class);
//			DocFile docFile=docFileService.getDocFileByName(delfile);
//			docFileService.remove(docFile);
		} else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
			// File file = new File(request.getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
//			File file = new File(this.getUploadPath() + "/" + request.getParameter("getthumb"));
			String getthumb=request.getParameter("getthumb");
			File file=new File(Path.getPathByFilename(getthumb));
			if (file.exists()) {
				System.out.println(file.getAbsolutePath());
				String mimetype = getMimeType(file);
				if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
					BufferedImage im = ImageIO.read(file);
					if (im != null) {
						BufferedImage thumb = Scalr.resize(im, 75);
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						if (mimetype.endsWith("png")) {
							ImageIO.write(thumb, "PNG", os);
							response.setContentType("image/png");
						} else if (mimetype.endsWith("jpeg")) {
							ImageIO.write(thumb, "jpg", os);
							response.setContentType("image/jpeg");
						} else if (mimetype.endsWith("jpg")) {
							ImageIO.write(thumb, "jpg", os);
							response.setContentType("image/jpeg");
						} else {
							ImageIO.write(thumb, "GIF", os);
							response.setContentType("image/gif");
						}
						ServletOutputStream srvos = response.getOutputStream();
						response.setContentLength(os.size());
						response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
						os.writeTo(srvos);
						srvos.flush();
						srvos.close();
					}
				}
			} // TODO: check and report success
		} else {
			PrintWriter writer = response.getWriter();
			writer.write("call POST with multipart form data");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<UploadResult> result = Upload.upoad(request);
		PrintWriter writer = response.getWriter();
		response.setContentType("text/json");
//		List items = new ArrayList();
//		// JSONArray json = new JSONArray();
//		if (CollectionUtils.isNotEmpty(result)) {
//			for (UploadResult uploadResult : result) {
//				// JSONObject jsono = new JSONObject();
//				Map jsono = new HashMap();
//				jsono.put("name", uploadResult.getName());
//				jsono.put("key", uploadResult.getGenerateFileName().substring(0, uploadResult.getGenerateFileName().lastIndexOf(".") - 1));
//				jsono.put("size", uploadResult.getSize());
//				jsono.put("url", "/UploadServlet?getfile=" + uploadResult.getGenerateFileName());
//				jsono.put("thumbnailUrl", "/UploadServlet?getthumb=" + uploadResult.getGenerateFileName());
//				// 如果是word,ppt,pdf文档，则生成不同的预览图
//				if (StringUtils.isNotEmpty(uploadResult.getGenerateFileName())) {
//					if (uploadResult.getGenerateFileName().toLowerCase().endsWith("doc")
//							|| uploadResult.getGenerateFileName().toLowerCase().endsWith("ppt")
//							|| uploadResult.getGenerateFileName().toLowerCase().endsWith("docx")) {
//						jsono.put("thumbnailUrl", "/UploadServlet?getthumb=" + this.WORD_PREVIEW_FILE);
//					}
//				}
//				jsono.put("deleteUrl", "/UploadServlet?delfile=" + uploadResult.getGenerateFileName());
//				jsono.put("deleteType", "GET");
//				items.add(jsono);
//			}
//		}
//
//		String res = "{\"files\":";
//		res += Json.toJson(items);
//		res += "}";
		String res=Upload.uploadResults2Json(result);
		writer.write(res);
		writer.close();

	}

	private String getMimeType(File file) {
		String mimetype = "";
		if (file.exists()) {
			String suffix=this.getSuffix(file.getName());
			if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
				mimetype = "image/png";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpg")) {
				mimetype = "image/jpg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpeg")) {
				mimetype = "image/jpeg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("gif")) {
				mimetype = "image/gif";
			}else if(suffix.equalsIgnoreCase("doc")||suffix.equalsIgnoreCase("docx")){
				mimetype="application/x-msword";
				//application/msword
				//application/vnd.ms-word
			}else if(suffix.equalsIgnoreCase("xls")||suffix.equalsIgnoreCase("xlsx")){
				mimetype="application/vnd.ms-excel";
			}else if(suffix.equalsIgnoreCase("pdf")){
				mimetype="application/pdf";
			}else {
				javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
				mimetype = mtMap.getContentType(file);
			}
		}
		return mimetype;
	}

	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		return suffix;
	}

	/**
	 * 取得今天的文件存储目录
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年2月19日
	 */
//	private String getTodayDir() {
//		String dir = "";
//		dir = DateUtil.formatDate(new Date(), "yyyyMMdd");
//		return dir;
//	}

//	private String getUploadPath() {
//		PropertyService propertyService=BeanContextUtil.getBean(PropertyService.class);
//		String path=propertyService.get("upload.dir");
//		String result = "";
//		String dir = this.getTodayDir();
//		result = path + "/" + dir;
//		this.initPath(result);
//		return result;
//	}
	
	/**
	 * 根据文件名反推存储目录
	 * @todo
	 * @param filename
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年8月19日
	 */
//	private String getUploadPathByFileName(String filename){
//		String res="";
//		
//		return res;
//	}

//	private void initPath(String path) {
//		File file = new File(path);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//	}
}
