package com.example.quizwebengine.service;

import com.example.quizwebengine.model.quiz.Quiz;
import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.repository.QuizRepository;
import com.example.quizwebengine.repository.UserRepository;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    QuizRepository quizRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    QuizService service;

    Quiz quiz;
    User user;

    @BeforeEach
    void setUp() {

        quiz = new Quiz();
        user = new User();
        user.setId(1L);
        quiz.setId(3L);

    }

    @Test
    void createQuiz() throws Exception {

        when(userRepository.findUserById(1L)).thenReturn(java.util.Optional.of(user));
        when(quizRepository.save(quiz)).thenReturn(quiz);

        Long quizResponseId =  service.createQuiz(quiz, user.getId());

        assertAll(
                () -> verify(userRepository).findUserById(1L),
                () -> verify(quizRepository).save(quiz),
                () -> assertEquals(quizResponseId, quiz.getId())
        );

    }

    @Test
    void getDataAboutQuiz() throws Exception {
        when(quizRepository.findById(3L)).thenReturn(java.util.Optional.of(quiz));

        service.getDataAboutQuiz(quiz.getId());

        verify(quizRepository).findById(3L);

    }

    @Test
    void updateQuizData() {
    }

    @Test
    void deleteQuizData() {
    }

    @Test
    void getListOfQuizzes() {
    }
}