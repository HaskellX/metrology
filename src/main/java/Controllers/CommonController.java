package Controllers;

import Application.Application;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @RequestMapping("/deleteall")
    public ResponseEntity DeleteAllData(){
        Application.getSession().createSQLQuery("delete from authors").executeUpdate();
        Application.getSession().createSQLQuery("delete from tracks").executeUpdate();
        Application.getSession().createSQLQuery("delete from albums").executeUpdate();
        return new ResponseEntity(HttpStatus.OK);
    }
}
