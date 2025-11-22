package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.topic.CreateTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.EditTopicDTO;
import ru.ystu.studentappealsservice.dto.topic.GetTopicsResponse;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;

public interface TopicControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.GET, value = "/topics")
    ResponseEntity<GetTopicsResponse> getTopics();

    @RequestMapping(method = RequestMethod.POST, value = "/topic")
    ResponseEntity<TopicDTO> createTopic(@RequestBody CreateTopicDTO request);

    @RequestMapping(method = RequestMethod.PUT, value = "/topic/{id}")
    ResponseEntity<TopicDTO> editTopic(@PathVariable("id") Integer topicId, @RequestBody EditTopicDTO request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/topic/{id}")
    ResponseEntity<Void> deleteTopic(@PathVariable("id") Integer topicId);
}
