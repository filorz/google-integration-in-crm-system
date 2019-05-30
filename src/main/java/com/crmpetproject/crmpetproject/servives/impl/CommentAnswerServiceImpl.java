package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.CommentAnswer;
import com.crmpetproject.crmpetproject.repository.interfaces.CommentAnswerRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.CommentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerServiceImpl extends CommonServiceImpl<CommentAnswer> implements CommentAnswerService {
	private final CommentAnswerRepository commentAnswerRepository;

	@Autowired
	public CommentAnswerServiceImpl(CommentAnswerRepository commentAnswerRepository) {
		this.commentAnswerRepository = commentAnswerRepository;
	}

	@Override
    public CommentAnswer addCommentAnswer(CommentAnswer commentAnswer){
	    return commentAnswerRepository.saveAndFlush(commentAnswer);
    }
}
