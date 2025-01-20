package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutsourcedPartRepositoryTest {

  OutsourcedPartRepository outsourcedPartRepository;
  @BeforeEach
  void setUp() {
    outsourcedPartRepository=mock(OutsourcedPartRepository.class);
  }
  @Test
  void findAll() {
    OutsourcedPart part=new OutsourcedPart();
    List partData=new ArrayList();
    partData.add(part);
    when(outsourcedPartRepository.findAll()).thenReturn(partData);
    List<OutsourcedPart> parts=(List<OutsourcedPart>)outsourcedPartRepository.findAll();
    assertEquals(partData.size(),1);
  }
}
