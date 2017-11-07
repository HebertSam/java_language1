package com.project.crudassign.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.crudassign.crudassignApplication;
import com.project.crudassign.models.Language;
import com.project.crudassign.services.CrudService;

@Controller
public class crudController{
	//Member variables go here
	private CrudService crudService;

	public crudController(CrudService crudService){
		this.crudService = crudService;
	}
	
	@RequestMapping("")
	public String index(@ModelAttribute("Language") Language language, Model model){
		// System.out.println(all);
		if (crudService.getAll() != null){
			System.out.println(crudService.getAll());
			ArrayList<Language> all = crudService.getAll();
			model.addAttribute("languages", all);
			return "index";
		} else {
			return "index";
		}
	}
	@PostMapping("/language/new")
	public String create(@Valid @ModelAttribute("Language") Language language, BindingResult result, RedirectAttributes flash){
		System.out.println(result);
		if (result.hasErrors()){
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/";
		}
		crudService.createLang(language);
		return "redirect:/";
	}
	@RequestMapping("/update/{id}")
	public String thisLang(@ModelAttribute("Language") Language language, @PathVariable("id") int id, Model model, HttpSession session){
		session.setAttribute("id", id);
		Language thislang = crudService.getOne(id);
		System.out.println(thislang);
		model.addAttribute("lang", thislang);
		return "edit";
	}
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("Language") Language language, BindingResult result, HttpSession session, RedirectAttributes flash){
		int id = (int) session.getAttribute("id");
		if (result.hasErrors()){
			System.out.println(result.getAllErrors());
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/update/" + id;
		}
		crudService.updateLang(id, language);
		return "redirect:/";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id){
		crudService.delete(id);
		return "redirect:/";
	}
}
