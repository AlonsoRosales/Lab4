package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.Distribuidoras;
import edu.pucp.gtics.lab4_gtics_20221.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab4_gtics_20221.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    @GetMapping(value = {"/lista"})
    public String listaDistribuidoras (Model model){
        List<Distribuidoras> list = distribuidorasRepository.findAll();
        model.addAttribute("lista",list);
        return "distribuidoras/lista";
    }



    public String editarDistribuidoras(Model model, @RequestParam("id") int id){
        Optional<Distribuidoras> optDistribuidora = distribuidorasRepository.findById(id);

        if (optDistribuidora.isPresent()) {
            Distribuidoras distribuidora = optDistribuidora.get();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", paisesRepository.findAll());
            return "distribuidora/editarFrm";
        } else {
            return "redirect:/distribuidoras";
        }
    }


    @GetMapping(value = "/nueva")
    public String nuevaDistribuidora(@ModelAttribute("distribuidora") Distribuidoras distribuidoras,Model model){
        model.addAttribute("listaPaises",paisesRepository.findAll());
        return "distribuidoras/editarFrm";
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
