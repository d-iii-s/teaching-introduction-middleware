package io.swagger.api;

import io.swagger.model.User;
import io.swagger.model.UserBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin(origins = "*")
@Controller
public class UsersApiController implements UsersApi {

	private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Void> createUser(
			@ApiParam(value = "User to be added.", required = true) @Valid @RequestBody User body) {
		String accept = request.getHeader("Accept");

		body.id(Database.usersIds.getAndIncrement());

		Database.users.put(body.getId(), body);

		return ResponseUtils.makeOkEmpty();
	}

	public ResponseEntity<List<UserBase>> readUsers() {
		Collection<User> all = Database.users.values();
		List<UserBase> result = new ArrayList<>(all.size());
		for (User u : all) {
			UserBase b = (new UserBase()).id(u.getId()).firstname(u.getFirstname()).lastname(u.getLastname())
					.email(u.getEmail());
			result.add(b);
		}

		return ResponseUtils.makeOk(result);
	}
}
