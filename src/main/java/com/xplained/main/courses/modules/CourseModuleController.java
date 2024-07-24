package com.xplained.main.courses.modules;

import com.xplained.main.dto.courses.modules.CourseModuleRequestBody;
import com.xplained.main.dto.courses.modules.CourseModuleResponse;
import com.xplained.main.dto.courses.modules.IndexRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/modules")
@RequiredArgsConstructor
public class CourseModuleController {
    private final CourseModuleService courseModuleService;


    @GetMapping("/{courseId}")
    public List<CourseModuleResponse> getAllModules(@PathVariable Long courseId) {
        return courseModuleService.getAllModules(courseId);
    }

    @PostMapping("/{courseId}")
    public CourseModuleResponse createModule(@PathVariable Long courseId) {
        return courseModuleService.createModule(courseId);
    }

    @PutMapping("/{id}")
    public void updateModule(@PathVariable Long id, @RequestBody CourseModuleRequestBody requestBody) {
        courseModuleService.updateModule(id, requestBody);
    }

    @PutMapping("/index")
    public void updateIndex(@RequestBody List<IndexRequestBody> requestBody) {
        courseModuleService.updateIndex(requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        courseModuleService.deleteModule(id);
    }
}
