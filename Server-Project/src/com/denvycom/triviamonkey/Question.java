package com.denvycom.triviamonkey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.Key;

/**
 * This File: Question.java
 * Represents a question belong to a category.
 *  
 * @author tola.anjorin@gmail.com
 *
 */
public class Question {

	private @Id	Long id;
	private String content;
	private List<String> options;
	private int answer;
	private String longAnswer;
	private Key<Category> category;
	
	/** data access object */
	private static final DAO dao = new DAO();

	/**
	 * Sets the category this question belongs to 
	 * @param category
	 */
	public void setCategory(Category category){
		this.category = new Key<Category>(Category.class, category.getId());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Iterator<String> getOptions() {
		return options.iterator();
	}

	public void setOptions(List<String> options) {
		this.options = new ArrayList<>();
		this.options = options;
	}

	public void addOption(String option) {

		if (options != null)
			options = new ArrayList<>();

		options.add(option);

	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) throws Exception {
		if(options == null) throw new Exception("Options must be set before seting answer");
		if(answer > 0  && answer <= options.size())
			this.answer = --answer;
		else
			throw new Exception("Answer must be an integer greater than 1, reprenting the index of the correction option");
	}

	public String getLongAnswer() {
		return longAnswer;
	}

	public void setLongAnswer(String longAnswer) {
		this.longAnswer = longAnswer;
	}
	
	/**
	 * return all questions for the specified category
	 * @param categoryID
	 * @return
	 */
	public static List<Question> getQuestion(Long categoryID){
		Iterable<Question> query = dao.ofy().query(Question.class)
				 					.filter("category", new Key<Category>(Category.class, categoryID));
		return Lists.newArrayList(query);	
	}

}
