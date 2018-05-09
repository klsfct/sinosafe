package com.sinotn.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.util.mime.MimeUtility;
import org.apache.commons.lang.StringUtils;

public class FileUtils {
	

	/**********************************************************
	 * 获得指定文件路径下的文件列表
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 指定文件路径下的文件数组
	 ***********************************************************/
	public static File[] getAllFileByPath(String filePath) {
		if (StringUtils.isEmpty(filePath))
			return null;// 参数非空判断

		File[] list = null;
		try {
			File d = new File(filePath);// 建立对象
			list = d.listFiles();// 取得代表目录中所有文件的File对象数组
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 实现读取文件夹下（包括子目录）所有文件的文件
	 * 
	 * @return
	 */
    public static List<File> scanFiles(String path) {
    	// 存放所有文件的集合
    	List<File> fileList = new ArrayList<File>();
    	// 存放所有文件的集合(包含目录)
        List<File> filePaths = new ArrayList<File>();
        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(path);
        File[] file = dir.listFiles();

        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                // 把第一层的目录，全部放入链表
                list.add(file[i]);
            }
            filePaths.add(file[i]);
        }
        // 循环遍历链表
        while (!list.isEmpty()) {
            // 把链表的第一个记录删除
            File tmp = list.removeFirst();
            // 如果删除的目录是一个路径的话
            if (tmp.isDirectory()) {
                // 列出这个目录下的文件到数组中
                file = tmp.listFiles();
                if (file == null) {// 空目录
                    continue;
                }
                // 遍历文件数组
                for (int i = 0; i < file.length; ++i) {
                    if (file[i].isDirectory()) {
                        // 如果遍历到的是目录，则将继续被加入链表
                        list.add(file[i]);
                    }
                    filePaths.add(file[i]);
                }
            }
        }
        // 去除文件夹，只保留文件
        if (null != filePaths && filePaths.size()>0) {
			for (File tempFile : filePaths) {
				if (tempFile.isFile()) {
					fileList.add(tempFile);
				}
			}
		}
        return fileList;
    }

	/**********************************************************
	 * 删除指定文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 删除成功返回true，失败返回false，如果filePath是文件夹返回false
	 ***********************************************************/
	public static boolean delFile(String filePath) {
		if (StringUtils.isEmpty(filePath))
			return false;// 参数非空判断
		boolean retn = false;
		try {
			File myDelFile = new File(filePath);
			if (myDelFile.isDirectory())
				return false;

			if (myDelFile.exists()) {
				retn = myDelFile.delete();
			} else {
				retn = false;
			}
		} catch (Exception e) {
			retn = false;
			e.printStackTrace();
		}
		return retn;
	}

	/************************************************************
	 * 根据指定的文件名重新命名文件
	 * 
	 * @param pathFile
	 *            要从新命名的文件
	 * @param destname
	 *            文件名称
	 * @return 如果从新命名成功返回true，如果失败返回false
	 ***********************************************************/
	public static boolean renamefile(File pathFile, String destname) {
		if (StringUtils.isEmpty(destname) || null == pathFile)
			return false;// 参数非空判断
		boolean result = false;
		String fileParent = pathFile.getParent();
		File rf = new File(fileParent + "//" + destname);

		if (pathFile.renameTo(rf)) {
			result = true;
		}
		return result;
	}

	/*************************************************************
	 * 根据路径创建文件目录
	 * 
	 * @param path
	 *            文件路径
	 * @return 创建成功返回true，创建失败返回false
	 ************************************************************/
	public static boolean mkDir(String path) {

		if (StringUtils.isEmpty(path))
			return false;

		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return true;
		} else {
			return file.mkdirs();
		}
	}

	/***************************************************************
	 * 删除文件夹
	 **************************************************************/
	public static boolean delFolder(String folderPath) {
		if (StringUtils.isEmpty(folderPath))
			return false;
		boolean retn = false;

		try {
			if (delAllFile(folderPath)) {
				java.io.File myFilePath = new java.io.File(folderPath);
				retn = myFilePath.delete(); // 删除空文件夹
			}
		} catch (Exception e) {
			retn = false;
			e.printStackTrace();
		}
		return retn;
	}

	/*****************************************************************
	 * 删除指定文件夹下所有文件 param path 文件夹完整绝对路径
	 * 
	 * @param path
	 *            文件路径
	 * @return 成功返回true，失败返回false，文件路径不存在返回false
	 ****************************************************************/
	public static boolean delAllFile(String path) {
		if (StringUtils.isEmpty(path))
			return false;
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/*******************************************************************
	 * 根据文件目录名称和文件内容，创建新文件
	 * 
	 * @param filePathAndName
	 *            文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            文件内容
	 * @return boolean true创建文件成功 false创建文件失败
	 ******************************************************************/
	public static boolean newFile(String filePathAndName, String fileContent) {
		if (StringUtils.isEmpty(filePathAndName))
			return false;
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/***************************************************************************
	 * 获取文本文件内容
	 * 
	 * @param pathandname
	 *            路径和文件名
	 * @return
	 ******************************************************************/
	public static String findFileText(String pathandname) {
		if (StringUtils.isEmpty(pathandname))
			return "";
		String result = "";
		String[] filetxt;
		String strtxt = null;

		try {
			File dir = new File(pathandname);
			RandomAccessFile rFile = new RandomAccessFile(dir, "r");

			while (rFile.getFilePointer() < rFile.length()) {
				// 字符串的拆分
				strtxt = rFile.readLine();
				Pattern p = Pattern.compile(" ");
				filetxt = p.split(strtxt);
				for (int i = 0; i < filetxt.length; i++) {
					// 为解决中文乱码
					// String str1 = new
					// String(filetxt[i].getBytes("ISO8859_1"),"GBK");
					result = result + filetxt[i];
				}
			}
			rFile.close();
		} catch (Exception e) {
			result = "文件没有生成";
		}

		return result;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	/*******************************************************************
	 * 创建指定的文件
	 * 
	 * @param fileName
	 *            文件路径名
	 * @return 新创建的文件
	 ******************************************************************/
	public static File createFile(String fileName) {
		File f = null;
		if (fileName != null && !fileName.equals("")) {
			f = new File(fileName);
			try {
				f.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return f;
	}

	/*******************************************************************
	 * 判断指定的文件是否存在
	 * 
	 * @param fileName
	 *            文件路径名
	 * @return bFlag
	 ******************************************************************/
	public static boolean isExist(String fileName) {
		boolean bFlag = false;
		if (fileName != null && !fileName.equals("")) {
			File f = new File(fileName);
			if (f.exists()) {
				return true;
			}
		}
		return bFlag;
	}

	/*******************************************************************
	 * 删除指定的文件
	 ******************************************************************/
	public static boolean deleteFile(String fileName) {
		boolean bFlag = false;
		File f = null;
		if (fileName != null && !fileName.equals("")) {
			f = new File(fileName);
			return f.delete();
		}
		return bFlag;
	}

	/*******************************************************************
	 * 返回指定文件的大小
	 * 
	 * @param fileName
	 *            路径和文件名
	 * @return 文件大小
	 ******************************************************************/
	public static String getFileSize(String fileName) {
		FileInputStream fis = null;
		String retString = "";
		try {
			fis = new FileInputStream(fileName);
			retString = String.valueOf((fis.available() / 1000) + "k");
		} catch (FileNotFoundException e2) {
			System.out.println("找不到指定文件");
		} catch (IOException e1) {
			System.out.println("IO出错！");
		}
		return retString;
	}

	/*******************************************************************
	 * 拷贝文件
	 * 
	 * @param sourceFile
	 *            原文件
	 * @param targetFile
	 *            目标文件
	 ******************************************************************/
	public static void copyFileToFile(File sourceFile, File targetFile)
			throws IOException {
		if (!targetFile.exists()) {
			File parent = targetFile.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
		}
		FileInputStream in = new FileInputStream(sourceFile);
		FileOutputStream out = new FileOutputStream(targetFile);

		byte[] buf = new byte[20 * 1024];
		int size = in.read(buf);

		while (size != -1) {
			out.write(buf, 0, size);
			size = in.read(buf);
		}
		out.flush();
		out.close();
		in.close();
	}

	/*******************************************************************
	 * 读取文件返回此文件的字节内容
	 * 
	 * @param fileName
	 *            要读取的文件
	 * @return String 此文件字节内容的字符串，如果文件不存在或者是目录返回null
	 */
	public static String readFileToByte(File fileName) throws IOException {
		if (!fileName.exists() || fileName.isDirectory()) {
			return null;
		}
		int len = 0;
		InputStream fis = null;
		StringBuffer fileString = new StringBuffer();
		fis = new FileInputStream(fileName);
		byte buf[] = new byte[1023];
		while ((len = fis.read(buf)) > 0) {
			for (int i = 0; i < len; i++) {
				fileString.append(buf[i]);
			}
		}
		fis.close();

		return fileString.toString();
	}

	/*******************************************************************
	 * 拷贝文件
	 * 
	 * @param sourceFile
	 *            原文件
	 * @param targetDir
	 *            目标路径
	 ******************************************************************/
	public static synchronized void copyFileToDirectory(File sourceFile,
			File targetDir) throws IOException {
		if (sourceFile.exists()) {
			File newFile = new File(targetDir, sourceFile.getName());
			copyFileToFile(sourceFile, newFile);
		}
	}

	/**
	 * 获得文件字节数组
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filename) throws IOException {
		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException(filename);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void createFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos, 20 * 1024);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void appendFile(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		FileUtils.copyFileToFile(new File("C:\\upload\\1221.JPG"), new File(
				"C:\\upload\\5100\\1221.JPG"));
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String fileName,
			HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(fileName, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(fileName.getBytes("GB2312"),
							"ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.decodeText(fileName);

			return fileName;
		} catch (Exception ex) {
			return fileName;
		}
	}

}
