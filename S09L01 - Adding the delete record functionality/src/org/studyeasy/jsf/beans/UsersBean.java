package org.studyeasy.jsf.beans;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.studyeasy.jsf.model.DBHelper;
import org.studyeasy.jsf.model.User;

@SessionScoped
@ManagedBean(name="usersBean")
public class UsersBean {
	
    List<User> usersList = new ArrayList<>();
    
	public UsersBean() {
		usersList = new DBHelper().listUsers();
    }
	
	public List<User> getUsersList() {
		return usersList;
	}
	
	public void setUsersList(List<User> users) {
		this.usersList = users;
	}

}
