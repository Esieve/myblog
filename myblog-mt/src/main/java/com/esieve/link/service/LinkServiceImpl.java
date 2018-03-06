package com.esieve.link.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;
import com.esieve.link.dao.LinkDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 500, loadbalance = "leastactive")
public class LinkServiceImpl implements LinkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);

    @Autowired
    private LinkDao linkDao;

    @Override
    public Link getLinkByLinkId(int linkId) {
        Link link = linkDao.getLinkByLinkId(linkId);
        if (link == null) {
            LOGGER.warn("get link error, linkId {}", linkId);
        }
        return link;
    }

    @Override
    public List<Link> getLinks() {
        return linkDao.getLinks();
    }

    @Override
    public OperationResult insertLink(Link link) {
        int result = linkDao.insertLink(link);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("insert link error, link {}", link);
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
            LOGGER.warn("update link error, link {}", link);
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
            LOGGER.warn("delete link error, linkId {}", linkId);
        }
        return operationResult;
    }
}
