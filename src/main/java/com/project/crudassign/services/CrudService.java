package com.project.crudassign.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.scripting.config.LangNamespaceUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.project.crudassign.models.Language;

@Service
public class CrudService {
	private final ArrayList<Language> languages = new ArrayList<Language>(Arrays.asList(
		new Language("java", "bob", "1.2")
	));
	// private Language language = new Language();
		
	public CrudService(){

	}

	public ArrayList<Language> getAll(){
		System.out.println(languages);
		return languages;
	}

	public Language getOne(int id){
		if (id < languages.size()){
			return languages.get(id);
		} else {
			return null;
		}
		
	}

	public void createLang(Language language){
		System.out.println(language);
		languages.add(language);
	}

	public void updateLang(int id, Language language){
		if (id < languages.size()){
			languages.set(id, language);
		} 
	}
	public void delete(int id){
		languages.remove(id);
	}
	
	// Crud methods to act on services go here.
}
