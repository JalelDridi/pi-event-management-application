package tn.esprit.review_module.controllers;

import jakarta.ws.rs.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import tn.esprit.review_module.Dtos.ReviewUserDto;
import tn.esprit.review_module.entities.Reclamation;
import tn.esprit.review_module.entities.TypeReclamation;
import tn.esprit.review_module.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
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
    public Reclamation addReclamation( @RequestBody Reclamation reclamation ){
        /*String userId = reclamation.getUserId() ;
        String userMicroserviceUrl = UriComponentsBuilder
                .fromUriString("http://localhost:8091/api/v1/users/{userId}")
                .buildAndExpand(userId)
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReviewUserDto> responseEntity = restTemplate.getForEntity(userMicroserviceUrl, ReviewUserDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ReviewUserDto userDto = responseEntity.getBody();
            if (userDto != null) {
                String userEmail = userDto.getEmail();
                reclamationService.SendReclamationEmail(userEmail);
            } else {
                throw new RuntimeException("User DTO is null.");
            }
        } else {
            throw new RuntimeException("Failed to fetch user from User Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }*/
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
    @GetMapping("/getreclamationsbyuseridandeventid/{userid}/{eventid}")
    @ResponseBody
    public List<Reclamation> findreclamationbyuseridandeventid(@PathVariable String userid, @PathVariable Long eventid){
        return reclamationService.findreclamationbyeventidanduserid(userid, eventid);

    }

    @DeleteMapping("/deletereclamation/{idrec}")
    @ResponseBody
    public void deletereclamation(@PathVariable Long idrec){
        reclamationService.deletereclamation(idrec);
    }




}