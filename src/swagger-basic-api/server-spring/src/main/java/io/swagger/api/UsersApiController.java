package io.swagger.api;

import io.swagger.model.User;
import io.swagger.model.UserBase;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin (origins = "*")
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger (UsersApiController.class);

    private static ConcurrentHashMap<Integer, User> user_data = new ConcurrentHashMap<Integer, User> ();
    private static AtomicInteger user_last_id = new AtomicInteger (1);

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController (HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<Void> createUser (@ApiParam (value = "User data", required=true) @Valid @RequestBody User body) {
        body.setId (user_last_id.getAndIncrement ());
        user_data.put (body.getId (), body);
        return new ResponseEntity<Void> (HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteUser (@ApiParam (value = "User identifier", required=true) @PathVariable ("user_id") Integer userId) {
        User user = user_data.remove (userId);
        if (user == null) return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
        return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<User> readUser (@ApiParam(value = "User identifier", required=true) @PathVariable ("user_id") Integer userId) {
        User user = user_data.get (userId);
        if (user == null) return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }

    public ResponseEntity<List<UserBase>> readUsers () {
        List<UserBase> list = new ArrayList (user_data.values ());
        return new ResponseEntity<List<UserBase>> (list, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser (@ApiParam(value = "User identifier", required=true) @PathVariable ("user_id") Integer userId, @ApiParam (value = "User data", required=true) @Valid @RequestBody User body) {
        body.setId (userId);
        user_data.put (body.getId (), body);
        return new ResponseEntity<Void> (HttpStatus.RESET_CONTENT);
    }
}
