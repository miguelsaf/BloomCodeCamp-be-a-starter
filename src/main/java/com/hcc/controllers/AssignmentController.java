package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @GetMapping(value="/api/assignments")
    public ResponseEntity<?> getAssignmentsByUser(){
        User user = userDetailService.getLoogedUser();
        return ResponseEntity.ok(assignmentService.findAssignmentsByUser(user));
    }

    @GetMapping("/api/assignments/{id}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentsById(@PathVariable Long id){
        AssignmentResponseDto responseDto = assignmentService.findAssignmentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value="/api/assignments")
    public ResponseEntity<AssignmentResponseDto> postAssignment(@RequestBody Assignment assignment) {
        User user = userDetailService.getLoogedUser();
        assignment.setLmsUser(user);
        AssignmentResponseDto savedAssignmentResponseDto = assignmentService.save(assignment);
        return new ResponseEntity<>(savedAssignmentResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value="/api/assignments/{id}")
    public ResponseEntity<AssignmentResponseDto> putAssignmentById(@PathVariable Long id, @RequestBody Assignment assignment){

        AssignmentResponseDto updatedAssignmentResponseDto = assignmentService.update(id, assignment);
        return ResponseEntity.ok(updatedAssignmentResponseDto);
    }



}
