package de.hska.iwi.vslab.coreserviceuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreServiceUserController {

    @Autowired
    private DatabaseUser dbUser;

    /**
     * Erstellt ein neues User-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param userName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/User", method = RequestMethod.POST)
    public HttpStatus postUser(@RequestBody User newUser) {

        dbUser.postUser(newUser); // schauen wie wir in die DB schreiben können
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/User/login", method = RequestMethod.GET)
    public ResponseEntity<User> login(@RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        String sessionKey = userLogin(username, password); // ?? ?? ? ? ? ? ? ? ? ?

        if (sessionKey == null) {
            return HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(sessionKey, HttpStatus.OK);
    }

    @RequestMapping(value = "/User/logout", method = RequestMethod.GET)
    public ResponseEntity<User> logout(@PathVariable String sessionkey) {

        userLogout(sessionkey); // ?? ?? ? ? ? ? ? ? ? ?

        return HttpStatus.OK;
    }

    /**
     * 
     * @param username
     * @param password
     * @return Sessionkey to authenticate the user
     */
    private String userLogin(String username, String password){

        if (dbUser.(findUserByName(username)).getPassword().equals(password) ){

        } else {
            return FUCK_OFF;
        }

    }
}