package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.*;
import edu.pucp.gtics.lab4_gtics_20221.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"", "/","/juegos/lista"})
    public String listaJuegos (Model model){
        List<Juegos> juegosList = juegosRepository.findAll();
        model.addAttribute("lista",juegosList);
        return "juegos/lista";
    }

    public String vistaJuegos ( ){
        return "juegos/comprado";
    }
    @GetMapping("/juegos/nuevo")
    public String nuevoJuegos(@ModelAttribute("juego") Juegos juegos, Model model){
        List<Generos> generosList = generosRepository.findAll();
        List<Plataformas> plataformasList = plataformasRepository.findAll();
        List<Distribuidoras> distribuidorasList = distribuidorasRepository.findAll();
        model.addAttribute("generos",generosList);
        model.addAttribute("plataformas",plataformasList);
        model.addAttribute("distribuidoras",distribuidorasList);
        return "juegos/editarFrm";
    }

    public String editarJuegos( ){
        return "juegos/editarFrm";
    }

    public String guardarJuegos( ){
        return "redirect:/juegos/lista";
    }

    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }

}
