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
@RequestMapping("/juegos")
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

    @GetMapping(value = {"", "/","/lista"})
    public String listaJuegos (Model model){
        List<Juegos> juegosList = juegosRepository.findAll();
        model.addAttribute("lista",juegosList);
        return "juegos/lista";
    }

    public String vistaJuegos ( ){
        return "redirect:/juegos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(@ModelAttribute("juego") Juegos juego, Model model){
        List<Generos> generosList = generosRepository.findAll();
        List<Plataformas> plataformasList = plataformasRepository.findAll();
        List<Distribuidoras> distribuidorasList = distribuidorasRepository.findAll();
        model.addAttribute("generos",generosList);
        model.addAttribute("plataformas",plataformasList);
        model.addAttribute("distribuidoras",distribuidorasList);
        return "juegos/editarFrm";
    }

    @GetMapping("/editar")
    public String editarJuegos(@ModelAttribute("juego") Juegos juego, @RequestParam("id") int id, Model model){

        Optional<Juegos> optionalJuegos = juegosRepository.findById(id);
        if (optionalJuegos.isPresent()) {
            Juegos juegos = optionalJuegos.get();
           model.addAttribute("juego",juegos);
            List<Generos> generosList = generosRepository.findAll();
            List<Plataformas> plataformasList = plataformasRepository.findAll();
            List<Distribuidoras> distribuidorasList = distribuidorasRepository.findAll();
            model.addAttribute("generos",generosList);
            model.addAttribute("plataformas",plataformasList);
            model.addAttribute("distribuidoras",distribuidorasList);
            return "juegos/editarFrm";
        } else {
            return "redirect:/juegos/lista";
        }

    }

    @PostMapping("/guardar")
    public String guardarJuegos(@ModelAttribute("juego") @Valid Juegos juego,BindingResult bindingResult,RedirectAttributes attr,Model model ){

        if(bindingResult.hasErrors()){
            model.addAttribute("generos", generosRepository.findAll());
            model.addAttribute("distribuidoras", distribuidorasRepository.findAll());
            model.addAttribute("plataformas", plataformasRepository.findAll());
            return "juegos/editarFrm";

        }else {
            if (juego.getIdjuego() == 0) {
                attr.addFlashAttribute("msg1", "Juego creado exitosamente");
                juegosRepository.save(juego);
                return "redirect:/juegos/lista";
            } else {
                juegosRepository.save(juego);
                attr.addFlashAttribute("msg2", "Juego actualizado exitosamente");
                return "redirect:/juegos/lista";
            }
        }
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id,RedirectAttributes attr){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
            attr.addFlashAttribute("msg3", "Juego borrado exitosamente");
        }
        return "redirect:/juegos/lista";
    }

}
