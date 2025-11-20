package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.TopicControllerApi;
import ru.ystu.studentappealsservice.dto.topic.CreateTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.EditTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.GetTopicsResponse;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;
import ru.ystu.studentappealsservice.service.TopicService;

@RestController
@RequiredArgsConstructor
public class TopicController implements TopicControllerApi {

    private final TopicService topicService;

    @Override
    public ResponseEntity<GetTopicsResponse> getTopics() {
        return ResponseEntity.ok(topicService.getTopics());
    }

    @Override
    public ResponseEntity<TopicDTO> createTopic(CreateTopicDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(request));

    }

    @Override
    public ResponseEntity<TopicDTO> editTopic(Integer topicId, EditTopicDTO request) {
        return ResponseEntity.ok(topicService.editTopic(topicId, request));
    }

    @Override
    public ResponseEntity<Void> deleteTopic(Integer topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}