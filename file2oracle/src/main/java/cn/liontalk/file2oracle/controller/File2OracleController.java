package cn.liontalk.file2oracle.controller;


import cn.liontalk.file2oracle.entity.File2OracleEntity;
import cn.liontalk.file2oracle.service.File2OracleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/file2oracle")
@Api(value = "File2OracleController", tags = {"文件到Oracle数据库"})
public class File2OracleController {



    @Autowired
    private File2OracleService file2OracleService;

    @GetMapping(value = "/getById")
    @ApiOperation(value = "测试API,查看数据库是否可以查询数据")
    public File2OracleEntity  getFile2OracleEntityById(@ApiParam(value = "查询详情显示ID") @RequestParam(value = "id") String id){
        File2OracleEntity oracleEntity  = file2OracleService.getFile2OracleEntityById(id);
        return oracleEntity;
    }
}
