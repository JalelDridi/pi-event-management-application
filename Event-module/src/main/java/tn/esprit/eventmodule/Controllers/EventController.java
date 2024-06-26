package tn.esprit.eventmodule.Controllers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.UsersEventsDao;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
import tn.esprit.eventmodule.Dtos.ReviewDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.EventType;
import tn.esprit.eventmodule.Entities.StatusType;
import tn.esprit.eventmodule.ServiceImpl.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventmodule.ServiceImpl.ReviewsImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/Event")
public class EventController {

    @Autowired
    EventImpl eventimpl;
    @Autowired
    EventDao eventDao;
    @Autowired
    UsersEventsDao usersEventsDao;
    @Autowired
    ReviewsImpl reviewsImpl;

    @CrossOrigin("*")
    @PostMapping(value = "/addevent/{userid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> ajouterevent (@RequestParam ("file") MultipartFile file , @RequestParam("file1") MultipartFile file1, @ModelAttribute Event event , @PathVariable String userid){
       try {
           if( file != null && file1 != null) {
               byte[] imageData = file.getBytes();
               event.setImage(imageData);
               byte[] imageData1 = file1.getBytes();
               event.setImage1(imageData1);
           }
           Event savedEvent = eventimpl.addEvent(event,userid);
           return ResponseEntity.ok("succeeeded");

       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }

   }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageForEvent(@PathVariable Long id) {
        Optional<Event> optionalevent = Optional.ofNullable(eventDao.findById(id).get());
        if (optionalevent.isPresent()) {
            Event event = optionalevent.get();
            if (event.getImage() != null && event.getImage().length > 0) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(event.getImage());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/image1")
    public ResponseEntity<byte[]> getImage1ForEvent(@PathVariable Long id) {
        Optional<Event> optionalevent = Optional.ofNullable(eventDao.findById(id).get());
        if (optionalevent.isPresent()) {
            Event event = optionalevent.get();
            if (event.getImage() != null && event.getImage().length > 0) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(event.getImage());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/getEventById/{eventId}")
    public Optional<Event> getEventById(@PathVariable Long eventId){return eventimpl.findEventById(eventId);}

    @GetMapping ("/getall")
    public List<Event> getAllEvent () {
        return eventimpl.getallEvent();
    }
    @GetMapping("getAnEvent/{eventId}")
    public  Optional<Event> getAnEvent(@PathVariable Long eventId){return eventimpl.findEventById(eventId);}

//    @GetMapping("/events/{eventId}")
//
//    public Mono<tn.esprit.eventmodule.dtos.EventDetailsDto> getEventDetailsWithReviews(Long eventId) {
//        Mono<Event> eventDtoMono = eventimpl.findEventById(eventId);
//        Flux<ReviewDto> reviewsFlux = reviewsImpl.getReviewsByEventId(eventId);
//
//        return eventDtoMono.zipWith(reviewsFlux.collectList(), (eventDto, reviews) -> {
//            tn.esprit.eventmodule.dtos.EventDetailsDto eventDetails = new tn.esprit.eventmodule.dtos.EventDetailsDto();  // Assume constructor or setters to set properties from EventAdminDto
//            eventDetails.setEventId(eventDto.getEventId());
//            eventDetails.setName(eventDto.getName());
//            // set other properties...
//            eventDetails.setReviews(reviews);
//            return eventDetails;
//        });
//    }

    @PutMapping("/edited/{eventId}")
    public Event editedEvent (@PathVariable Long eventId, @RequestBody Event event){
        return eventimpl.editEvent(eventId, event) ;
    }
    @DeleteMapping("/deleteevent/{eventId}")
    public void deleteEvent (@PathVariable Long eventId){
        eventimpl.deleteEvent(eventId);
    }

    @GetMapping("/todaysEvents")
    public List<Event> getTodaysEvents() {
        Date today = new Date();
        return eventDao.findByStartDate(today);
    }
    @GetMapping("/completedevent")
    public List<Event> completedEvent (@RequestParam StatusType status){
        return eventDao.findByStatus(status);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> events = eventimpl.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/add-participation")
    @ResponseBody
    public void addParticipation(@RequestParam String userId,  @RequestParam long eventId) {
        eventimpl.affectUserToEvent(userId, eventId);
    }
    @GetMapping("/{eventId}/users")
    public ResponseEntity<List<UserDto>> displayUsersOfEvent(@PathVariable Long eventId) {
        try {
            List<UserDto> users = eventimpl.displayUserOfEvent(eventId);
            return ResponseEntity.ok(users);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/events/{type}")
    public List<Event> findeventbytype(EventType type){
        return eventDao.findByType(type);
    }

    @PostMapping("/userevents")
    public ResponseEntity<?> assignUserToEvents(@RequestParam String userId, @RequestParam Long eventIds) {

        eventimpl.assignEventToUser(userId,eventIds);
        return ResponseEntity.ok("Events assigned successfully to user with ID " + userId);

    }

    @GetMapping("/export-events")
    public ResponseEntity<byte[]> exportUsersToExcel() throws IOException {
        List<Event> event = eventDao.findAll(); // Obtenez la liste des utilisateurs depuis votre service

        // Créez un classeur Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Utilisateurs");

        // En-têtes
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(4).setCellValue("capacity");

        // Remplissez les données des utilisateurs
        int rowNum = 1;
        for (Event eventt : event) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(eventt.getEventId());
            row.createCell(1).setCellValue(eventt.getName());
            row.createCell(2).setCellValue(eventt.getCapacity());
        }

        // Convertissez le classeur en un tableau d'octets
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        // Préparez la réponse HTTP avec le contenu Excel
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "utilisateurs.xlsx");

        // Retournez la réponse avec le fichier Excel
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(outputStream.toByteArray());
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable String userId) {
        try {
            List<Event> events = usersEventsDao.findEventsByUserId(userId);
            return ResponseEntity.ok(events);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Or handle more gracefully depending on your API design
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null); // Provide a more generic error response
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        try {
            UserDto user = eventimpl.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    /***************************************
     STATISTIQUES
     *************************************************/

    @GetMapping("/percentages")
    public Map<String, Map<String, Double>> getEventPercentages() {
        return eventimpl.calculateEventPercentageByTypeAndStatus();
    }

    // Optional: Endpoint to display percentages in a more readable format
    @GetMapping("/display-percentages")
    public void displayEventPercentages() {
        eventimpl.displayEventPercentages();
    }

















//    @PostMapping("/assign-resource")
//    public ResponseEntity<String> assignResourceToEvent(@RequestParam Long resourceId, @RequestParam Long eventId) {
//        try {
//            eventimpl.assignResourceToEvent(resourceId, eventId);
//            return ResponseEntity.ok("Resource assigned to event successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign resource to event.");
//        }
//    }
//    @GetMapping("/{eventId}/resources")
//    public ResponseEntity<Map<String, List<ResourceDto>>> displayResourcesOfEvent(@PathVariable Long eventId) {
//        Map<String, List<ResourceDto>> resourcesByType = eventimpl.displayResourceOfEvent(eventId);
//        return ResponseEntity.ok(resourcesByType);
//    }

}
