package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.Distribuidoras;
import edu.pucp.gtics.lab4_gtics_20221.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab4_gtics_20221.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/distribuidoras")

public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    /*@GetMapping(value = {"/lista"})
    public String listaDistribuidoras ( ){

    }


    public String editarDistribuidoras(){

    }

    public String nuevaDistribuidora( ){

    }

    public String guardarDistribuidora( ){

    }*/

    public String guardarDistribuidora(@ModelAttribute("distribuidora") @Valid Distribuidoras distribuidora, BindingResult bindingResult,
                                       RedirectAttributes attr,
                                       Model model ){

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPaises", paisesRepository.findAll());
            return "distribuidoras/editarFrm";
        } else {

            if (distribuidora.getId() == null) {
                attr.addFlashAttribute("msg", "Distribuidora creada exitosamente");
                distribuidorasRepository.save(distribuidora);
                return "redirect:/distribuidoras";
            } else {
                distribuidorasRepository.save(distribuidora);
                attr.addFlashAttribute("msg", "Distribuidora actualizada exitosamente");
                return "redirect:/distribuidoras";
            }
        }


    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        if (opt.isPresent()) {
            distribuidorasRepository.deleteById(id);
        }
        return "redirect:/distribuidoras/lista";
    }

}
