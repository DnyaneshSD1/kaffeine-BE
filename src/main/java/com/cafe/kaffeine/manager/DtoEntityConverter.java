package com.cafe.kaffeine.manager;

import org.springframework.stereotype.Component;

@Component
public interface DtoEntityConverter<T, E> {
    E convertDtoToEntity(T dto);
    T convertEntityToDto(E entity);
}

