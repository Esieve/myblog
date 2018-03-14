package com.esieve.link.service;

import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;

import java.util.List;

public interface LinkService {
    OperationResult<Link> getLinkByLinkId(int linkId);

    List<Link> getLinks();

    OperationResult insertLink(Link link);

    OperationResult updateLink(Link link);

    OperationResult deleteLink(int linkId);
}
