package com.ashokit.serviceimpl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ashokit.entity.Phonebook;
import com.ashokit.service.PhonebookService;
@Service
public class PhonebookServiceImpl implements PhonebookService {

	private String SAVE_CONTACT_URL = "http://localhost:8080/contact";

	private String GET_CONTACT_URL = "http://localhost:8080/contact/{id}";

	private String GETALL_CONTACT_URL = "http://localhost:8080/allcontact";

	private String DELETE_CONTACT_URL = "http://localhost:8080/deletecontact/{id}" ;


	@Override
	public String saveContact(Phonebook details) {
		WebClient webClient = WebClient.create();
		String status = webClient.post().uri(SAVE_CONTACT_URL).bodyValue(details).retrieve().bodyToMono(String.class)
				.block();

		return status;
	}

	@Override
	public Phonebook getById(Integer id) {

		WebClient webClient = WebClient.create();

		Phonebook contact = webClient.get()
				.uri(GET_CONTACT_URL,id).retrieve()
				.bodyToMono(Phonebook.class)
				.block();

		return contact;
	}

	@Override
	public List<Phonebook> getAllContacts() {

		WebClient webClient = WebClient.create();

		List<Phonebook> allContacts = webClient.get().uri(GETALL_CONTACT_URL).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Phonebook>>() {
				}).block();

		return allContacts;
	}

	@Override
	public String deletById(Integer id) {
		WebClient webClient = WebClient.create();

		String status = webClient.delete()

				.uri(DELETE_CONTACT_URL,id).retrieve().bodyToMono(String.class).block();

		return status;

	}

}
