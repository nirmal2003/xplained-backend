package com.xplained.main.models.objects;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.objects.ConstraintRequestBody;
import com.xplained.main.dto.models.objects.EditorObjectRequestBody;
import com.xplained.main.dto.models.objects.EditorObjectResponse;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.ModelRepository;
import com.xplained.main.models.objects.constraints.ConstrainsObjectRepository;
import com.xplained.main.models.objects.constraints.ConstraintsObject;
import com.xplained.main.models.objects.groups.objects.GroupObject;
import com.xplained.main.models.objects.groups.objects.GroupObjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class EditorObjectService {
    private final AuthService authService;
    private final EditorObjectRepository editorObjectRepository;
    private final ModelRepository modelRepository;
    private final ConstrainsObjectRepository constrainsObjectRepository;
    private final GroupObjectRepository groupObjectRepository;


    public List<EditorObjectResponse> getAllObjects(Long modelId) {

        List<EditorObjectResponse> objects = editorObjectRepository.findAllByModelIdOrderByCreatedAtDesc(modelId);

//        List<EditorObjectResponse> objects = new ArrayList<>();

        for (EditorObjectResponse object : objects) {

            List<ConstraintsObject> constraints = constrainsObjectRepository.findAllByObjectId(object.getId());

            object.setConstraints(constraints.stream().map(ConstraintsObject::getConstraintId).toList());
        }

        return objects;
    }

    public EditorObjectResponse getObjectDetails(Long id) {
        EditorObject object = editorObjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        return EditorObjectResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .shape(object.getShape())
                .fill(object.getFill())
                .width(object.getWidth())
                .height(object.getHeight())
                .yAxis(object.getYAxis())
                .xAxis(object.getXAxis())
                .angle(object.getAngle())
                .radius(object.getRadius())
                .isFrozen(object.getIsFrozen())

                .velocityX(object.getVelocityX())
                .velocityY(object.getVelocityY())
                .friction(object.getFriction())
                .frictionAir(object.getFrictionAir())
                .restitution(object.getRestitution())
                .mass(object.getMass())

                .length(object.getLength())
                .stiffness(object.getStiffness())
                .damping(object.getDamping())

                .segmentWidth(object.getSegmentWidth())
                .segmentHeight(object.getSegmentHeight())
                .segmentCount(object.getSegmentCount())
                .stringStartY(object.getStringStartY())
                .stringSpacing(object.getStringSpacing())
                .isStartStatic(object.getIsStartStatic())
                .isEndStatic(object.getIsEndStatic())

                .bodyA(object.getBodyA())
                .bodyB(object.getBodyB())
                .pointAX(object.getPointAX())
                .pointAY(object.getPointAY())
                .pointBX(object.getPointBX())
                .pointBY(object.getPointBY())

                .build();
    }

    public EditorObjectResponse createObject(Long modelId, EditorObjectRequestBody requestBody) {
        if (!modelRepository.existsById(modelId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found");

        UserDTO user = authService.getCurrentUser();


        EditorObject editorObject = editorObjectRepository.saveAndFlush(EditorObject.builder()
                .name("Object " + editorObjectRepository.countByModelId(modelId).intValue() + 1)
                .userId(user.getId())
                .modelId(modelId)
                .fill("#212121")
                .yAxis(ThreadLocalRandom.current().nextDouble(0, 100))
                .xAxis(ThreadLocalRandom.current().nextDouble(0, 500))
                .width(ThreadLocalRandom.current().nextDouble(50, 100))
                .height(ThreadLocalRandom.current().nextDouble(50, 100))
                .angle(0.0)
                .shape(requestBody.getShape())
                .radius(ThreadLocalRandom.current().nextDouble(50, 60))
                .isFrozen(true)
                .velocityX(0.0)
                .velocityY(0.0)
                .friction(0.0)
                .frictionStatic(0.0)
                .frictionAir(0.0)
                .restitution(0.0)
                .mass(1.0)
                .isLineObject(false)
                .lineDefault(false)
                .color("#212121")
                .strokeColor("#ffffff")
                .lineWidth(2f)
                .build());

        if (requestBody.getShape() == 3) {
            editorObject.setPointAX(0.0);
            editorObject.setPointAY(0.0);

            editorObject.setPointBX(0.0);
            editorObject.setPointBY(0.0);
            editorObject.setStiffness(1.0);
            editorObject.setDamping(0.0);
            editorObject.setLength(300.0);

            EditorObject bodyA = editorObjectRepository.saveAndFlush(EditorObject.builder()
                    .name(requestBody.getName())
                    .userId(user.getId())
                    .modelId(modelId)
                    .fill(requestBody.getFill())
                    .yAxis(ThreadLocalRandom.current().nextDouble(0, 100))
                    .xAxis(ThreadLocalRandom.current().nextDouble(0, 500))
                    .angle(0.0)
                    .shape(2)
                    .radius(10.0)
                    .isFrozen(true)
                    .velocityX(0.0)
                    .velocityY(0.0)
                    .friction(0.0)
                    .frictionStatic(0.0)
                    .frictionAir(0.0)
                    .restitution(0.0)
                    .mass(1.0)
                    .isLineObject(true)
                    .lineId(editorObject.getId())
                    .lineDefault(true)
                    .color("#ffffff")
                    .strokeColor("#ffffff")
                    .lineWidth(0f)
                    .build());

            EditorObject bodyB = editorObjectRepository.saveAndFlush(EditorObject.builder()
                    .name(requestBody.getName())
                    .userId(user.getId())
                    .modelId(modelId)
                    .fill(requestBody.getFill())
                    .yAxis(ThreadLocalRandom.current().nextDouble(100, 200))
                    .xAxis(ThreadLocalRandom.current().nextDouble(100, 300))
                    .angle(0.0)
                    .shape(2)
                    .radius(10.0)
                    .isFrozen(true)
                    .velocityX(0.0)
                    .velocityY(0.0)
                    .friction(0.0)
                    .frictionStatic(0.0)
                    .frictionAir(0.0)
                    .restitution(0.0)
                    .mass(1.0)
                    .isLineObject(true)
                    .lineId(editorObject.getId())
                    .lineDefault(true)
                    .color("#ffffff")
                    .strokeColor("#ffffff")
                    .lineWidth(0f)
                    .build());

            editorObject.setBodyA(bodyA.getId());
            editorObject.setBodyB(bodyB.getId());
            editorObject.setBodyADefault(bodyA.getId());
            editorObject.setBodyBDefault(bodyB.getId());
        }

        if (requestBody.getShape() == 4) {
            editorObject.setStringStartX(ThreadLocalRandom.current().nextDouble(20, 300));
            editorObject.setStringStartY(requestBody.getStringStartY());
            editorObject.setSegmentWidth(requestBody.getSegmentWidth());
            editorObject.setSegmentHeight(requestBody.getSegmentHeight());
            editorObject.setSegmentCount(requestBody.getSegmentCount());
            editorObject.setStringSpacing(requestBody.getStringSpacing());
            editorObject.setIsStartStatic(true);
            editorObject.setIsEndStatic(false);
        }

        EditorObject object = editorObjectRepository.saveAndFlush(editorObject);

        EditorObjectResponse response = EditorObjectResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .fill(object.getFill())
                .yAxis(object.getYAxis())
                .xAxis(object.getXAxis())
                .width(object.getWidth())
                .height(object.getHeight())
                .angle(object.getAngle())
                .shape(object.getShape())
                .radius(object.getRadius())
                .isFrozen(object.getIsFrozen())
                .velocityX(object.getVelocityX())
                .velocityY(object.getVelocityY())
                .friction(object.getFriction())
                .frictionAir(object.getFrictionAir())
                .restitution(object.getRestitution())
                .density(object.getDensity())
                .mass(object.getMass())
                .inertia(object.getInertia())

                .color(object.getColor())
                .strokeColor(object.getStrokeColor())
                .lineWidth(object.getLineWidth())

                .bodyA(object.getBodyA())
                .bodyB(object.getBodyB())
                .pointAX(object.getPointAX())
                .pointAY(object.getPointAY())
                .pointBX(object.getPointBX())
                .pointBY(object.getPointBY())
                .stiffness(object.getStiffness())
                .damping(object.getStiffness())
                .length(object.getLength())

                .segmentWidth(object.getSegmentWidth())
                .segmentHeight(object.getSegmentHeight())
                .segmentCount(object.getSegmentCount())
                .stringStartY(object.getStringStartY())
                .stringStartX(object.getStringStartX())
                .stringSpacing(object.getStringSpacing())
                .isStartStatic(object.getIsStartStatic())
                .isEndStatic(object.getIsEndStatic())


                .constraints(new ArrayList<>())

                .isLineObject(object.getIsLineObject())

                .createdAt(object.getCreatedAt())
                .build();

        if (requestBody.getShape() == 3) {

            Optional<EditorObject> bodyA = editorObjectRepository.findById(object.getBodyA());

            bodyA.ifPresent(response::setBodyAData);

            Optional<EditorObject> bodyB = editorObjectRepository.findById(object.getBodyB());

            bodyB.ifPresent(response::setBodyBData);
        }

        return response;
    }

    public void updateObject(Long id, EditorObjectRequestBody requestBody) {
        EditorObject object = editorObjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        if (requestBody.getName() != null) object.setName(requestBody.getName());
        if (requestBody.getShape() != null) object.setShape(requestBody.getShape());
        if (requestBody.getFill() != null) object.setFill(requestBody.getFill());
        if (requestBody.getWidth() != null) object.setWidth(requestBody.getWidth());
        if (requestBody.getHeight() != null) object.setHeight(requestBody.getHeight());
        if (requestBody.getTop() != null) object.setYAxis(requestBody.getTop());
        if (requestBody.getLeft() != null) object.setXAxis(requestBody.getLeft());
        if (requestBody.getAngle() != null) object.setAngle(requestBody.getAngle());
        if (requestBody.getRadius() != null) object.setRadius(requestBody.getRadius());
        if (requestBody.getIsFrozen() != null) object.setIsFrozen(requestBody.getIsFrozen());
        if (requestBody.getColor() != null) object.setColor(requestBody.getColor());
        if (requestBody.getStrokeColor() != null) object.setStrokeColor(requestBody.getStrokeColor());
        if (requestBody.getLineWidth() != null) object.setLineWidth(requestBody.getLineWidth());

        // forces
        if (requestBody.getVelocityX() != null) object.setVelocityX(requestBody.getVelocityX());
        if (requestBody.getVelocityY() != null) object.setVelocityY(requestBody.getVelocityY());
        if (requestBody.getFriction() != null) object.setFriction(requestBody.getFriction());
        if (requestBody.getFrictionStatic() != null) object.setFrictionStatic(requestBody.getFrictionStatic());
        if (requestBody.getFrictionAir() != null) object.setFrictionAir(requestBody.getFrictionAir());
        if (requestBody.getRestitution() != null) object.setRestitution(requestBody.getRestitution());
        if (requestBody.getDensity() != null) object.setDensity(requestBody.getDensity());
        if (requestBody.getMass() != null) object.setMass(requestBody.getMass());
        if (requestBody.getInertia() != null) object.setInertia(requestBody.getInertia());

        if (requestBody.getIsLineObject() != null) object.setIsLineObject(requestBody.getIsLineObject());
        if (requestBody.getLineDefault() != null) object.setLineDefault(requestBody.getLineDefault());
        if (requestBody.getLineId() != null) object.setLineId(requestBody.getLineId());
        if (requestBody.getPointAX() != null) object.setPointAX(requestBody.getPointAX());
        if (requestBody.getPointAY() != null) object.setPointAY(requestBody.getPointAY());
        if (requestBody.getPointBX() != null) object.setPointBX(requestBody.getPointBX());
        if (requestBody.getPointBY() != null) object.setPointBY(requestBody.getPointBY());
        if (requestBody.getStiffness() != null) object.setStiffness(requestBody.getStiffness());
        if (requestBody.getDamping() != null) object.setDamping(requestBody.getDamping());
        if (requestBody.getLength() != null) object.setLength(requestBody.getLength());


        if (requestBody.getBodyA() != null) {


            if (!Objects.equals(object.getBodyA(), object.getBodyADefault())) {
                Optional<EditorObject> oldBodyA = editorObjectRepository.findById(object.getBodyA());

                oldBodyA.ifPresent(editorObject -> constrainsObjectRepository.deleteByObjectId(editorObject.getId()));
            }

            if (requestBody.getBodyA() == 0) object.setBodyA(object.getBodyADefault());
            else {
                EditorObject bodyA = editorObjectRepository.findById(requestBody.getBodyA()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

                if (!constrainsObjectRepository.existsByObjectIdAndConstraintId(bodyA.getId(), object.getId())) {
                    constrainsObjectRepository.save(ConstraintsObject.builder()
                            .objectId(bodyA.getId())
                            .constraintId(object.getId())
                            .build());
                }

                object.setBodyA(bodyA.getId());
            }
        }


        if (requestBody.getBodyB() != null) {


//            if (!Objects.equals(object.getBodyB(), object.getBodyBDefault())) {
            if (object.getBodyB() != null) {
                Optional<EditorObject> oldBodyB = editorObjectRepository.findById(object.getBodyB());

                oldBodyB.ifPresent(editorObject -> constrainsObjectRepository.deleteByObjectIdAndConstraintId(editorObject.getId(), object.getId()));
            }
//            }

            if (requestBody.getBodyB() == 0) {
                object.setBodyB(object.getBodyBDefault());
            } else {
                EditorObject bodyB = editorObjectRepository.findById(requestBody.getBodyB()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

                if (!constrainsObjectRepository.existsByObjectIdAndConstraintId(bodyB.getId(), object.getId())) {
                    constrainsObjectRepository.save(ConstraintsObject.builder()
                            .objectId(bodyB.getId())
                            .constraintId(object.getId())
                            .build());
                }

                object.setBodyB(bodyB.getId());
            }
        }

        editorObjectRepository.save(object);

    }

//    public void updateString(Long stringId, Long objectId, EditorObjectRequestBody requestBody) {
//        EditorObject string = editorObjectRepository.findById(stringId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));
//
//        if (objectId != 0) {
//        }
//    }


    public void groupObject(List<Long> objects) {
        List<EditorObject> objectList = new ArrayList<>();

        for (Long objectId : objects) {
            Optional<EditorObject> editorObject = editorObjectRepository.findById(objectId);

            editorObject.ifPresent(objectList::add);
        }
    }

    public void deleteObject(Long id) {
        UserDTO user = authService.getCurrentUser();

        EditorObject object = editorObjectRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        List<ConstraintsObject> constraints = constrainsObjectRepository.findAllByObjectId(object.getId());

        for (ConstraintsObject obj : constraints) {
            Optional<EditorObject> constraint = editorObjectRepository.findById(obj.getConstraintId());


            if (constraint.isPresent()) {
                if (Objects.equals(constraint.get().getBodyA(), object.getId()))
                    constraint.get().setBodyA(constraint.get().getBodyADefault());
                else constraint.get().setBodyB(constraint.get().getBodyBDefault());

                editorObjectRepository.save(constraint.get());
            }
        }

//        if (object.getLineId() != null) {
//            Optional<EditorObject> lineObject = editorObjectRepository.findById(object.getLineId());
//
//            if (lineObject.isPresent()) {
//                EditorObject line = lineObject.get();
//
//                if (Objects.equals(line.getBodyA(), object.getId())) {
//                    line.setBodyA(line.getBodyADefault());
//                }
//
//                if (Objects.equals(line.getBodyB(), object.getId())) {
//                    line.setBodyB(line.getBodyBDefault());
//                }
//            }
//        }

        Optional<GroupObject> groupObject = groupObjectRepository.findByObjectId(id);

        groupObject.ifPresent(groupObjectRepository::delete);

        if (object.getShape() == 3) {
            editorObjectRepository.deleteById(object.getBodyA());
            editorObjectRepository.deleteById(object.getBodyB());
        }

        editorObjectRepository.delete(object);
    }
}
