package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private final String user = "student";
	private final String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {

			String sql = "SELECT film.id, film.title, description, release_year, language_id, rental_duration, \n"
					+ " rental_rate, length, replacement_cost, rating, special_features, language.name\n"
					+ "	FROM film JOIN film_actor ON film.id = film_actor.film_id \n"
					+ " JOIN language ON language.id=film.language_id where film.id = ?";

			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();

				film.setFilmId(filmResult.getInt(1));
				film.setTitle(filmResult.getString(2));
				film.setDesc(filmResult.getString(3));
				film.setReleaseYear(filmResult.getInt(4));
				film.setLangId(filmResult.getString(5));
				film.setRentDur(filmResult.getInt(6));
				film.setRate(filmResult.getDouble(7));
				film.setLength(filmResult.getInt(8));
				film.setRepCost(filmResult.getDouble(9));
				film.setRating(filmResult.getString(10));
				film.setFeatures(filmResult.getString(11));
				film.setLanguage(filmResult.getString(12));
				film.setActors(findActorsByFilmId(filmId));

			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:

				actor.setActorId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ...
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.id, film.title, description, release_year, language_id, rental_duration, \n"
					+ " rental_rate, length, replacement_cost, rating, special_features, language.name\n"
					+ "	FROM film JOIN film_actor ON film.id = film_actor.film_id \n"
					+ " JOIN language ON language.id=film.language_id\n" + "WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				short releaseYear = rs.getShort(4);
				String langId = rs.getString(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				String language = rs.getString(12);

				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, language);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM actor JOIN film_actor ON  actor.id=film_actor.actor_id "
					+ "JOIN film ON film.id=film_actor.film_id WHERE film.id= ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

}
