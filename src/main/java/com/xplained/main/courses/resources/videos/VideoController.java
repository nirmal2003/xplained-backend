package com.xplained.main.courses.resources.videos;

import com.xplained.main.dto.courses.resources.videos.VideoRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;


    @GetMapping
    public List<Video> getVideos() {
        return videoService.getVideos();
    }

    @GetMapping("/{id}")
    public Video getVideo(@PathVariable Long id) {
        return videoService.getVideo(id);
    }

    @PostMapping
    public Video uploadVideo(@RequestBody VideoRequestBody requestBody) {
        return videoService.uploadVideo(requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }
}
