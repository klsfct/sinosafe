package com.sinotn.examsafety.test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteOrder;

import com.sinotn.common.utils.StringUtils;
import com.sinotn.examsafety.struct.AreaStruct;

import struct.JavaStruct;

public class CreateDataTest {

	public static void main(String[] args){
		System.out.println("zanggc123456");
		CreateDataTest createDataTest = new CreateDataTest();
		createDataTest.createAreaData();
		createDataTest.createExamPlaceData();
		//createDataTest.createExamRoomData();
	}
	
	/**
	 * 生成规则：文件名称city
	 * 文件内容：保存的是地市信息,每一条固定占用48个字节,前两个字节是数字类型的城市编号,后46个字节是ansi编码的城市名称,名称长度应小于46字节,已\0结束.
	 */
	public void createAreaData(){
		String filePath = "D:\\司法部国家司法考试\\2016年国家司法考试\\考点安全\\考点安全数据处理\\";
		String fileName = "city";
		System.out.println("createAreaData......start");
		FileOutputStream fos = null;
        try{
        	fos = new FileOutputStream(filePath + fileName);
        	AreaStruct struct = new AreaStruct();
            struct.setCode(Short.parseShort("2301"));
            struct.setName(StringUtils.char2Gb2312("哈尔滨市"));
			byte[] b = JavaStruct.pack(struct, ByteOrder.LITTLE_ENDIAN);
			fos.write(b);
			fos.flush();
        }
        catch(Exception e){
        	e.printStackTrace();
            System.out.println(e);
        }
        finally{
            try{
                fos.close();
            }
            catch(Exception e2){
                System.out.println(e2);
            }
        }
		System.out.println("createAreaData......end");
	}
	
	public void createExamPlaceData(){
		System.out.println("createExamPlace......");
		byte[] buf = new byte[]{0x50,0x51,0x48,0x49};
        
        File file = new File("d:/1.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buf);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}


	public void createExamRoomData(){
		System.out.println("createExamRoom......");
	}
}
