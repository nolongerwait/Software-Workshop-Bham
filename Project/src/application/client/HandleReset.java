package application.client;

import application.dao.UserDAO;
import application.utils.Toast;
import javafx.stage.Stage;

public class HandleReset {
	public HandleReset(Stage stage, String Email, String OldPassword, String NewPassword) throws Exception {
		Toast toast = new Toast(stage);
		UserDAO userDAO = new UserDAO();
		if(userDAO.findByName(Email) == null) {
			Toast.Level level = Toast.Level.values()[2];
			toast.show(level, 1000, "There no user registed by this email");
		}
		else {
            if(userDAO.resetPassword(Email, OldPassword, NewPassword)) {
                Toast.Level level = Toast.Level.values()[0];
			toast.show(level, 1000, "Reset success!");
			Thread.sleep(1000);
			stage.close();
            }
			else {
                Toast.Level level = Toast.Level.values()[2];
			    toast.show(level, 1000, "The Old Password is wrong!");
            }
		}
	}
}
