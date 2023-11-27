package com.std.sbb.Question;

import com.std.sbb.DataNotException;
import com.std.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList () {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
         Optional<Question> oq = this.questionRepository.findById(id);

        if (oq.isPresent() == false) throw new DataNotException("question not found");

         return oq.get();
    }
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question create(String subject, String content, SiteUser user) {
            Question q = new Question();
            q.setSubject(subject);
            q.setContent(content);
            q.setCreateDate(LocalDateTime.now());
            q.setSiteUser(user);
            this.questionRepository.save(q);
            return q;
    }
}
