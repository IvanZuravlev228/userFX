package ivan.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ivan.app.dao.UserDao;
import ivan.app.model.User;
import ivan.app.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
    private final Random random = new Random();

    @Override
    public boolean save(User user) {
        String query = "INSERT INTO users (id, login, password) VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement createManufactureStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createManufactureStatement.setLong(1, user.getId());
            createManufactureStatement.setString(2, user.getLogin());
            createManufactureStatement.setString(3, user.getPassword());
            createManufactureStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Can't insert into DB " + user);
            return false;
        }
    }

    @Override
    public User getRandomUser() {
        long randomUserId = getRandomUserId();

        String query = "SELECT * FROM users WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement getOneStatement = connection.prepareStatement(query)) {
            getOneStatement.setLong(1, randomUserId);
            ResultSet resultSet = getOneStatement.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get manufacturer by id: " + randomUserId, e);
        }
        return null;
    }

    private List<Long> getAllUserIds() {
        List<Long> userIds = new ArrayList<>();

        String query = "SELECT id FROM users;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long userId = resultSet.getLong("id");
                userIds.add(userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user ids from the database", e);
        }
        return userIds;
    }

    private Long getRandomUserId() {
        List<Long> allUserIds = getAllUserIds();
        long id = random.nextLong(allUserIds.size());
        return allUserIds.get((int) id);
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        Long idFromDB = resultSet.getObject("id", Long.class);
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        return new User(idFromDB, login, password);
    }
}
