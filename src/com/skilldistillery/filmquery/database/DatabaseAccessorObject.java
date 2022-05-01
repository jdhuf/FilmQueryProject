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

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();

				film.setFilmId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDesc(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLangId(rs.getString(5));
				film.setRentDur(rs.getInt(6));
				film.setRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setRepCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setFeatures(rs.getString(11));
				film.setLanguage(rs.getString(12));
				film.setActors(findActorsByFilmId(filmId));

			}
			rs.close();
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
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				actor = new Actor();

				actor.setActorId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
				actor.setFilms(findFilmsByActorId(actorId));

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

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

				Film film = new Film();

				film.setFilmId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDesc(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLangId(rs.getString(5));
				film.setRentDur(rs.getInt(6));
				film.setRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setRepCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setFeatures(rs.getString(11));
				film.setLanguage(rs.getString(12));
				film.setActors(findActorsByFilmId(rs.getInt(1)));

				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
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

				Actor actor = new Actor();

				actor.setActorId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));

				actors.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmsBySearchKeyword(String keyword) {

		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.id, film.title, film.description, release_year, language_id, rental_duration, \n"
					+ " rental_rate, length, replacement_cost, rating, special_features, language.name\n"
					+ "	FROM film JOIN film_actor ON film.id = film_actor.film_id \n"
					+ " JOIN language ON language.id=film.language_id\n WHERE film.title LIKE ? OR film.description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Film film = new Film();

				film.setFilmId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDesc(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLangId(rs.getString(5));
				film.setRentDur(rs.getInt(6));
				film.setRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setRepCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setFeatures(rs.getString(11));
				film.setLanguage(rs.getString(12));
				film.setActors(findActorsByFilmId(rs.getInt(1)));

				films.add(film);

			}

			rs.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return films;

	}

}
