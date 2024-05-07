package tn.esprit.eventmodule.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventmodule.ServiceImpl.IServiceMapbox;

import java.io.IOException;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/map")
public class MapBoxController {
    @Autowired
     public  IServiceMapbox iServiceMapbox ;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/MapBox/{x1}/{y1}/{x2}/{y2}")
    public Map<String , String> getInfo(@PathVariable String x1 , @PathVariable String y1 , @PathVariable String x2 , @PathVariable String y2) throws IOException {
        return iServiceMapbox.getInfo(x1,y1,x2,y2);
    }
    @GetMapping("/getAddress/{longitude}/{latitude}")
    public String getAddressFromCoordinates(@PathVariable Double longitude, @PathVariable Double latitude) throws IOException {
        return iServiceMapbox.getAddressFromCoordinates(longitude, latitude);
    }
}
