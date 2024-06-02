package service;

import models.Like;
import dao.LikesDAO;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class LikeService {
    LikesDAO likesDAO = new LikesDAO();

    public void update(Like like) throws SQLException {
        likesDAO.update(like);
    }

    public void insert(Like like) throws SQLException {
        likesDAO.insert(like);
    }

    public void delete(Like like) throws SQLException {
        likesDAO.delete(like);
    }

    public ArrayList<Like> selectAll() throws SQLException {
        return likesDAO.select();
    }

    public ArrayList<User> selectByLike(int user_id) throws SQLException {
        return likesDAO.selectByLike(user_id);
    }

    public int  selectIdByUsers(int who,int whom) throws SQLException {
        return likesDAO.selectIdByUsers(who,whom);
    }
}