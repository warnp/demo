package com.example.demo.services;

import com.example.demo.dto.UrlModel;
import com.example.demo.dto.UrlsList;
import com.example.demo.model.UrlEntry;
import com.example.demo.repo.BasicUrlsQueries;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CrunchUrl {

    private BasicUrlsQueries urlsQueries;

    @Autowired
    public CrunchUrl(BasicUrlsQueries urlsQueries){
        this.urlsQueries = urlsQueries;
    }

    public void crunch(UrlModel urlModel){
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        UrlEntry urlEntry = new UrlEntry();
        urlEntry.setBaseUrl(urlModel.getUrl());
        urlEntry.setGeneratedLink(generatedString);
        urlsQueries.save(urlEntry);

    }

    public UrlsList getAllUrls(){
        Iterable<UrlEntry> all = urlsQueries.findAll();

        UrlsList urlsList = new UrlsList();
        urlsList.setUrls(new HashMap<>());
        all.forEach(urlEntry -> urlsList.getUrls().put(urlEntry.getGeneratedLink(), urlEntry.getBaseUrl()));
        return urlsList;
    }

    public String getUrlFromLink(String link){
        UrlEntry byGeneratedLink = urlsQueries.getByGeneratedLink(link);
        return byGeneratedLink.getBaseUrl();
    }
}
