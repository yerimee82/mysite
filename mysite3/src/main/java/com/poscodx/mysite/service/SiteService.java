package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public SiteVo getSite(Long no) {
         return siteRepository.find(no);
    }

    public void updateSite(SiteVo vo) {
        siteRepository.update(vo);
    }
}
