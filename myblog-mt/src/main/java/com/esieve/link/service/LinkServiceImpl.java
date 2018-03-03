package com.esieve.link.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;
import com.esieve.link.dao.LinkDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 300, loadbalance = "leastactive")
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkDao linkDao;

    @Override
    public Link getLinkByLinkId(int linkId) {
        Link link = null;
        link = linkDao.getLinkByLinkId(linkId);
        return link;
    }

    @Override
    public List<Link> getLinks() {
        List<Link> links = null;
        links = linkDao.getLinks();
        return links;
    }

    @Override
    public OperationResult insertLink(Link link) {
        int result = linkDao.insertLink(link);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
        }
        return operationResult;
    }

    @Override
    public OperationResult updateLink(Link link) {
        int result = linkDao.updateLink(link);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteLink(int linkId) {
        int result = linkDao.deleteLink(linkId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
        }
        return operationResult;
    }
}
