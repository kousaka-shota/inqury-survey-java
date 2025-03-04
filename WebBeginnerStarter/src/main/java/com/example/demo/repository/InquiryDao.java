package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryDao {
	
	void insertInquiry(Inquiry inquiry);
	
	int updateInquiry(Inquiry inquiry);
	
	List<Inquiry> getAll();
}
