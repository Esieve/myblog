package com.esieve.link.dao;


import com.esieve.link.bean.Link;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public interface LinkDao {

    @Select("SELECT * FROM link WHERE link_id=#{linkId}")
    Link getLinkByLinkId(int linkId);

    @Select("SELECT * FROM link")
    List<Link> getLinks();

    @Insert("INSERT INTO link VALUES (NULL ,#{linkName},#{url})")
    int insertLink(Link link);

    @Update("UPDATE link SET link_name=#{linkName},url=#{url} WHERE link_id=#{linkId}")
    int updateLink(Link link);

    @Delete("DELETE FROM link where link_id=#{linkId}")
    int deleteLink(int linkId);

}
