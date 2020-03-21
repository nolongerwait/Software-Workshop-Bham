package application.client;

import application.dao.UserDAO;
import application.model.User;
import application.utils.Toast;
import javafx.stage.Stage;

public class HandleRegister {
	public HandleRegister(Stage stage, User user) throws Exception {
		Toast toast = new Toast(stage);
		UserDAO userDAO = new UserDAO();
		if(userDAO.findByName(user.getNickname()) != null) {
			Toast.Level level = Toast.Level.values()[2];
			toast.show(level, 1000, "UserID has been registered!");
		}
		else if(userDAO.insert(user) > 0) {			
			Toast.Level level = Toast.Level.values()[0];
			toast.show(level, 1000, "Registration success!");
			Thread.sleep(1000);
			stage.close();
		}else {
			Toast.Level level = Toast.Level.values()[2];
			toast.show(level, 1000, "Registration failed!");
		}
	}
}
