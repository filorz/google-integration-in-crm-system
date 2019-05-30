package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.Comment;
import com.crmpetproject.crmpetproject.models.User;

import java.util.List;

public interface CommentDAO extends CommonGenericRepository<Comment> {
    List<Comment> getAllByClient(Client client);
    List<Comment> getAllByUser(User user);
}
