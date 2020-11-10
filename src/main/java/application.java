import java.util.Scanner;

import data.UserDao;
import models.User;

public class application {
	public static void main(String[] args) {
		int id = 0;
		String username = "";
		String pass = "";
		UserDao userDao = new UserDao();
		int option = 0;
		do {
			System.out.println("<====================MENU====================>");
			System.out.println("1. Add new user.");
			System.out.println("2. Update an user.");
			System.out.println("3. Delte an user.");
			System.out.println("4. Show all users.");
			System.out.println("5. Exit.\n");
			System.out.print("Option #");

			Scanner scanner = new Scanner(System.in);
			option = scanner.nextInt();
			
			switch (option) {
			case 1:
				System.out.print("type the username: ");
				username = scanner.next();
				System.out.print("type the pass: ");
				pass = scanner.next();
				User newUser = new User(0, username, pass);
				userDao.addNewUser(newUser);
				break;
			case 2:
				System.out.print("type the user id: ");
				id = scanner.nextInt();
				System.out.print("type the username: ");
				username = scanner.next();
				System.out.print("type the pass: ");
				pass = scanner.next();
				User updateUser = new User(id, username, pass);
				userDao.updateUser(updateUser);
				break;
			case 3:
				System.out.print("type the user id: ");
				id = scanner.nextInt();
				userDao.deleteUser(id);
				break;
			case 4:
				userDao.getList().forEach(user -> {
					System.out.print("id: " + user.getId() + ", ");
					System.out.print("user: " + user.getUsername() + ", ");
					System.out.print("password: " + user.getPass());
					System.out.println("");
				});
				break;
			case 5:
				System.out.println("\nGoodbye <3");
				break;
			default:
				System.out.println("\nInvalid option");
				break;
			}
			scanner.nextLine();
			scanner.nextLine();
			System.out.println("");
		} while (option != 5);

	}
}