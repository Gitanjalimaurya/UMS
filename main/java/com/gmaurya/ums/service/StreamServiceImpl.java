package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.repository.StreamRepository;
import com.gmaurya.ums.repository.CourseRepository; // Ensure this is imported
import com.gmaurya.ums.service.interfaces.StreamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;
    private final CourseRepository courseRepository; // Inject the CourseRepository

    public StreamServiceImpl(StreamRepository streamRepository, CourseRepository courseRepository) {
        this.streamRepository = streamRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    @Override
    public void saveStream(Stream stream) {
        streamRepository.save(stream);
    }

    @Override
    public String deleteStream(String streamId) {
        // Check if the stream is referenced in the course table
        if (courseRepository.existsByStreamStreamId(streamId)) {
            return "Warning: This stream is still referenced by one or more courses. Cannot delete.";
        }

        // Proceed with deletion if not referenced
        streamRepository.deleteById(streamId);
        return "Stream deleted successfully";
    }

    @Override
    public Stream getStreamByName(String streamName) {
        Optional<Stream> streamOptional = streamRepository.findByStreamName(streamName);
        return streamOptional.orElse(null);
    }
}
