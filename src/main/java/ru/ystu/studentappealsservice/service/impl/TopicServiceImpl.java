package ru.ystu.studentappealsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ystu.studentappealsservice.dto.topic.CreateTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.EditTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.GetTopicsResponse;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;
import ru.ystu.studentappealsservice.exception.ServerLogicException;
import ru.ystu.studentappealsservice.exception.ServerLogicExceptionType;
import ru.ystu.studentappealsservice.mapper.TopicMapper;
import ru.ystu.studentappealsservice.model.Topic;
import ru.ystu.studentappealsservice.repository.TopicRepository;
import ru.ystu.studentappealsservice.service.TopicService;
import ru.ystu.studentappealsservice.util.DateUtils;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    @Transactional(readOnly = true)
    public GetTopicsResponse getTopics() {
        return topicMapper.toGetTopicsResponse(topicRepository.findAllByDeletedAtIsNullOrderByIdAsc());
    }

    @Override
    @Transactional
    public TopicDTO createTopic(CreateTopicDTO request) {
        checkUniqueTopic(request.getName());
        return topicMapper.toDto(topicRepository.save(topicMapper.toEntity(request)));
    }

    @Override
    @Transactional
    public TopicDTO editTopic(Integer topicId, EditTopicDTO request) {
        Topic topic = getTopic(topicId);
        topicMapper.updateEntity(topic, request);
        return topicMapper.toDto(topic);
    }

    @Override
    @Transactional
    public void deleteTopic(Integer topicId) {
        getTopic(topicId).setDeletedAt(DateUtils.truncatedNow());
    }

    @Override
    public Topic getTopic(Integer topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new ServerLogicException("Темы с id = %s - не существует".formatted(topicId),
                        ServerLogicExceptionType.NOT_FOUND));
    }

    private void checkUniqueTopic(String name) {
        if (topicRepository.existsByName(name)) {
            throw new ServerLogicException("Тема с name = %s - уже существует", ServerLogicExceptionType.ALREADY_EXISTS);
        }
    }
}
