package com.xplained.main.courses;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.resources.videos.VideoRepository;
import com.xplained.main.dto.courses.*;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.user.bio.UserBio;
import com.xplained.main.user.bio.UserBioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final AuthService authService;
    private final CourseRepository courseRepository;
    private final VideoRepository videoRepository;
    private final UserBioRepository userBioRepository;


    public List<CourseResponse> getAllCourses(Integer page) {
        Pageable pageable = PageRequest.of(page, 15);

        return courseRepository.findAllByUserId(authService.getCurrentUser().getId(), pageable);
    }

    public List<CourseSearchResponse> getRecommendedCourses(Integer page) {

        Pageable pageable = PageRequest.of(page, 5);

        return courseRepository.findAllRecommendedCourses(pageable);
    }

    public List<CourseSearchResponse> searchCourses(String title, Integer page) {

        Pageable pageable = PageRequest.of(page, 15);

        if (title == null || title.isEmpty()) return courseRepository.searchCourseByEmptyTitle(pageable);
        else return courseRepository.searchCourseByTitle(title.toLowerCase(), pageable);
    }

    public CourseDetailsResponse getCourseDetails(Long id) {

        UserDTO user = authService.getCurrentUser();

        Course course = courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));

        CourseDetailsResponse response = CourseDetailsResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .heading(course.getHeading())
                .shortDescription(course.getShortDescription())
                .description(course.getDescription())
                .image(course.getImage())
                .video(course.getVideo())
                .isPrivate(course.getIsPrivate())
                .isPublished(course.getIsPublished())
                .isActive(course.getIsActive())
                .numberOfLessons(course.getNumberOfLessons())
                .numberOfModels(course.getNumberOfModels())
                .createdAt(course.getCreatedAt())
                .build();

        userBioRepository.findByUserId(user.getId()).ifPresent(value -> {
            response.setUser(
                    CourseDetailsResponseUser.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .heading(value.getHeading())
                    .image(value.getImage())
                    .build()
            );
        });

        return response;
    }

    public Course createCourse() {
        UserDTO user = authService.getCurrentUser();

        Course course = Course.builder()
                .title("Untitled " + courseRepository.countByUserId(user.getId()))
                .userId(user.getId())
                .isPrivate(false)
                .isPublished(false)
                .isActive(false)
                .build();

        return courseRepository.saveAndFlush(course);
    }

    public void updateCourse(Long id, CourseRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        System.out.println(requestBody);

        Course course = courseRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));

        if (requestBody.getTitle() != null) course.setTitle(requestBody.getTitle());
        if (requestBody.getShortDescription() != null) course.setShortDescription(requestBody.getShortDescription());
        if (requestBody.getDescription() != null) course.setDescription(requestBody.getDescription());
        if (requestBody.getRequirements() != null) course.setRequirements(requestBody.getRequirements());
        if (requestBody.getLearners() != null) course.setLearners(requestBody.getLearners());
        if (requestBody.getIsPrivate() != null) course.setIsPrivate(requestBody.getIsPrivate());

        // Add a condition to find the within our video database
        if (requestBody.getVideo() != null) {
            course.setVideo(requestBody.getVideo());
        }

        if (requestBody.getImage() != null) course.setImage(requestBody.getImage());

        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        UserDTO user = authService.getCurrentUser();

        Course course = courseRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));

        courseRepository.delete(course);
    }
}

