package com.std.sbb.Answer;

import com.std.sbb.Question.Question;
import com.std.sbb.user.SiteUser;
import com.std.sbb.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public Answer create(Question q, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(q);
        answer.setCreateDate(LocalDateTime.now());
        answer.setSiteUser(author);
        this.answerRepository.save(answer);

        return answer;
    }

}
