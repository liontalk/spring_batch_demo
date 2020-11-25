package cn.liontalk.file2oracle.dao;

import cn.liontalk.file2oracle.entity.File2OracleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface File2OracleMapper {

    File2OracleEntity getFile2OracleEntityById(@Param("id") String id);

}
