package ru.ystu.studentappealsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.ystu.studentappealsservice.dto.topic.CreateTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.EditTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.GetTopicsResponse;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;
import ru.ystu.studentappealsservice.model.Topic;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    Topic toEntity(CreateTopicDTO request);

    TopicDTO toDto(Topic topic);

    void updateEntity(@MappingTarget Topic topic, EditTopicDTO request);

    List<TopicDTO> toDto(List<Topic> topics);

    default GetTopicsResponse toGetTopicsResponse(List<Topic> topics) {
        return new GetTopicsResponse(toDto(topics));
    }
}
