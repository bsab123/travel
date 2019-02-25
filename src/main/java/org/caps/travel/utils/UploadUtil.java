package org.caps.travel.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具
 */
@Component
public class UploadUtil {



	/**
	 * spring不允许/不支持把值注入到静态变量中
	 * Spring支持set方法注入，可以利用非静态的setter方法注入静态常量。注意set方法不能有static
	 * @param UPLOADED_FOLDER
	 */
	public static String UPLOADED_FOLDER;
	@Value("${UPLOADED_FOLDER}")
	public void setUploadedFolder(String UPLOADED_FOLDER) {
		UploadUtil.UPLOADED_FOLDER = UPLOADED_FOLDER;
	}

	/**
	 * 处理文件上传
	 *
	 * @param file
	 *            存放文件的目录的绝对路径 servletContext.getRealPath("/upload")
	 * @return  123.png
	 */
	public static String upload(MultipartFile file) {
		String uuid = UUID.randomUUID().toString();

		String orgFileName = file.getOriginalFilename();
		String ext= "." + FilenameUtils.getExtension(orgFileName);
		String fileName = uuid + ext;
		try {
			File targetFile = new File(UPLOADED_FOLDER, fileName);
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
			//图片压缩
			String smallImg = uuid + "_small" + ext;
			File smallTargetFile = new File(UPLOADED_FOLDER, smallImg);
			Thumbnails.of(new File[] { targetFile }).scale(0.4D).toFile(smallTargetFile);
			return fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 删除文件
	 * @param imagePath
	 */
	public static void deleteFile(ServletContext servletContext, String imagePath) {
		String path = servletContext.getRealPath("/") + imagePath;
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		path = servletContext.getRealPath("/")
				+ imagePath.substring(0, imagePath.indexOf(".")) + "_small"
				+ imagePath.substring(imagePath.indexOf("."));
		file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}










































