package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.CommentAnswer;

public interface CommentAnswerService extends CommonService<CommentAnswer> {
	CommentAnswer addCommentAnswer(CommentAnswer commentAnswer);
}
