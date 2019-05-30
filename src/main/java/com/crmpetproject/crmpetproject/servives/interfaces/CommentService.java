package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.Comment;
import com.crmpetproject.crmpetproject.models.User;

import java.util.List;

public interface CommentService extends CommonService<Comment>{

    List<Comment> getAllCommentsByClient(Client client);

    List<Comment> getAllCommentsByUser(User user);
}
