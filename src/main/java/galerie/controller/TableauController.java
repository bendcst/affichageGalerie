package galerie.controller;

import galerie.dao.TableauRepository;
import galerie.entity.Tableau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/tableau")
public class TableauController {

    @Autowired
    private TableauRepository tableauDao;

    @GetMapping(path = "show")
    public String afficheTousLesTableaux(Model model){
        model.addAttribute("tableaux", tableauDao.findAll());
        return "afficheTableaux";
    }

    @PostMapping(path = "save")
    public String ajouteLeTableauPuisMontreLaListe(Tableau tableau, RedirectAttributes redirectInfo){
        String message;
        try {
            tableauDao.save(tableau);
            message = "Le tableau " + tableau.getTitre() + " est enregistre";
        } catch (DataIntegrityViolationException e){
            message = "Echec d'execution, veuillez reessayer";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show";
    }

    @GetMapping(path = "delete")
    public String supprimeUnTableauPuisMontreLaListe(@RequestParam("id") Tableau tableau, RedirectAttributes redirectInfo){
        String message = "Le tableau " + tableau.getTitre() + " est supprime";
        try {
            tableauDao.delete(tableau);
        } catch (DataIntegrityViolationException e){
            message = "Echec de suppression, veuillez reessayer";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show";
    }

    
}

