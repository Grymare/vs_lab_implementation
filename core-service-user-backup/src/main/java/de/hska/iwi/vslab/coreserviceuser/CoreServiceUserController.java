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
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param newUser Daten des neuen Users
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public HttpStatus postUser(@RequestBody Account newUser) {

        Account user = new Account(newUser.getUsername(), newUser.getFirstname(), newUser.getLastname(),
                newUser.getPassword(), newUser.getPermission());

        userRepository.save(user);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ResponseEntity<Object> login(@RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        String sessionKey = userLogin(username, password);

        if (!sessionKey.equals("SessionKey")) {
            return new ResponseEntity<Object>("", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Object>(sessionKey, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public HttpStatus logout(@RequestParam(required = true) String sessionkey) {

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
            if (user.getUsername().equals(username)) {
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