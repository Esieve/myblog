package com.esieve.link.dao;

import com.esieve.link.bean.Link;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service.xml")
public class LinkDaoTest {
    @Autowired
    private LinkDao linkDao;

    private int linkId = 4;

    @Test
    public void insertLink() {
        Link link = new Link("test", "test");
        int result = linkDao.insertLink(link);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getLinkByLinkId() {
        Link link = linkDao.getLinkByLinkId(linkId);
        Assert.assertNotNull(link);
        Assert.assertEquals("test", link.getLinkName());

        link = linkDao.getLinkByLinkId(-1);
        Assert.assertNull(link);
    }

    @Test
    public void getLinks() {
        List<Link> links = linkDao.getLinks();
        Assert.assertNotNull(links);
        Assert.assertEquals(1, links.size());
    }

    @Test
    public void updateLink() {
        Link link = new Link(linkId, "test", "test");
        int result = linkDao.updateLink(link);
        Assert.assertEquals(1, result);

        result = linkDao.updateLink(null);
        Assert.assertEquals(0, result);
    }

    @Test
    public void deleteLink() {
        int result = linkDao.deleteLink(linkId);
        Assert.assertEquals(1, result);

        result = linkDao.deleteLink(0);
        Assert.assertEquals(0, result);
    }
}