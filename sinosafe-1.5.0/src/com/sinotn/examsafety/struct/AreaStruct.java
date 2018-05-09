package com.sinotn.examsafety.struct;

import struct.CString;
import struct.StructClass;
import struct.StructField;
@StructClass
public class AreaStruct extends BaseStruct{
	@StructField(order = 0)
	public int code ;
	
	
	@StructField(order = 1)
	public CString name = new CString(46);
	
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return getString(name);
	}

	public void setName(String name) {
		this.name = new CString(name, this.name.length());
	}

}
