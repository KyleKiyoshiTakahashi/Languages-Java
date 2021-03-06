package com.codingdojo.Languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.Languages.models.Language;
import com.codingdojo.Languages.services.LanguageService;




@Controller
public class LanguagesController {
	private final LanguageService languageService;
	
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@RequestMapping("/")
	public String root(Model model) {
		return "redirect:/languages";
	}
	
	@RequestMapping("/languages")
	public String index(Model model) {
        List<Language> languages = languageService.allLanguages();
        model.addAttribute("languages", languages);
        return "/languages/index.jsp";
	}
	@RequestMapping("/languages/new")
	public String newLanguage(@ModelAttribute("language") Language language) {
	        return "/languages/addnew.jsp";
	}
	
//	@RequestMapping(value="/languages", method=RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("language") Language language, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/languages/addnew.jsp";
//        } else {
//            languageService.createLanguage(language);
//            return "redirect:/laguages";
//        }
//    }
	@PostMapping("/languages/add")
	public String addLanguage(@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "/languages/addnew.jsp";
		}
		else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}
	}
	@RequestMapping("/languages/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
    	Language language = languageService.findLanguage(id);
    	model.addAttribute("language", language);
    	return "/languages/show.jsp";
    }
	@RequestMapping("/languages/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
        model.addAttribute("language", language);
        return "/languages/edit.jsp";
    }
	@RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("language") Language language, BindingResult result) {
        if (result.hasErrors()) {
            return "/languages/edit.jsp";
        } else {
            languageService.updateLanguage(language);
            return "redirect:/languages";
        }
    }
//	@RequestMapping(value="/languages/{id}/delete", method=RequestMethod.DELETE)
//    public String destroy(@PathVariable("id") Long id) {
//        languageService.deleteLanguage(id);
//        return "redirect:/languages";
//    }
	@RequestMapping("/languages/delete/{id}")
	public String removeLanguage(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
		}
	
}
