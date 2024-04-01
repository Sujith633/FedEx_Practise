package org.studyeasy.jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.studyeasy.jsf.model.DBHelper;
import org.studyeasy.jsf.model.User;



@ManagedBean(name="usersOpt")
public class UsersOperations {
	int users_id;
	String username,email;
    
	public UsersOperations() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public String addUser() {
		// System.out.println(username);
		// System.out.println(email);
		User user = new User(username, email);
		new DBHelper().addUser(user);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index";
	}
	public String updateUser() {
        User user = new User(users_id, username, email);
        new DBHelper().updateUser(user);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index";
	}
	public void loadUserData(int users_id) {
		User user = new DBHelper().getUser(users_id);
		setUsers_id(user.getUsers_id());
		setUsername(user.getUsername());
		setEmail(user.getEmail());
	}
	public String deleteUser(int users_id) {
		new DBHelper().deleteUser(users_id);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index";
	}
	
}







