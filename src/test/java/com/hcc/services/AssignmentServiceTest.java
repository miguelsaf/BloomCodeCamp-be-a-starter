package com.hcc.services;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.repositories.AssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentServiceTest {

    @Mock
    AssignmentRepository assignmentRepository;

    @Mock
    AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAssignmentByIdValidIdReturn() {
        //Given
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        assignment.setBranch("end points");


        //When
        when(assignmentRepository.findById(assignment.getId())).thenReturn(Optional.of(assignment));
        AssignmentResponseDto assignmentResponseDto = assignmentService.findAssignmentById(assignment.getId()) ;

        //Then
        assertNotNull(assignmentResponseDto);
        assertEquals("end points", assignmentResponseDto.getAssignment().getBranch());
        verify(assignmentRepository, times(1)).findById(assignment.getId());
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findAssignmentsByUser() {
    }
}