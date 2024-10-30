package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.service.interfaces.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StreamController {

    private final StreamService streamService;

    @Autowired
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    // View all streams
    @GetMapping("/view-stream")
    public String viewAllStreams(Model model) {
        model.addAttribute("streams", streamService.getAllStreams());
        return "view-stream";
    }

    // Add stream page
    @GetMapping("/add-stream")
    public String addStreamPage(Model model) {
        model.addAttribute("stream", new Stream()); // Empty Stream object for the form
        return "add-stream";
    }

    // Handle adding stream
    @PostMapping("/add-stream")
    public String addStream(@ModelAttribute Stream stream, Model model) {
        streamService.saveStream(stream);
        model.addAttribute("message", "Stream added successfully");
        return "add-stream";
    }

    // Handle stream deletion with POST
    @PostMapping("/delete-stream/{streamId}")
    public String deleteStream(@PathVariable String streamId, Model model) {
        String responseMessage = streamService.deleteStream(streamId);
        model.addAttribute("message", responseMessage);

        model.addAttribute("streams", streamService.getAllStreams());
        return "view-stream"; // Redirect after deletion
    }
}
