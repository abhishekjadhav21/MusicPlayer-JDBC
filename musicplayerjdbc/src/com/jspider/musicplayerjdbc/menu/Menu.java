


package com.jspider.musicplayerjdbc.menu;

import java.util.Scanner;


import com.jspider.musicplayerjdbc.operation.*;

public class Menu {
	Scanner sc = new Scanner(System.in);
	static boolean loop = true;
	SongOperation songOperation = new SongOperation();

	public static void main(String[] args) {
		Menu music = new Menu();

		while (loop) {
			music.multiPlayer();

		}

	}

	public void multiPlayer() {
		System.out.println("select Option :\n1.play song \n2.Add or remove song \n3.Update Song \n4.Exit");
		System.out.println("-----------------------");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			System.out.println(
					"Choose option to play song :\n1.Choose to play \n2.play all song  \n4.go back");
			int choose2 = sc.nextInt();
			switch (choose2) {
			case 1:
				songOperation.choseToPlaySong();
				break;

			case 2:
				songOperation.playAllSong();
				break;
			case 3:
				System.out.println("Going back...\n\n");
				break;
			default:
				System.out.println("Enter valid choice");
				break;
			}
			break;

		case 2:
			System.out.println("choose option to add song :\n1.addSong \n2.Remove Song \n3.Go back ");
			int choose3 = sc.nextInt();
			switch (choose3) {
			case 1:
				songOperation.addSong();
				break;
			case 2:
				songOperation.removeSong();
				break;
			case 3:
				System.out.println("Going Back...");
			}
			break;
		case 3:
			System.out.println(
					"choose option to Update song :\n1.Update Song Name\\Singer Name\\\\Movie Name\\\\Song Duration\n2.Go Back\\n");
			int choose4 = sc.nextInt();
			switch (choose4) {
			case 1:
				songOperation.updateSong();
				break;
			case 2:
				System.out.println("Going back.....");
				break;
			default:
				System.out.println("Enter valid Input.....");
				break;

			}
			break;
		case 4:
			System.out.println("Wait.....Existing....");
			loop = false;

		}

	}

}
