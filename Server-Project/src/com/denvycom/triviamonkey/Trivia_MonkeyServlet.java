package com.denvycom.triviamonkey;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Trivia_MonkeyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Maths category = 16;
		
		//Category c = Category.findCategoryByID(new Long(27));
		//createSubCategory(c, "Algebra");
		
		//getMainCategory();
		
		getSubCategory(new Long(27));
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	/* get main categories */
	void getSubCategory(Long id){
		List<Category> c = Category.getSubCategories(id);
		for(Category cat: c)
			println(cat.getName());
	}
	/* get main categories */
	void getMainCategory(){
		List<Category> c = Category.getMainCategories();
		for(Category cat: c)
			println(cat.getName());
	}
	/* create new sub category */
	Category createSubCategory(Category parent, String name) {
	
		Category sub = new Category(name);
		if(parent.addSubCategory(sub))
		{
			println("Sub Category Created");
			return sub;
		}
		else{
			println("Sub Category Creation failed");
			return null;
		}
	}
	
	/* create new category */
	Category createCategory(String name) {
	
		Category h = new Category(name);
		if (h.save()){
			println("Category Created");
			return h;
		}
		else{
			println("Category Creation failed");
			return null;
		}
	}
	
	
	void createQuestion(Category c) {
		/* create new question */
		Question q1 = new Question();
		q1.setContent("What is 1 + 1?");
		q1.setOptions(Arrays.asList("2", "3", "4", "5"));
		try {
			q1.setAnswer(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* add question to category */
		c.addQuestion(q1);
	}

	void print(Object s) {

		System.out.print(s);
	}

	void println(Object s) {

		System.out.println(s);
	}
}
