package com.sinotn.examsafety.struct;

import java.io.UnsupportedEncodingException;

import struct.CString;

public class BaseStruct {

	private String charset = null;

	public BaseStruct() {

	}

	public BaseStruct(String charset) {
		this.charset = charset;
	}

	public String getString(CString cstr) {

		byte buffer[] = cstr.getBuffer();

		try {
			if (charset != null) {
				String str = new String(buffer, charset).trim();
				return str == null || str.length() == 0 ? null : str;

			}
		} catch (UnsupportedEncodingException e) {

		}
		String str = new String(buffer).trim();
		return str == null || str.length() == 0 ? null : str;
	}

}
