package com.xplained.main.models.objects.groups;

import com.xplained.main.dto.models.objects.groups.GroupRequestBody;
import com.xplained.main.models.objects.EditorObject;
import com.xplained.main.models.objects.EditorObjectRepository;
import com.xplained.main.models.objects.constraints.ConstrainsObjectRepository;
import com.xplained.main.models.objects.constraints.ConstraintsObject;
import com.xplained.main.models.objects.groups.objects.GroupObject;
import com.xplained.main.models.objects.groups.objects.GroupObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final EditorObjectRepository editorObjectRepository;
    private final GroupRepository groupRepository;
    private final GroupObjectRepository groupObjectRepository;
    private final ConstrainsObjectRepository constrainsObjectRepository;


    public List<ObjectGroup> getGroups(Long modelId) {
        return groupRepository.findAllByModelIdOrderByCreatedAtDesc(modelId);
    }

    public List<GroupObject> getAllObjectsInGroup(Long modelId) {
        List<GroupObject> groupObjectList = groupObjectRepository.findAllByModelId(modelId);

        groupObjectList.forEach(groupObject -> {
            Optional<EditorObject> object = editorObjectRepository.findById(groupObject.getObjectId());

            object.ifPresent((value) -> {
                groupObject.setObject(value);

                List<ConstraintsObject> constraints = constrainsObjectRepository.findAllByObjectId(value.getId());

                value.setConstraints(constraints.stream().map(ConstraintsObject::getConstraintId).toList());
            });
        });

        return groupObjectList;
    }

    public ObjectGroup createGroup(Long modelId) {

        ObjectGroup group = ObjectGroup.builder()
                .modelId(modelId)
                .name("Group " + groupRepository.countByModelId(modelId))
                .build();

        return groupRepository.saveAndFlush(group);
    }

    public GroupObject addObjectToGroup(Long objectId, Long groupId) {
        EditorObject object = editorObjectRepository.findById(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));
        ObjectGroup group = groupRepository.findById(groupId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        object.setIsGroupObject(true);
        object.setGroupId(group.getId());

        GroupObject groupObject = GroupObject.builder()
                .modelId(group.getModelId())
                .groupId(group.getId())
                .objectId(object.getId())
                .object(object)
                .build();

        return groupObjectRepository.save(groupObject);
    }

    public void removeObjectFromGroup(Long objectId) {

        GroupObject groupObject = groupObjectRepository.findByObjectId(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        if (!groupRepository.existsById(groupObject.getGroupId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");

        EditorObject object = editorObjectRepository.findById(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        object.setIsGroupObject(false);
        object.setGroupId(null);
        groupObjectRepository.delete(groupObject);
    }

    public GroupObject changeObjectGroup(Long objectId, Long groupId) {
        GroupObject groupObject1 = groupObjectRepository.findByObjectId(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        EditorObject object = editorObjectRepository.findById(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));
        ObjectGroup group = groupRepository.findById(groupId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        groupObjectRepository.delete(groupObject1);

        object.setIsGroupObject(true);
        object.setGroupId(group.getId());


        return groupObjectRepository.save(GroupObject.builder()
                .modelId(group.getModelId())
                .groupId(group.getId())
                .objectId(object.getId())
                .object(object)
                .build());
    }

    public void updateGroup(Long groupId, GroupRequestBody requestBody) {
        ObjectGroup group = groupRepository.findById(groupId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        if (requestBody.getName() != null) group.setName(requestBody.getName());

        groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        ObjectGroup group = groupRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found"));

        List<GroupObject> groupObjects = groupObjectRepository.findAllByGroupId(group.getId());

        //            editorObjectRepository.deleteById(groupObject.getObjectId());
        groupObjectRepository.deleteAll(groupObjects);

        groupRepository.delete(group);
    }
}
