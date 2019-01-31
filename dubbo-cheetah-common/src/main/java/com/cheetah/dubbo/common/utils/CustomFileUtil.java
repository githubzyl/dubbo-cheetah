package com.cheetah.dubbo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具类
 * 
 * @author: zhaoyl
 * @since: 2017年6月6日 下午3:28:18
 * @history:
 */
@Slf4j
public class CustomFileUtil {

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件
	 * @param uploadPath
	 *            上传路径
	 * @return
	 * @create: 2017年8月25日 上午10:21:52 zhaoyl
	 * @history:
	 */
	public static Map<String, String> uploadFile(MultipartFile file, String uploadPath) {
		String status = "1";
		String message = "";
		String data = "";
		// 判断是否为空文件，如果是空文件，则直接返回
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String extendName = FilenameUtils.getExtension(fileName);
			try {
				String fileId = getInputStreamMD5(file.getInputStream());
				String filePath = uploadPath + "/" + fileId + "." + extendName;

				File f = new File(filePath);
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				
				FileOutputStream fos = new FileOutputStream(filePath);
				fos.write(file.getBytes());
				fos.close();
				// 返回上传文件的路径
				message = filePath;
				data = fileId;
			} catch (Exception e) {
				status = "2";
				message = "文件上传失败";
				log.error(message + ":", e);
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param uploadPath
	 * @param keepFileName
	 * @return
	 */
	public static Map<String, String> uploadFile(MultipartFile file, String uploadPath, boolean keepFileName) {
		String status = "1";
		String message = "";
		String data = "";
		// 判断是否为空文件，如果是空文件，则直接返回
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String extendName = FilenameUtils.getExtension(fileName);
			try {
				String fileId = keepFileName ? fileName : getInputStreamMD5(file.getInputStream());
				String filePath = keepFileName ? uploadPath + fileId : uploadPath + fileId + "." + extendName;

				File f = new File(filePath);
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				
				FileOutputStream fos = new FileOutputStream(filePath);
				fos.write(file.getBytes());
				fos.close();
				// 返回上传文件的路径
				message = filePath;
				data = fileId;
			} catch (Exception e) {
				status = "2";
				message = "文件上传失败";
				log.error(message + ":", e);
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		return map;
	}

	// 创建临时文件
	public static File createTempFile(String prefix, String suffix, String fileDir) throws IOException {
		File file = new File(fileDir + "/" + prefix + suffix);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public static String getFileDir(Environment env, String path) {
		String dirPath = env.getProperty(path);
		return getFileDir(dirPath);
	}

	public static String getFileDir(String dirPath) {
		File fileDir = null;
		if (StringUtils.isNotBlank(dirPath)) {
			fileDir = new File(dirPath);
			// 如果文件夹不存在则创建
			if (!fileDir.exists() && !fileDir.isDirectory()) {
				fileDir.mkdirs();
			}
		}
		return dirPath;
	}

	public static void downloadFile(String fileName, String filePath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		download(fileName, new FileInputStream(filePath), request, response);
	}

	public static void downloadFile(String fileName, FileInputStream fileInputStream, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		download(fileName, fileInputStream, request, response);
	}

	public static void downloadFile(String fileName, byte[] bytes, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initDownloadParams(fileName, request, response);
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		try {
			bos.write(bytes);
		} finally {
			closeStream(bos);
		}
	}

	public static void showPdfToPage(String pdfFilePath, HttpServletResponse response) {
		try {
			showPdfToPage(new FileInputStream(pdfFilePath), response);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 向页面展示pdf
	public static void showPdfToPage(FileInputStream fileInputStream, HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");
			DataOutputStream temps = new DataOutputStream(response.getOutputStream());
			DataInputStream in = new DataInputStream(fileInputStream);
			byte[] b = new byte[2048];
			while ((in.read(b)) != -1) {
				temps.write(b);
				temps.flush();
			}
			in.close();
			temps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showHtmlToPage(File file, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String html = readFileToStr(file, "utf-8");
			out.write(html);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void download(InputStream inputStream, HttpServletResponse response) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(inputStream);
			byte[] b = new byte[1024];
			int bytes;
			while (-1 != (bytes = bis.read(b, 0, b.length))) {
				bos.write(b, 0, bytes);
			}
		} finally {
			closeStream(bis);
			closeStream(bos);
		}
	}

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	public static String readFileToStr(File file, String encode) {
		String str = null;
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String readFileToStr(InputStream in, String encode) {
		String str = null;
		try {
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String readFileToStr(String path) throws Exception {
		File file = new File(path);// 定义一个file对象，用来初始化FileReader
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		BufferedReader bReader = new BufferedReader(reader);// new一个BufferedReader对象，将文件内容读取到缓存
		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
		String s = "";
		while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
			sb.append(s);
		}
		bReader.close();
		String str = sb.toString();
		return str;
	}

	public static void writeStrToFile(String sourceString, String filePath) {
		byte[] sourceByte = sourceString.getBytes();
		if (null != sourceByte) {
			FileOutputStream outStream = null;
			try {
				File file = new File(filePath); // 文件路径（路径+文件名）
				if (!file.exists()) { // 文件不存在则创建文件，先创建目录
					File dir = new File(file.getParent());
					dir.mkdirs();
					file.createNewFile();
				}
				outStream = new FileOutputStream(file); // 文件输出流用于将数据写入文件
				outStream.write(sourceByte);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CustomFileUtil.closeStream(outStream);
			}
		}
	}

	private static void download(String fileName, FileInputStream fileInputStream, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initDownloadParams(fileName, request, response);
		download(fileInputStream, response);
	}

	public static void initDownloadParams(String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.reset();
		response.setContentType("application/x-msdownload;charset=utf-8");
		if (RequestUtil.isFirefoxBrowser(request)) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "utf-8");
			if (RequestUtil.isMSBrowser(request)) {
				if (fileName.length() > 150) {
					// 根据request的locale 得出可能的编码
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
			}
		}
		response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
	}

	public static void closeStream(Closeable closeable) {
		if (null == closeable) {
			return;
		}
		try {
			closeable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据文件流计算出文件的MD5
	 * 
	 * @param file
	 * @return
	 */
	private static String getInputStreamMD5(InputStream in) {
		MessageDigest digest = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());

		return bigInt.toString(16);
	}

	/**
	 * 下载远程文件并保存到本地
	 *
	 * @param remoteFilePath-远程文件路径
	 * @param localFilePath-本地文件路径（带文件名）
	 */
	public static String downloadRemoteFile(String remoteFilePath, String localFilePath) {
		String error = null;
		URL website = null;
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			website = new URL(remoteFilePath);
			rbc = Channels.newChannel(website.openStream());
			File parentFile = new File(localFilePath).getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			fos = new FileOutputStream(localFilePath);//本地要存储的文件地址 例如：test.txt
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			log.error("文件下载失败:",e);
			error = "文件下载失败";
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(rbc!=null){
				try {
					rbc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return error;
		}
	}

}
