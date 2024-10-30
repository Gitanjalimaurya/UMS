package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.Stream;

import java.util.List;

public interface StreamService {
    List<Stream> getAllStreams();
    void saveStream(Stream stream);
    String deleteStream(String streamId);

    Stream getStreamByName(String streamName);

}
