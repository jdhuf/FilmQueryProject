package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Actor {

	private int id;
	private String FirstName;
	private String LastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(FirstName, LastName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(FirstName, other.FirstName) && Objects.equals(LastName, other.LastName) && id == other.id;
	}

	public void setFilms(List<Film> findFilmsByActorId) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public String toString() {
//		return "Actor [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + "]";
//	}
//	
	

}
