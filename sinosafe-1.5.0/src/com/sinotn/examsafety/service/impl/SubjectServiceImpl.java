package com.sinotn.examsafety.service.impl;

import java.util.List;

import com.sinotn.examsafety.dao.SubjectDAO;
import com.sinotn.examsafety.po.Subject;

public class SubjectServiceImpl {
	private SubjectDAO subjectDAO;
	
	public List<Subject> findSubject() {
		
		return this.subjectDAO.findSubjectList();
		
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	
}
