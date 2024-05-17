package com.xplained.main.courses.resources.videos;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.courses.resources.videos.VideoRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final AuthService authService;


    public List<Video> getVideos() {
        return videoRepository.findAllByUserId(authService.getCurrentUser().getId());
    }

    public Video getVideo(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video not found"));
    }

    public Video uploadVideo(VideoRequestBody requestBody) {
        Video video = Video.builder()
                .userId(authService.getCurrentUser().getId())
                .name(requestBody.getName())
                .thumbnail(requestBody.getThumbnail())
                .build();

        return videoRepository.saveAndFlush(video);
    }

    public void deleteVideo(Long id) {
        Video video = videoRepository.findByIdAndUserId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "video not found"));

        videoRepository.delete(video);
    }
}
