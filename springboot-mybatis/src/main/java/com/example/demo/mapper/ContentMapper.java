package com.example.demo.mapper;

import com.example.demo.domain.Content;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dinghw on 2017/7/3.
 */
@Repository("contentMapper")
public interface ContentMapper {


    @Select("SELECT * FROM t_content_base where effectivedate is not null and rownum < 1000")
    @Results({
            @Result(property = "gcid", column = "gcid"),
            @Result(property = "cid", column = "contentid"),
            @Result(property = "name", column = "contentname"),
            @Result(property = "datatype", column = "datatype"),
            @Result(property = "pic1", column = "pic1"),
            @Result(property = "pic2", column = "pic2"),
            @Result(property = "pic3", column = "pic3"),
            @Result(property = "pic4", column = "pic4"),
            @Result(property = "pic5", column = "pic5"),
            @Result(property = "character1", column = "character1"),
            @Result(property = "character2", column = "character2"),
            @Result(property = "description", column = "description"),
            @Result(property = "effectiveStartTime", column = "effectivedate"),
            @Result(property = "effectiveEndTime", column = "invaliddate"),
            @Result(property = "price", column = "price")
    })
    List<Content> getAll();

    @Select("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from t_content_base) A WHERE ROWNUM <= #{toIndex,jdbcType=INTEGER}) WHERE RN >= #{fromIndex,jdbcType=INTEGER}")
    @Results({
            @Result(property = "gcid", column = "gcid"),
            @Result(property = "cid", column = "contentid"),
            @Result(property = "name", column = "contentname"),
            @Result(property = "datatype", column = "datatype"),
            @Result(property = "pic1", column = "pic1"),
            @Result(property = "pic2", column = "pic2"),
            @Result(property = "pic3", column = "pic3"),
            @Result(property = "pic4", column = "pic4"),
            @Result(property = "pic5", column = "pic5"),
            @Result(property = "character1", column = "character1"),
            @Result(property = "character2", column = "character2"),
            @Result(property = "description", column = "description"),
            @Result(property = "effectiveStartTime", column = "effectivedate"),
            @Result(property = "effectiveEndTime", column = "invaliddate"),
            @Result(property = "price", column = "price")
    })
    List<Content> getPageList(@Param("fromIndex") int fromIndex, @Param("toIndex") int toIndex);


    @Select("SELECT * FROM t_content_base WHERE gcid = #{gcid}")
    @Results({
            @Result(property = "gcid", column = "gcid"),
            @Result(property = "cid", column = "contentid"),
            @Result(property = "name", column = "contentname"),
            @Result(property = "datatype", column = "datatype"),
            @Result(property = "pic1", column = "pic1"),
            @Result(property = "pic2", column = "pic2"),
            @Result(property = "pic3", column = "pic3"),
            @Result(property = "pic4", column = "pic4"),
            @Result(property = "pic5", column = "pic5"),
            @Result(property = "character1", column = "character1"),
            @Result(property = "character2", column = "character2"),
            @Result(property = "description", column = "description"),
            @Result(property = "effectiveStartTime", column = "effectivedate"),
            @Result(property = "effectiveEndTime", column = "invaliddate"),
            @Result(property = "price", column = "price")
    })
    Content getOne(String gcid);
}
