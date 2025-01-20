package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 */
class InhousePartServiceTest {
    InhousePartRepository inhousePartRepository;
    InhousePartService inhousePartService;
    @BeforeEach
    void setUp() {
        inhousePartRepository=mock(InhousePartRepository.class);
        inhousePartService=new InhousePartServiceImpl(inhousePartRepository);
    }

    @Test
    void findAll() {
        InhousePart part=new InhousePart();
        List partData=new ArrayList();
        partData.add(part);
        when(inhousePartRepository.findAll()).thenReturn(partData);
        List<InhousePart> parts=inhousePartService.findAll();
        assertEquals(partData.size(),1);
    }

    @Test
    void findById() {
        long partId = 1;
        InhousePart part = new InhousePart();
        part.setId(partId);
        when(inhousePartRepository.findById(partId)).thenReturn(Optional.of(part));
        InhousePart foundPart = inhousePartService.findById((int)partId);
        assertNotNull(foundPart);
        assertEquals(partId, foundPart.getId());
    }

    @Test
    void save() {
        InhousePart part = new InhousePart();
        inhousePartService.save(part);
        verify(inhousePartRepository, times(1)).save(part);
    }

    @Test
    void deleteById() {
        long partId = 1;
        inhousePartService.deleteById((int)partId);
        verify(inhousePartRepository, times(1)).deleteById(partId);
    }
}