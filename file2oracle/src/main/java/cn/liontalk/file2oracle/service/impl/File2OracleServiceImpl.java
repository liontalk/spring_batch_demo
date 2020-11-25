package cn.liontalk.file2oracle.service.impl;

import cn.liontalk.file2oracle.dao.File2OracleMapper;
import cn.liontalk.file2oracle.entity.File2OracleEntity;
import cn.liontalk.file2oracle.service.File2OracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class File2OracleServiceImpl implements File2OracleService {

    @Autowired
    private File2OracleMapper file2OracleMapper;

    @Override
    public File2OracleEntity getFile2OracleEntityById(String id) {
        return file2OracleMapper.getFile2OracleEntityById(id);
    }
}
