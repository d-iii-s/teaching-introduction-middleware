package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import io.swagger.model.User;

public class Database {
	public static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
	public static AtomicInteger usersIds = new AtomicInteger(0);

	static {
		try {
			loadUsersFromFile("data.json");
		} catch (IOException e) {
			try {
				loadUsersFromFile("data.init.json");
			} catch (IOException e2) {
				e2.printStackTrace(System.err);
				System.exit(1);
			}
		}
		
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
	}
	
	private static class ShutdownThread extends Thread {
		@Override
		public void run() {
			System.out.println("Shutting down!");
			
			Map<String, Object> top = new HashMap<>();
			top.put("users", users.values());
			top.put("users.seq", usersIds.get());
			
			Map<String, Object> wrapper = new HashMap<>();
			wrapper.put("data", top);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			try {
				writer.writeValue(new File("data.json"), wrapper);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
	}
	
	private static void loadUsersFromFile(String filename) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		byte[] jsonData = Files.readAllBytes(Paths.get(filename));
		
		JsonNode root = mapper.readTree(jsonData);
		JsonNode usersNode = root.path("data").path("users");
		User[] usersArray = mapper.treeToValue(usersNode, User[].class);
		
		for (User u : usersArray) {
			users.put(u.getId(), u);
		}
		
		JsonNode idNode = root.path("data").path("users.seq");
		usersIds.getAndSet(idNode.asInt(0));
		
		if (usersIds.get() == 0) {
			for (Integer i : users.keySet()) {
				if (i >= usersIds.get()) {
					usersIds.getAndSet(i + 1);
				}
			}
		}
	}
}
