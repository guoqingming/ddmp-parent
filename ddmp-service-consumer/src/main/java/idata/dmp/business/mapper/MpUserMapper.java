package idata.dmp.business.mapper;

import idata.dmp.business.bean.MpUser;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

//@Mapper
public interface MpUserMapper {

    int deleteById(Integer id);

    int insert(MpUser record);

    int insertSelective(MpUser record);

    MpUser getById(Integer id);

    int updateByIdSelective(MpUser record);

    int updateById(MpUser record);

    List<MpUser> listUsers();
}