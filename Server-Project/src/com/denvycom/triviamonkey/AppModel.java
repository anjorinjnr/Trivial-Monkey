package com.denvycom.triviamonkey;

import java.util.List;
/**
 * This File: AppModel.java
 * Implements application logic. Abstracts away the internals of the models 
 * from the webservice class and other high-level classes
 * 
 * @author tola.anjorin@gmail.com
 *
 */
public class AppModel {

	public List<Category> getCategories(){
		return Category.getMainCategories();
	}
}
