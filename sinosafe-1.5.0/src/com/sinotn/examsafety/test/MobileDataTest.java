package com.sinotn.examsafety.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteOrder;

import struct.JavaStruct;
import struct.StructException;
import struct.StructUnpacker;

import com.sinotn.common.utils.DateUtils;
import com.sinotn.examsafety.struct.MobileCardStruct;
import com.sinotn.examsafety.struct.MobileParameterStruct;
import com.sinotn.examsafety.struct.MobilePlaceStruct;



public class MobileDataTest {

	public static void main(String[] args){
		MobileDataTest mobileDataTest = new MobileDataTest();
		//mobileDataTest.placeTest();
		//mobileDataTest.parameterTest();
		mobileDataTest.cardTest();
	}
	
	private void placeTest(){
		System.out.println("placeTest..................start");
		MobilePlaceStruct mobilePlaceStruct = null;
		try {
			InputStream is = new FileInputStream("D:\\司法部国家司法考试\\2016年国家司法考试\\考点安全\\考点安全数据处理\\手持机上传数据格式\\1");
			StructUnpacker unPacker = JavaStruct.getUnpacker(is,
					ByteOrder.LITTLE_ENDIAN);
			mobilePlaceStruct = new MobilePlaceStruct();
			unPacker.readObject(mobilePlaceStruct);
			System.out.println("DataType==" + mobilePlaceStruct.getDataType());
			System.out.println("DumpEnergy==" + mobilePlaceStruct.getDumpEnergy());
			System.out.println("ExamPlace==" + mobilePlaceStruct.getExamPlace());
			System.out.println("SignalStrength==" + mobilePlaceStruct.getSignalStrength());
			System.out.println("Datetime==" + mobilePlaceStruct.getDatetime());
			System.out.println("Imei===" + mobilePlaceStruct.getImei());
			is.close();
				//targetFile.delete();
		} 
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (StructException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("placeTest..................end");
	}
	
	private void parameterTest(){
		System.out.println("parameterTest..................start");
		MobileParameterStruct mobileParameterStruct = null;
		try {
			InputStream is = new FileInputStream("D:\\司法部国家司法考试\\2016年国家司法考试\\考点安全\\考点安全数据处理\\手持机上传数据格式\\2");
			StructUnpacker unPacker = JavaStruct.getUnpacker(is,
					ByteOrder.LITTLE_ENDIAN);
			mobileParameterStruct = new MobileParameterStruct();
			unPacker.readObject(mobileParameterStruct);
			System.out.println("DataType==" + mobileParameterStruct.getDataType());
			System.out.println("DumpEnergy==" + mobileParameterStruct.getDumpEnergy());
			System.out.println("ExamPlace==" + mobileParameterStruct.getExamPlace());
			System.out.println("SignalStrength==" + mobileParameterStruct.getSignalStrength());
			System.out.println("Datetime==" + mobileParameterStruct.getDatetime());
			System.out.println("Imei===" + mobileParameterStruct.getImei());
			is.close();
				//targetFile.delete();
		} 
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (StructException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("parameterTest..................end");
	}
	private void cardTest(){
		System.out.println("cardTest..................start");
		MobileCardStruct mobileCardStruct = null;
		try {
			InputStream is = new FileInputStream("D:\\司法部国家司法考试\\2016年国家司法考试\\考点安全\\考点安全数据处理\\手持机上传数据格式\\3");
			StructUnpacker unPacker1 = JavaStruct.getUnpacker(is,
					ByteOrder.LITTLE_ENDIAN);
			MobileCardStruct mobileCardStruct1 = new MobileCardStruct();
			unPacker1.readObject(mobileCardStruct1);
			int examCardAmount = mobileCardStruct1.getExamCardAmount();
			System.out.println("examCardAmount====" + examCardAmount);
			if(examCardAmount > 0){
				InputStream is1 = new FileInputStream("D:\\司法部国家司法考试\\2016年国家司法考试\\考点安全\\考点安全数据处理\\手持机上传数据格式\\3");
				StructUnpacker unPacker = JavaStruct.getUnpacker(is1,
						ByteOrder.LITTLE_ENDIAN);
				mobileCardStruct = new MobileCardStruct(examCardAmount);
				unPacker.readObject(mobileCardStruct);
				System.out.println("DataType==" + mobileCardStruct.getDataType());
				System.out.println("DumpEnergy==" + mobileCardStruct.getDumpEnergy());
				System.out.println("ExamPlace==" + mobileCardStruct.getExamPlace());
				System.out.println("SignalStrength==" + mobileCardStruct.getSignalStrength());
				System.out.println("Datetime==" + DateUtils.getStr2LDate(mobileCardStruct.getDatetime()));
				System.out.println("Imei===" + mobileCardStruct.getImei());
				System.out.println("ExamSubject===" + mobileCardStruct.getExamSubject());
				System.out.println("ExamRoom===" + mobileCardStruct.getExamRoom());
				System.out.println("ExamCardAmount===" + mobileCardStruct.getExamCardAmount());
				for(int i = 0 ;i<mobileCardStruct.getExamCard().length;i++ ){
					System.out.println("ExamCard[" + i + "]===" + mobileCardStruct.getExamCard()[i]);
				}
			}
			is.close();
				//targetFile.delete();
		} 
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (StructException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("cardTest..................end");
	}
}
