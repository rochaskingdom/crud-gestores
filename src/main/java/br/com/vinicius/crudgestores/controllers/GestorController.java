package br.com.vinicius.crudgestores.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.vinicius.crudgestores.models.Gestor;
import br.com.vinicius.crudgestores.services.GestorService;

@Controller
public class GestorController {

	@Autowired
	private GestorService service;

	@RequestMapping("/")
	public String gestorView(Model model) {
		List<Gestor> listGestores = service.listAll();
		model.addAttribute("listGestores", listGestores);

		return "index";
	}

	@RequestMapping("/new")
	public String novoGestor(Model model) {
		Gestor gestor = new Gestor();
		model.addAttribute("gestor", gestor);

		return "novoGestor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveGestor(@ModelAttribute("gestor") Gestor gestor) {
		service.save(gestor);

		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editGestor(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("editarGestor");
		Gestor gestor = service.get(id);
		mav.addObject("gestor", gestor);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteGestor(@PathVariable(name = "id") int id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
