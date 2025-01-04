package com.spring.songjava.mvc.service;

import com.spring.songjava.mvc.domain.UploadFile;
import com.spring.songjava.mvc.parameter.UploadFileParameter;
import com.spring.songjava.mvc.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService {

    @Autowired
    private UploadFileRepository repository;

    public void save(UploadFileParameter parameter) {
        repository.save(parameter);
    }

    public UploadFile get(int uploadFIleSeq) {
        return repository.get(uploadFIleSeq);
    }
}
