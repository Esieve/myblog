package com.esieve.link.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:service.xml", "classpath:consumer.xml"})
public class LinkServiceMainTest {
    @Reference
    private LinkService linkService;

    @Test
    public void main() {
        Link link = new Link("test", "test");

        OperationResult result = linkService.insertLink(link);
        Assert.assertTrue(result.isSuccess());

        List<Link> links = linkService.getLinks();
        Assert.assertNotNull(links);

        result = linkService.deleteLink(2);
        Assert.assertTrue(result.isSuccess());

        result = linkService.deleteLink(-1);
        Assert.assertFalse(result.isSuccess());
    }
}