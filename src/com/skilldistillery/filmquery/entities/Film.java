package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {

	private int filmId;
	private String title;
	private String desc;
	private int releaseYear;
	private String langId;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private String language;

	public Film() {

	}

	public Film(int filmId, String title, String desc, int releaseYear, String langId, int rentDur, double rate,
			int length, double repCost, String rating, String features, String language) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.language = language;

	}

	private List<Actor> actors;

	public Film(List<Actor> actors) {
		super();
		this.actors = actors;
	}

	public int getFilmId() {
		return filmId;
	}

	public int setFilmId(int filmId) {
		return this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public String setTitle(String title) {
		return this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public String setDesc(String desc) {
		return this.desc = desc;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public int setReleaseYear(int releaseYear) {
		return this.releaseYear = releaseYear;
	}

	public String getLangId() {
		return langId;
	}

	public String setLangId(String langId) {
		return this.langId = langId;
	}

	public int getRentDur() {
		return rentDur;
	}

	public int setRentDur(int rentDur) {
		return this.rentDur = rentDur;
	}

	public double getRate() {
		return rate;
	}

	public double setRate(double rate) {
		return this.rate = rate;
	}

	public int getLength() {
		return length;
	}

	public int setLength(int length) {
		return this.length = length;
	}

	public double getRepCost() {
		return repCost;
	}

	public double setRepCost(double repCost) {
		return this.repCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public String setRating(String rating) {
		return this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public String setFeatures(String features) {
		return this.features = features;
	}

	public String getLanguage() {
		return language;
	}

	public String setLanguage(String language) {
		return this.language = language;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public List<Actor> setActors(List<Actor> actors) {
		return this.actors = actors;
	}

	@Override
	public String toString() {
		return "\n\nTitle = " + title + "\n" + "Description = " + desc + "\n" + "Release Year = " + releaseYear +
			 "\n" + "Rating = " + rating +  "\n" + "Language = " + language + "\n" + "Actors = "
				+ actors + "\n\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, desc, features, filmId, langId, language, length, rate, rating, releaseYear,
				rentDur, repCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(desc, other.desc)
				&& Objects.equals(features, other.features) && filmId == other.filmId && langId == other.langId
				&& Objects.equals(language, other.language) && length == other.length
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}

}