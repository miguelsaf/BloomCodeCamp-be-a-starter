package com.hcc.services;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.exceptions.AssignmentNotFoundException;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    public AssignmentResponseDto findAssignmentById(Long id)throws AssignmentNotFoundException, NullPointerException {
        if( id == null) {
            throw new NullPointerException("Id is null");
        }
        Optional<Assignment> assignmentOpt = assignmentRepository.findById(id);
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto(assignmentOpt.orElseThrow(()-> new AssignmentNotFoundException("Assignment not found")));

        return assignmentResponseDto;
    }

    public AssignmentResponseDto save(Assignment assignment)throws NullPointerException {
        if( assignment == null) {
            throw new NullPointerException("Assignment is null");
        }
        Assignment savedAssignment = assignmentRepository.save(assignment);
        AssignmentResponseDto responseDto = new AssignmentResponseDto(savedAssignment);

        return responseDto;
    }

    public AssignmentResponseDto update(Long id, Assignment assignment) {
        Assignment savedAssignment = findAssignmentById(id).getAssignment();

        savedAssignment.setBranch(assignment.getBranch());
        savedAssignment.setNumber(assignment.getNumber());
        savedAssignment.setStatus(assignment.getStatus());
        savedAssignment.setGithubUrl(assignment.getGithubUrl());
        savedAssignment.setReviewVideoUrl(assignment.getReviewVideoUrl());

        return save(savedAssignment);
    }

    public List<AssignmentResponseDto> findAssignmentsByUser(User user){
        if(user == null){
            throw new RuntimeException("Username is null");
        }

        return assignmentRepository.findAssignmentsByLmsUser(user).stream()
                .map(ass -> new AssignmentResponseDto(ass))
                .collect(Collectors.toList());
    }
}
