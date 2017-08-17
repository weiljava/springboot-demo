package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinghw on 2017/7/3.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);

        Map<String, Integer> m = new HashMap<String, Integer>();
        m.put("物理", 89);
        m.put("数学", 88);
        m.put("英语", 70);
        m.put("语文", 60);
        m.put("化学", 80);
        model.addAttribute("map",m);
        return "hello";
    }
}
