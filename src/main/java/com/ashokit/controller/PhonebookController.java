package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.entity.Phonebook;
import com.ashokit.service.PhonebookService;

@Controller
public class PhonebookController {

	@Autowired
	private PhonebookService service;

	@GetMapping("/")
	public String indexPage(Model model) {

		model.addAttribute("contactDetails", new Phonebook());

		return "indexhtml";

	}

	@PostMapping("/save")
	public String saveContact(@ModelAttribute("contactDetails") Phonebook contactDetails, Model model) {

		String status = service.saveContact(contactDetails);

		model.addAttribute("saveMsg", status);

		model.addAttribute("contactDetails", new Phonebook());

		return "indexhtml";

	}

	@GetMapping("/retrieveContact")
	public String contactById(@RequestParam("id") Integer id, Model model) {

		Phonebook  book= service.getById(id);

		model.addAttribute("userContacts", book);

		return "contact";
	}

	@GetMapping("/viewcontacts")
	public String allContacts(Model model) {

		List<Phonebook> allContacts = service.getAllContacts();

		model.addAttribute("userContacts", allContacts);
		return "contact";
	}

	@GetMapping("/delete")
	public String deletecontact(@RequestParam("id") Integer id, Model model) {

		String status = service.deletById(id);

		model.addAttribute("deleteMsg", status);
		List<Phonebook> allContacts = service.getAllContacts();

		model.addAttribute("userContacts", allContacts);
		
		return "contact";
	}

	@GetMapping("/update")
	public String edit(@RequestParam("id") Integer id, Model model) {

		Phonebook findById = service.getById(id);

		model.addAttribute("contactDetails", findById);

		return "indexhtml";

	}

}



