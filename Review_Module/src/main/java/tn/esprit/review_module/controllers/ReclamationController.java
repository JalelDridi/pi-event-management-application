package tn.esprit.review_module.controllers;

import jakarta.ws.rs.PathParam;
import tn.esprit.review_module.entities.Reclamation;
import tn.esprit.review_module.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reclamations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReclamationController {

    private final ReclamationService reclamationService;
    @Autowired
    public ReclamationController( ReclamationService reclamationService){

        this.reclamationService = reclamationService;
    }

    @PostMapping("/addreclamation")
    public Reclamation addReclamation(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                      @RequestBody Reclamation reclamation ){
        return reclamationService.addReclamation(reclamation);
    }
    @GetMapping("/getreclamations")
    @ResponseBody
    public List<Reclamation> getAllReclamations(){
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/getreclamationsbyeventid/{eventid}")
    @ResponseBody
    public List<Reclamation> findByeventId(@PathVariable Long eventid){
        return reclamationService.findByeventId(eventid);

    }

    @GetMapping("/getreclamationsbyuserid/{userid}")
    @ResponseBody
    public List<Reclamation> findByUserId(@PathVariable String userid) {
        return reclamationService.findByUserId(userid);

    }


}