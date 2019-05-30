package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.Comment;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.repository.interfaces.CommentDAO;
import com.crmpetproject.crmpetproject.servives.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends CommonServiceImpl<Comment> implements CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> getAllCommentsByClient(Client client) {
        return commentDAO.getAllByClient(client);
    }

    @Override
    public List<Comment> getAllCommentsByUser(User user) {
        return commentDAO.getAllByUser(user);
    }
}
