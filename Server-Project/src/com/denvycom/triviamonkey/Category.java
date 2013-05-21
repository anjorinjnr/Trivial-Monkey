package com.denvycom.triviamonkey;

import java.util.List;
import javax.persistence.Id;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.Key;

/**
 * This File: Category.java
 * Represent a category or sub-category of related questions.
 * A category has a name and belongs to 1 or none category.
 * 
 * @author tola.anjorin@gmail.com
 *
 */
public class Category {

	private @Id	Long id;
	private String name;
	private Key<Category> parentCategory;
	/** data access object */
	private static final DAO dao = new DAO();

	Category() {
	}
	Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
			this.name = name;
	}

	public void delete() {
		dao.ofy().delete(this);
	}
	/**
	 * adds a question to this category,
	 * the question is also set to belong to this category
	 * @param question
	 */
	public void addQuestion(Question question) {
		question.setCategory(this);
		dao.ofy().put(question);
	}

	/**
	 * adds a list of questions to this category
	 * @param questions
	 */
	public void addQuestions(List<Question> questions) {
		for (Question question : questions){
			question.setCategory(this);
		}
		dao.ofy().put(questions);
	}
	
	/**
	 * adds a sub/child category to this category,
	 * sets the category's parent to this category, then saves the category
	 * @param category
	 * @return
	 */
	public boolean addSubCategory(Category category) {
		category.setParentCategory(this);
		dao.ofy().put(category);
		return (category.id != null)? true : false;
	}

	public void setParentCategory(Category category) {
		this.parentCategory = new Key<Category>(Category.class, category.getId());
	}
	
	/**
	 * saves/updates this category to the datastore
	 * @return
	 */
	public boolean save(){
		dao.ofy().put(this);
		return (this.id != null)? true : false;
	}
	
	/** 
	 * returns a category with the corresponding id
	 * @param id
	 * @return
	 */
	public static Category findCategoryByID(Long id){
	
		return dao.ofy().find(Category.class, id);
	}
	/**
	 * Returns the list of main categories
	 * @return
	 */
	public static List<Category> getMainCategories(){
		
		Iterable<Category> query =  dao.ofy().query(Category.class).filter("parentCategory", null);
		return Lists.newArrayList(query);
		
	}
	
	/**
	 * Returns the subcategories for provided category id
	 * @param id
	 * @return
	 */
	public static List<Category> getSubCategories(Long id){
		Iterable<Category> query =  dao.ofy().query(Category.class)
											 .filter("parentCategory", new Key<Category>(Category.class, id));
		return Lists.newArrayList(query);	
	}
	

}
