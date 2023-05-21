package com.example.librarybe.mappers;

import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.models.Publisher;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-21T20:14:21+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public PublisherDto toPublisherDto(Publisher publisher) {
        if ( publisher == null ) {
            return null;
        }

        PublisherDto publisherDto = new PublisherDto();

        publisherDto.setId( publisher.getId() );
        publisherDto.setName( publisher.getName() );
        publisherDto.setAddress( publisher.getAddress() );
        publisherDto.setPhone( publisher.getPhone() );

        return publisherDto;
    }

    @Override
    public Publisher toPublisher(PublisherDto publisherDto) {
        if ( publisherDto == null ) {
            return null;
        }

        Publisher publisher = new Publisher();

        publisher.setId( publisherDto.getId() );
        publisher.setName( publisherDto.getName() );
        publisher.setAddress( publisherDto.getAddress() );
        publisher.setPhone( publisherDto.getPhone() );

        return publisher;
    }
}
