package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String name = "student";
		String pwd = "student";

		Connection conn = DriverManager.getConnection(URL, name, pwd);
		String sql = "SELECT * FROM film WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();
		
		if ( rs.next() ) {
			 int id = rs.getInt("id");
			 String title = rs.getString("title");
			 String description = rs.getString("description");
			 Integer releaseYear = rs.getInt("release_year");
			 int languageId = rs.getInt("language_id");
			 int rentalDuration = rs.getInt("rental_duration");
			 double rentalRate = rs.getDouble("rental_rate");
			 int length = rs.getInt("length");
			 double replacementCost = rs.getDouble("replacement_cost");
			 String rating = rs.getString("rating");
			 String specialFeatures = rs.getString("special_Features");
			 
			 film = new Film(id, title, description, releaseYear, languageId, rentalDuration, 
					 rentalRate, length, replacementCost, rating, specialFeatures);
		
			 // the film exists now
			 // now set its actors
			 
//			 film.setActors(finish this);
		}
		
		ps.close();
		conn.close();
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		return null;
	}

}
