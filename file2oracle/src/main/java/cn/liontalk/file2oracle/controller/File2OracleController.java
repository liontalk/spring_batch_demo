package cn.liontalk.file2oracle.controller;


import cn.liontalk.file2oracle.entity.File2OracleEntity;
import cn.liontalk.file2oracle.service.File2OracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/file2oracle")
public class File2OracleController {



    @Autowired
    private File2OracleService file2OracleService;

    @RequestMapping(value = "/getById/{id}")
    public File2OracleEntity  getFile2OracleEntityById(@PathVariable("id") String id){
        File2OracleEntity oracleEntity  = file2OracleService.getFile2OracleEntityById(id);
        return oracleEntity;
    }
}
