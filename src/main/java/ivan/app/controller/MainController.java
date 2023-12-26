package ivan.app.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ivan.app.dao.UserDao;
import ivan.app.dao.impl.UserDaoImpl;
import ivan.app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController {
    private final UserDao userDao = new UserDaoImpl();

    @FXML
    private TextField id;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Text errorText;

    @FXML
    private Text outputId;

    @FXML
    private Text outputLogin;

    @FXML
    private Text outputPassword;


    @FXML
    public void registerUser(ActionEvent event) {
        if (!isValidPassword(password.getText())) {
            errorText.setText("Invalid password");
            return;
        }
        User user = new User(Long.valueOf(id.getText()), login.getText(), password.getText());
        if (userDao.save(user)) {
            id.clear();
            login.clear();
            password.clear();
            errorText.setText("");
        } else {
            errorText.setText("A user with the same ID already exists");
        }
    }

    @FXML
    void getRandomUser(ActionEvent event) {
        User randomUser = userDao.getRandomUser();
        outputId.setText(randomUser.getId().toString());
        outputLogin.setText(randomUser.getLogin());
        outputPassword.setText(randomUser.getPassword());
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[!@#$%^&*()-_+=<>?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
