package com.example.ez2onservertest.domain.comment;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int writeComment(Map<String, String> commentMap) {
        return commentMapper.insertComment(commentMap);
    }

    @Override
    public List<CommentDTO> getComments(int musicNumber, int offset) {
        return commentMapper.selectComments(musicNumber, offset);
    }

    @Override
    public int getCountComments(int musicNumber) {
        return commentMapper.countComments(musicNumber);
    }

    @Override
    public int getCommentLevels(int musicNumber) {
        return commentMapper.selectCommentsLevel(musicNumber);
    }

    @Override
    public List<CommentDTO> insertCommentDTOCount(List<CommentDTO> comments, int number) {
        int commentCount = getCountComments(number);
        comments.stream().forEach( (comment) -> {
            comment.setComment_cnt(commentCount);
        });
        return comments;
    }

    @Override
    public void updateLevels(int musicNumber) {
        double score = getScoreTally(musicNumber);
        commentMapper.updateCommentLevel(musicNumber, score);
    }

    @Override
    public double getScoreTally(int musicNumber) {
        int totalScore = getCommentLevels(musicNumber);
        int scoreCount = getCountComments(musicNumber);

        double doubleResult = (double) totalScore/scoreCount;

        DecimalFormat form = new DecimalFormat("#.##");
        return Double.parseDouble(form.format(doubleResult));
    }
}
