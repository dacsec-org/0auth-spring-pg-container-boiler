package org.dacsec.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockService {
    @Autowired
    private MockRepository mockRepository;
    
    public List<Mock> findAllMocks() {
        return mockRepository.findAll();
    }
}
