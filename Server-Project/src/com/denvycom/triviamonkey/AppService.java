package com.denvycom.triviamonkey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
/**
 * This File: AppService.java
 * RESTFUL Web Service to be consumed by mobile clients. Returns JSON data!
 * 
 * @author tola.anjorin@gmail.com
 *
 */
@Path("/api")
public class AppService {

	private static final AppModel model = new AppModel();
	private static final Gson gson = new Gson();

	/**
	 * returns a list of all top categories
	 * @return format is JSON
	 */
	@Path("/categories")
	@GET
	@Produces("application/json")
	public String getCategories() {
		try {
			List<Category> categories =  model.getCategories();
			return gson.toJson(categories);
		} catch (Exception ex) {
			// log error
			return "";
		}

	}
}
