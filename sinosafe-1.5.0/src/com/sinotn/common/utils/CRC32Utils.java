package com.sinotn.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * CRC生成
 * 
 * @author Libin
 * 
 */
public class CRC32Utils {

	/**
	 * 根据文件路径读取CRC32的值
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static long loadCRC32(String filePath) {
		long checksum = 0;
		try {
			CheckedInputStream cis = null;
			long fileSize = 0;
			File file = null;
			try {
				file = new File(filePath);
				cis = new CheckedInputStream(new FileInputStream(file),
						new CRC32());
				fileSize = file.length();
			} catch (FileNotFoundException e) {
				System.err.println("File not found.");
				System.exit(1);
			}
			byte[] buf = new byte[128];
			while (cis.read(buf) >= 0) {
			}
			checksum = cis.getChecksum().getValue() & 0x0ffffffffL;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return checksum;
	}
	/**
	 * 生成人脸识别files.dt文件
	 * @param dirPath
	 */
	public static void readFile(String dirPath) {
		// 获取目录下的文件列表
		List<File> list = FileUtils.scanFiles(dirPath);
		String fileNameStr = dirPath.substring(dirPath.length() - 8,
				dirPath.length());
		File fileNamePath = new File(
				dirPath + fileNameStr
						+ "\\conf");

		if (!fileNamePath.exists()) {
			fileNamePath.mkdirs();
		}
		FileWriter fileWriter = null;
		BufferedWriter bw = null;
		try {
			File readFile = FileUtils.createFile(fileNamePath.getAbsolutePath()
					+ "\\files.dt");
			fileWriter = new FileWriter(readFile);
			bw = new BufferedWriter(fileWriter);
			if (null != list && list.size() > 0) {
				for (File file : list) {
					if (!file.getName().equals("conf.ini")) {
						String filePath = file.getPath();
						filePath = filePath.replace(dirPath, "");
						bw.write(filePath);
						bw.newLine();
						bw.flush();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static long loadCRC32ByDir(String dirPath) {
		long checksum = 3528615269L;
		//long checksum = 0;
		// 获取目录下的文件列表
		List<File> list = FileUtils.scanFiles(dirPath);
		try {

			if (null != list && list.size() > 0) {
				for (File file : list) {
					System.out.println(file.getName());
					if (!file.getName().equals("conf.ini")) {
						
						checksum += loadCRC32(file.getAbsolutePath());
					}
					checksum = checksum & 0x0ffffffffL;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checksum;
	}

	public static void main(String[] args) {
		String dirPath = "C:\\Users\\SinotnLibin\\Desktop\\face_1.0.1_180428-14501001\\";
		System.out.println(loadCRC32ByDir(dirPath));
	}
	/**
	 * 生成人工核查conf.ini文件
	 */
	public void readFileToHanSystem() {
		String dirPath = "C:\\Users\\SinotnLibin\\Desktop\\广西测试人工核查客户端\\";
		File[] filePath = FileUtils.getAllFileByPath(dirPath);
		for (int i = 0; i < filePath.length; i++) {
			long checkNum = loadCRC32ByDir(filePath[i].getAbsolutePath()
					+ "\\data");
			System.out.println(filePath[i] + "====>" + checkNum);
			FileWriter fileWriter = null;
			BufferedWriter bw = null;
			try {
				File readFile = FileUtils.createFile(filePath[i]
						.getAbsolutePath() + "\\conf\\conf.ini");
				fileWriter = new FileWriter(readFile);
				bw = new BufferedWriter(fileWriter);
				bw.write("[seting]");
				bw.newLine();
				bw.write("files=" + checkNum);
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileWriter.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
