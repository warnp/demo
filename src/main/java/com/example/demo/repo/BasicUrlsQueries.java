package com.example.demo.repo;

import com.example.demo.model.UrlEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUrlsQueries extends CrudRepository<UrlEntry, Long> {
//    void save(String baseUrl, String link);
//    List<UrlEntry> findAll();

    UrlEntry getByGeneratedLink(String link);

}
