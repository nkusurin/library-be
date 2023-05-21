package com.example.librarybe.mappers;

import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.models.Publisher;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDto toPublisherDto(Publisher publisher);
    Publisher toPublisher(PublisherDto publisherDto);
}