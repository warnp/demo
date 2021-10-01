package com.example.demo.controller;

import com.example.demo.dto.UrlModel;
import com.example.demo.dto.UrlsList;
import com.example.demo.services.CrunchUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrlController {

    private final CrunchUrl cruncher;

    @Autowired
    UrlController(CrunchUrl cruncher){
        this.cruncher = cruncher;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{link}")
    public ModelAndView reroute(@PathVariable String link){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("redirect:").append(cruncher.getUrlFromLink(link));
        return new ModelAndView(stringBuilder.toString());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/url-crunch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String insertUrl(@RequestBody UrlModel url){
        cruncher.crunch(url);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/urls", produces = "application/json")
    public ResponseEntity<UrlsList> getAllUrls(HttpEntity<byte[]> requestEntity){
        return new ResponseEntity<UrlsList>(cruncher.getAllUrls(), HttpStatus.ACCEPTED);
    }
}
