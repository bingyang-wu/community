package com.milo.community.service;

import com.milo.community.dto.QuestionDTO;
import com.milo.community.mapper.QuestionMapper;
import com.milo.community.mapper.UserMapper;
import com.milo.community.model.Question;
import com.milo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionSevice {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.list();
        for (Question question : questionList) {
            QuestionDTO dto = new QuestionDTO();
            BeanUtils.copyProperties(question, dto);
            User user = userMapper.findById(question.getCreator());
            dto.setUser(user);
            questionDTOList.add(dto);
        }

        return questionDTOList;
    }
}
