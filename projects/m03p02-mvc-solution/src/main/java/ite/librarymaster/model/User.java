package ite.librarymaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class models the Library user.
 *  
 * @author ivan.macalak@posam.sk
 *
 */
@Entity
@Table(name="USER_TBL")
@NamedQueries(value = { 
		@NamedQuery(name="user.findAll", query="from User"),
		@NamedQuery(name="user.findByUsername", query="from User where username=:username")
	})
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	@Column(unique=true, nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	@Enumerated(EnumType.STRING)
	private UserState userState;
	
	
	public User() {
	}
	
	public User(Long id, String name, String surname, UserState userState) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userState = userState;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserState getUserState() {
		return userState;
	}
	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((userState == null) ? 0 : userState.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userState != other.userState)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + ", userState=" + userState + "]";
	}
	
}
