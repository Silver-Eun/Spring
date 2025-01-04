package com.spring.songjava.mvc.repository;

import com.spring.songjava.mvc.domain.UploadFile;
import com.spring.songjava.mvc.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {

    void save(UploadFileParameter parameter);

    UploadFile get(int uploadFIleSeq);
}
