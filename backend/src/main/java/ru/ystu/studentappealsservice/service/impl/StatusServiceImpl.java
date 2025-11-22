package ru.ystu.studentappealsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ystu.studentappealsservice.dto.status.GetStatusesResponse;
import ru.ystu.studentappealsservice.exception.ServerLogicException;
import ru.ystu.studentappealsservice.exception.ServerLogicExceptionType;
import ru.ystu.studentappealsservice.mapper.StatusMapper;
import ru.ystu.studentappealsservice.model.Status;
import ru.ystu.studentappealsservice.repository.StatusRepository;
import ru.ystu.studentappealsservice.service.StatusService;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Override
    @Transactional(readOnly = true)
    public GetStatusesResponse getStatuses() {
        return statusMapper.toResponseDto(statusRepository.findAllByOrderByIdAsc());
    }

    @Override
    public Status getStatus(Integer id) {
        return statusRepository.findById(id).orElseThrow(() ->
                new ServerLogicException("Статус с id = %s - не найден".formatted(id), ServerLogicExceptionType.NOT_FOUND));
    }
}
