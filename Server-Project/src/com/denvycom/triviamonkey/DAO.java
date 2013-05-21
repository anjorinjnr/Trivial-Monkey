package com.denvycom.triviamonkey;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

public class DAO extends DAOBase {

	static {
        ObjectifyService.register(Category.class);
        ObjectifyService.register(Question.class);
    }

    /** Your DAO can have your own useful methods
    public T createCategory(T o)
    {
        ofy().put(o);
        if(o.getId() != null)
        	return o;
        else return null;
   
    }
    public void put(Object c)
    {
        ofy().put(c);
   
    } */

}
