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
    public OperationResult<Link> getLinkByLinkId(int linkId) {
        OperationResult<Link> result = new OperationResult<>();

        Link link = linkDao.getLinkByLinkId(linkId);
        if (link == null) {
            LOGGER.warn("get link error, linkId {}", linkId);
            result.setInfo("未找到该链接");
        } else {
            result.setSuccess(true);
            result.setData(link);
        }
        return result;
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
            operationResult.setInfo("插入成功");
        } else {
            LOGGER.warn("insert link error, link {}", link);
            operationResult.setInfo("插入失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult updateLink(Link link) {
        int result = linkDao.updateLink(link);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("更新成功");
        } else {
            LOGGER.warn("update link error, link {}", link);
            operationResult.setInfo("更新失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteLink(int linkId) {
        int result = linkDao.deleteLink(linkId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("删除成功");
        } else {
            LOGGER.warn("delete link error, linkId {}", linkId);
            operationResult.setInfo("删除失败");
        }
        return operationResult;
    }
}
