package com.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ashokit.entity.Phonebook;

@Component
public interface PhonebookService {
	
	public String saveContact (Phonebook details);

	public Phonebook getById(Integer id);

	public List <Phonebook> getAllContacts();

	public String deletById(Integer id);


}
