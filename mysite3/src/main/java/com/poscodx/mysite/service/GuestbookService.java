package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.GuestbookLogRepository;
import com.poscodx.mysite.repository.GuestbookRepository;
import com.poscodx.mysite.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestbookService {
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

    @Autowired
    public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository) {
        this.guestbookRepository = guestbookRepository;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    @Transactional
    public void deleteContents(Long no, String password) {
        guestbookLogRepository.update(no);
        guestbookRepository.deleteByNoAndPassword(no, password);
    }

    @Transactional
    public void addContents(GuestbookVo vo) {
        int count = guestbookLogRepository.update();
        if(count == 0) {
            guestbookLogRepository.insert();
        }
        guestbookRepository.insert(vo);
    }
}
