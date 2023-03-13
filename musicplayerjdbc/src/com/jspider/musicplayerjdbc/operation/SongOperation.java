package com.jspider.musicplayerjdbc.operation;

import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import com.jspider.musicplayerjdbc.song.Song;

public class SongOperation {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static FileReader fileReader;
	private static Properties properties;
	private static ResultSet resultSet;
	private static String query;
	private static int result;
	private static int size;
	private static Statement statement;
	private static int value;

	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "D:\\WEJA1\\musicplayerjdbc\\resources\\db_info.properties";
	static Song song = new Song();

	// Open connection method
	private static void openConnection() {
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);

			Class.forName(properties.getProperty("driverPath"));

			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Close connection method
	private static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Chose song to play
	public void choseToPlaySong() {
		System.out.println("Playing song...please wait");
		System.out.println("loading song list....");

		openConnection();
		try {
			statement = connection.createStatement();
			query = "Select count(*) from music_player";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				value = resultSet.getInt(1);

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (value > 0) {

			query = "select * from music_player";

			try {
				preparedStatement = connection.prepareStatement(query);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					song.setId(resultSet.getInt(1));
					song.setSongName(resultSet.getString(2));
					song.setSingerName(resultSet.getString(3));
					song.setMoveName(resultSet.getString(4));
					song.setDuration(resultSet.getDouble(5));
					System.out.println(song);
					System.out.println("-----------------------");

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			query = "Select * from music_player " + "where id=?";
			try {
				preparedStatement = connection.prepareStatement(query);

				System.out.println("Enter song id to play song ");
				int id = scanner.nextInt();
				song.setId(id);
				preparedStatement.setInt(1, song.getId());

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					song.setId(resultSet.getInt(1));
					song.setSongName(resultSet.getString(2));
					song.setSingerName(resultSet.getString(3));
					song.setMoveName(resultSet.getString(4));
					song.setDuration(resultSet.getDouble(5));
					System.out.println(song);

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Enter valid ID ");
				System.out.println();
			} finally {
				closeConnection();
			}

		} else {
			System.out.println("No song present in the list");
		}
	}

	// Playing all song
	public void playAllSong() {

		openConnection();
		query = "select * from music_player";

		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				song.setId(resultSet.getInt(1));
				song.setSongName(resultSet.getString(2));
				song.setSingerName(resultSet.getString(3));
				song.setMoveName(resultSet.getString(4));
				song.setDuration(resultSet.getDouble(5));
				System.out.println(song);
				System.out.println("-----------------------");
				Thread.sleep(3000);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Adding song
	public void addSong() {
		System.out.println("Adding song in list");

		openConnection();
		query = "insert into " + "music_player values(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);

			try {
				System.out.println("Enter number of song you want to add:");
				size = scanner.nextInt();
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Enter valid number");

			}
			for (int i = 1; i <= size; i++) {
				Song song = new Song();
				System.out.println();

				try {
					System.out.println("Enter id of song");
					int id = scanner.nextInt();
					song.setId(id);
				
					System.out.println("Enter song name");
					scanner.nextLine();
					String name = scanner.nextLine();
					song.setSongName(name);
					System.out.println("Enter singer name");
					String singer = scanner.nextLine();
					song.setSingerName(singer);
					System.out.println("Enter move name");
					String move = scanner.nextLine();
					song.setMoveName(move);
				
						System.out.println("Enter duration of song");
						double duration = scanner.nextDouble();
						song.setDuration(duration);
						preparedStatement.setInt(1, song.getId());
						preparedStatement.setString(2, song.getSongName());
						preparedStatement.setString(3, song.getSingerName());
						preparedStatement.setString(4, song.getMoveName());
						preparedStatement.setDouble(5, song.getDuration());

						result = preparedStatement.executeUpdate();

						System.out.println("Query ok " + result + "row(s) affected");
					} catch (InputMismatchException e) {
						System.out.println("enetre valid ID OR Duration");
					}
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConnection();

		}

	}

	// Removing Song
	public void removeSong() {
		System.out.println("Removing song from list....");

		try {
			openConnection();
			query = "Select * from music_player";

			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				song.setId(resultSet.getInt(1));
				song.setSongName(resultSet.getString(2));
				song.setSingerName(resultSet.getString(3));
				song.setMoveName(resultSet.getString(4));
				song.setDuration(resultSet.getDouble(5));
				System.out.println(song);
				System.out.println("-----------------------");
			}

			query = "delete from music_player " + "where id=?";

			preparedStatement = connection.prepareStatement(query);

			System.out.println("Enter song id which you want to remove");
			int id = scanner.nextInt();
			song.setId(id);
			preparedStatement.setInt(1, song.getId());

			result = preparedStatement.executeUpdate();

			if (result <= 0) {
				System.out.println("Given ID number is not Presnt");
				System.out.println();
			} else {
				System.out.println("Query ok " + result + "rwo(s) affected");
				System.out.println();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println("Enter valid Id to remove song");
			System.out.println();

		} finally {
			closeConnection();
		}

	}

	// Updating song
	public void updateSong() {
		System.out.println("Updating song....");
		openConnection();
		query = "update music_player " + "set songName=?, singerName=?,moveName=?,duration=?  " + "where id=?";
		try {
			preparedStatement = connection.prepareStatement(query);

			System.out.println("Enter song id which you want to update ");
			int id = scanner.nextInt();
			song.setId(id);
			System.out.println("Enter updated song nmae");
			scanner.nextLine();
			String sname = scanner.nextLine();

			song.setSongName(sname);
			System.out.println("Enter updated singer name");
			String singer = scanner.nextLine();
			song.setSingerName(singer);
			System.out.println("Enter updated move name");
			String move = scanner.nextLine();
			song.setMoveName(move);
			try {
				System.out.println("Enter updated duration");
				double duration = scanner.nextDouble();
				song.setDuration(duration);
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Enter valid Duration");
			}

			preparedStatement.setString(1, song.getSongName());
			preparedStatement.setString(2, song.getSingerName());
			preparedStatement.setString(3, song.getMoveName());
			preparedStatement.setDouble(4, song.getDuration());
			preparedStatement.setInt(5, song.getId());

			result = preparedStatement.executeUpdate();
			if (result <= 0) {
				System.out.println("Given ID number is not present");
				System.out.println();
			} else {
				System.out.println("Query ok " + result + "row(s) affected");
				System.out.println();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		catch (InputMismatchException e) {
			System.out.println("Enter valid Id ");
			System.out.println();
		} finally {
			closeConnection();
		}

	}

}
