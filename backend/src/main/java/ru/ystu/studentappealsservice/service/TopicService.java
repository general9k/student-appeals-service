package ru.ystu.studentappealsservice.service;

import ru.ystu.studentappealsservice.dto.topic.CreateTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.EditTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.GetTopicsResponse;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;
import ru.ystu.studentappealsservice.model.Topic;

public interface TopicService {
    GetTopicsResponse getTopics();

    TopicDTO createTopic(CreateTopicDTO request);

    TopicDTO editTopic(Integer topicId, EditTopicDTO request);

    void deleteTopic(Integer topicId);

    Topic getTopic(Integer topicId);
}
