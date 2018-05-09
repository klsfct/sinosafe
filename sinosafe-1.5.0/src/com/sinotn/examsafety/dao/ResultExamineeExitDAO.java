package com.sinotn.examsafety.dao;

import java.util.List;

import com.sinotn.examsafety.po.ResultExamineeExit;

public class ResultExamineeExitDAO extends BaseDAO<ResultExamineeExit, String> {

	public ResultExamineeExit getResultExamineeExit(String subject,String licence){
		String[] propertyNames = {"subject","licence"};
		Object[] values = {subject, licence};
		List<ResultExamineeExit> list = this.findByPropertyArry(propertyNames, values);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
