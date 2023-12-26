package ivan.app.dao;

import ivan.app.model.User;

public interface UserDao {
    boolean save(User user);

    User getRandomUser();
}
