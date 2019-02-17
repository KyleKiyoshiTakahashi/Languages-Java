package com.codingdojo.Languages.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codingdojo.Languages.models.Language;
import com.codingdojo.Languages.repositories.LanguageRepository;


@Service
public class LanguageService {
	private final LanguageRepository langRepo;
	
	public LanguageService(LanguageRepository langRepo) {
		this.langRepo = langRepo;
	}
//	returns all languages
	public List<Language> allLanguages(){
		return langRepo.findAll();
	}
	// creates a language
    public Language createLanguage(Language l) {
        return langRepo.save(l);
    }
 // retrieves a language
    public Language findLanguage(Long id) {
        Optional<Language> optionalLanguage = langRepo.findById(id);
        if(optionalLanguage.isPresent()) {
            return optionalLanguage.get();
        } else {
            return null;
        }
    }

// updates language	
 	public void updateLanguage(@Valid Language language) {
 		langRepo.save(language);
 			
 	}
	public Language updateLanguage(Long id, String name, String creator, Double version) {
		Language langInDB = langRepo.findById(id).get();
		langInDB.setName(name);
		langInDB.setCreator(creator);
		langInDB.setVersion(version);
		return langRepo.save(langInDB);
	}
//	deletes language by ID
	public void deleteLanguage(Long id) {
		langRepo.deleteById(id);
		
	}
	
	
}
