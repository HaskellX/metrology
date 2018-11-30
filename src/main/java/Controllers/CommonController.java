package Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {


    public ResponseEntity DeleteAllData(){

        return new ResponseEntity(HttpStatus.OK);
    }
}
