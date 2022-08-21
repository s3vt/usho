package io.s3vt.usho.service;

import io.s3vt.usho.model.UshoEntity;
import io.s3vt.usho.repo.UshoRepo;
import io.s3vt.usho.util.UShoGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UshoService {

    @Value("${usho.short.len}")
    private int ushoLength;


    @Autowired
    UshoRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(UshoService.class);

    public UshoEntity createUsho(String longUrl) {
        return repo.save(new UshoEntity(UShoGenerator.generateShortUrl(ushoLength), longUrl));
    }

    @Cacheable(value = "usho")
    public UshoEntity find(String usho) {
        return repo.findById(usho).orElse(null);
    }

    @CacheEvict("usho")
    public void deleteUsho(String usho) {
        repo.deleteById(usho);
    }
}
