package com.esieve.link.dao;


import com.esieve.link.bean.Link;

import java.util.List;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public interface LinkDao {

    Link getLinkByLinkId(int linkId);

    List<Link> getLinks();

    int insertLink(Link link);

    int updateLink(Link link);

    int deleteLink(int linkId);

}
