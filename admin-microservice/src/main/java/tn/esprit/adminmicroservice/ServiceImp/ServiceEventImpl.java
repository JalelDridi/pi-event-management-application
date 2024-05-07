package tn.esprit.adminmicroservice.ServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.EventDto;
import tn.esprit.adminmicroservice.Ennum.Role;
import tn.esprit.adminmicroservice.Entities.StatusEvent;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Repository.RepositoryEvent;
import tn.esprit.adminmicroservice.Service.ServiceEvent;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceEventImpl implements ServiceEvent {

    @Autowired
    RepositoryEvent repositoryEvent;


    @Override
    public List<EventDto> getALLEvents() {
        // Remplacer "event-service-url" par l'URL réelle du microservice event
        // Construire l'URL sans paramètres
        String ressourceMicroserviceUrl = "http://localhost:8088/api/Event/getall";

        // Faire une requête GET au microservice event pour récupérer la liste des events
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EventDto>> responseEntity = restTemplate.exchange(
                ressourceMicroserviceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EventDto>>() {}
        );
        // Vérifier si la requête a réussi (code de statut HTTP 200)
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // Retourner la liste des events
        } else {
            // Gérer le cas où la requête n'a pas réussi
            // Vous pouvez lancer une exception ou le gérer en fonction des besoins de votre application
            throw new RuntimeException("Failed to fetch users from User Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Override

    public StatusEvent AcceptEvent(Long id) {
        List<EventDto> events = getALLEvents();

        for (EventDto event : events) {
            StatusEvent eventSaved=new StatusEvent();

            if (event.getEventId().equals(id)) {
                eventSaved.setName(event.getName());
                eventSaved.setStartDate(event.getStartDate());
                eventSaved.setEndDate(event.getEndDate());
                eventSaved.setClub(event.getClub());
                eventSaved.setStatus(true);
                repositoryEvent.save(eventSaved);
               //notif de event confirmé
                return eventSaved;
            }

        }
        return null;
    }
    @Override
    public StatusEvent refuserEvent(Long id) {
        List<EventDto> events = getALLEvents();

        for (EventDto event : events) {
            StatusEvent eventSaved=new StatusEvent();

            if (event.getEventId().equals(id)) {
                eventSaved.setName(event.getName());
                eventSaved.setStartDate(event.getStartDate());
                eventSaved.setEndDate(event.getEndDate());
                eventSaved.setClub(event.getClub());
                eventSaved.setStatus(false);
                repositoryEvent.save(eventSaved);
                //notif de event refusé
                return eventSaved;
            }

        }
        return null;
    }

    @Override
    public List<StatusEvent> findAllconfEvents() {

        List<StatusEvent> listaff = new ArrayList<>();

        // Retrieve all statuses
        List<StatusEvent> events = repositoryEvent.findAll();
        for (StatusEvent event : events) {
            if (event.isStatus()) {
                listaff.add(event);
            }

        }
        return listaff;
    }

    @Override
    public Double[] pourcentageEvents() {
        int EventAccepte = findAllconfEvents().size();
        int allEvent = getALLEvents().size();
        int refusedEvent = allEvent - EventAccepte;

        double percentageAccept = (double) EventAccepte / allEvent * 100;
        double percentageRefus = (double) refusedEvent / allEvent * 100;

        return new Double[]{Math.round(percentageAccept * 100.0) / 100.0, Math.round(percentageRefus * 100.0) / 100.0};
    }







}
