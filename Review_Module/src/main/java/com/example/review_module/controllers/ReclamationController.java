package com.example.review_module.controllers;

import com.example.review_module.entities.Reclamation;
import com.example.review_module.entities.Review;
import com.example.review_module.services.ReclamationService;
import com.example.review_module.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reclamations")
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


}
