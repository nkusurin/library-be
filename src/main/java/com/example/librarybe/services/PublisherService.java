package com.example.librarybe.services;

import com.example.librarybe.dtos.PublisherDto;
import com.example.librarybe.mappers.PublisherMapper;
import com.example.librarybe.models.Book;
import com.example.librarybe.models.Publisher;
import com.example.librarybe.repositories.PublisherRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;

    }

    public List<PublisherDto> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream()
                .map(publisherMapper::toPublisherDto)
                .collect(Collectors.toList());
    }

    public PublisherDto createPublisher(PublisherDto publisherDto) {
        Publisher publisher = publisherMapper.toPublisher(publisherDto);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return publisherMapper.toPublisherDto(savedPublisher);
    }

    public List<PublisherDto> searchPublishers(String query) {
        query = query.toLowerCase();

        List<Publisher> matchingPublishers = new ArrayList<>();

        List<Publisher> allPublishers = publisherRepository.findAll();
        for(Publisher p: allPublishers){
            if(p.getName().toLowerCase().contains(query) || p.getAddress().toLowerCase().contains(query) || p.getPhone().toLowerCase().contains(query)){
                matchingPublishers.add(p);
            }
        }

        return matchingPublishers.stream()
                .map(publisherMapper::toPublisherDto)
                .collect(Collectors.toList());
    }

    public Publisher getPublisherById(Long id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        return optionalPublisher.orElse(null);
    }

    public void deletePublisherById(Long id) {
        publisherRepository.deleteById(id);
    }

    public PublisherDto updatePublisher(Long id, PublisherDto updatedPublisherDto) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            Publisher existingPublisher = optionalPublisher.get();

            // Update the fields of the existing publisher with the updated values
            existingPublisher.setName(updatedPublisherDto.getName());
            existingPublisher.setAddress(updatedPublisherDto.getAddress());
            existingPublisher.setPhone(updatedPublisherDto.getPhone());

            Publisher updatedPublisher = publisherRepository.save(existingPublisher);
            return publisherMapper.toPublisherDto(updatedPublisher);
        } else {
            throw new IllegalArgumentException("Publisher not found with ID: " + id);
        }
    }

}
