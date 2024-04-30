package com.xplained.main.models.objects.groups;

import com.xplained.main.dto.models.objects.groups.GroupRequestBody;
import com.xplained.main.models.objects.groups.objects.GroupObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editor/models/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{modelId}")
    public List<ObjectGroup> getGroups(@PathVariable Long modelId) {
        return groupService.getGroups(modelId);
    }

    @GetMapping("/objects/{modelId}")
    public List<GroupObject> getAllObjectsInGroup(@PathVariable Long modelId) {
        return groupService.getAllObjectsInGroup(modelId);
    }

    @PostMapping("/{modelId}")
    public ObjectGroup createGroup(@PathVariable Long modelId) {
        return groupService.createGroup(modelId);
    }

    @PutMapping("/{groupId}")
    public void updateGroup(@PathVariable Long groupId, @RequestBody GroupRequestBody requestBody) {
        groupService.updateGroup(groupId, requestBody);
    }

    @PutMapping("/{groupId}/{objectId}")
    public GroupObject addObjectToGroup(@PathVariable Long objectId, @PathVariable Long groupId) {
        return groupService.addObjectToGroup(objectId, groupId);
    }

    @PutMapping("/objects/{groupId}/{objectId}")
    public GroupObject changeObjectGroup(@PathVariable Long objectId, @PathVariable Long groupId) {
        return groupService.changeObjectGroup(objectId, groupId);
    }

    @DeleteMapping("/objects/{objectId}")
    public void removeObjectFromGroup(@PathVariable Long objectId) {
        groupService.removeObjectFromGroup(objectId);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
