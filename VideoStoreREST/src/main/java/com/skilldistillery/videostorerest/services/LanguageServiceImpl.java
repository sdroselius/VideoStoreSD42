package com.skilldistillery.videostorerest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpavideostore.entities.Language;
import com.skilldistillery.videostorerest.repositories.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepo;
	
	@Override
	public List<Language> getAllLanguages() {
		return languageRepo.findAll();
	}

}
