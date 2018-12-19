package io.swagger.api;

import io.swagger.model.User;
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

@CrossOrigin(origins = "*")
@Controller
public class UserApiController implements UserApi {

	private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Void> deleteUser(
			@ApiParam(value = "ID of the user.", required = true) @PathVariable("id") Integer id) {
		User value = Database.users.remove(id);
		if (value == null) {
			return ResponseUtils.<Void>make404();
		}

		return ResponseUtils.makeOkEmpty();
	}

	public ResponseEntity<User> readUser(
			@ApiParam(value = "ID of the user.", required = true) @PathVariable("id") Integer id) {
		User value = Database.users.get(id);
		if (value == null) {
			return ResponseUtils.<User>make404();
		}

		return ResponseUtils.makeOk(value);
	}

	public ResponseEntity<Void> updateUser(
			@ApiParam(value = "ID of the user.", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "Updated data.", required = true) @Valid @RequestBody User body) {
		body.id(id);

		if (!Database.users.containsKey(id)) {
			return ResponseUtils.<Void>make404();
		}

		Database.users.put(id, body);

		return ResponseUtils.makeOkEmpty();
	}
}
