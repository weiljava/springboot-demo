package com.example.demo.controller;

import com.example.demo.domain.Content;
import com.example.demo.mapper.ContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dinghw on 2017/7/3.
 */
@Controller
public class ContentController {
    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentMapper contentMapper;

    @RequestMapping("/content/list")
    public String list(Model model) {
        List<Content> list = contentMapper.getAll();
        model.addAttribute("list", list);
        log.info("查询出来的list长度" + list.size());
        return "content_list";
    }

    @RequestMapping("/content/page")
    public String pageList(Model model, @RequestParam(value = "pageIndex", required = true) int pageIndex, @RequestParam(value = "pageSize", required = true) int pageSize) {

        int fromIndex = (pageIndex - 1) * pageSize + 1;
        int toIndex = pageIndex * pageSize;//将页数和每页条目数转为页数范围
        List<Content> list = contentMapper.getPageList(fromIndex, toIndex);
        model.addAttribute("list", list);
        log.info("查询出来的list长度" + list.size());
        return "content_list";
    }


    @RequestMapping(value = "/content/getone")
    public String getOne(Model model, @RequestParam(value = "gcid", required = true) String gcid) {
        Content content = contentMapper.getOne(gcid);
        model.addAttribute("content", content);
        return "content_detail";
    }
}
