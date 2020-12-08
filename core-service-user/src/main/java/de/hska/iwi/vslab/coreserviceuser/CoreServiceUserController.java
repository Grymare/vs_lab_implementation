package de.hska.iwi.vslab.coreserviceuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.json.JSONArray;

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
    private UserRepository userRepository;

    /**
     * Erstellt ein neues User-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param userName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/user{username, firstname, lastname, password, permission}", method = RequestMethod.POST)
    public HttpStatus postUser(@RequestParam(required = true) String username,
            @RequestParam(required = true) String firstname, @RequestParam(required = true) String lastname,
            @RequestParam(required = true) String password, @RequestParam(required = false) int permission) {

        Account newUser = new Account(username, firstname, lastname, password, permission);
        userRepository.save(newUser);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ResponseEntity<Object> login(@RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        String sessionKey = userLogin(username, password);

        if (!sessionKey.equals("SessionKey")) {
            return new ResponseEntity<Object>("", HttpStatus.OK);
        }

        return new ResponseEntity<Object>(sessionKey, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public HttpStatus logout(@PathVariable String sessionkey) {

        userLogout(sessionkey); // Dummy for now, Aufgabe 4

        return HttpStatus.OK;
    }

    /**
     * 
     * @param username
     * @param password
     * @return Sessionkey to authenticate the user
     */
    private String userLogin(String username, String password) {

        Iterable<Account> userIterable = userRepository.findAll();

        for (Account user : userIterable) {
            if (user.getUsername() == username) {
                if (user.getPassword().equals(password)) {
                    return "SessionKey";
                } else {
                    return "WrongPassword";
                }
            }
        }
        return "UserDoesNotExist";
    }

    private boolean userLogout(String sessionkey) {
        return true;
    }
}