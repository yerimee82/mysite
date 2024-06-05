package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.GuestbookRepository;
import com.poscodx.mysite.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookService {
    private final GuestbookRepository guestbookRepository;

    @Autowired
    public GuestbookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public void deleteContents(Long no, String password) {
        guestbookRepository.deleteByNoAndPassword(no, password);
    }

    public void addContents(GuestbookVo vo) {
        System.out.println(vo);
        guestbookRepository.insert(vo);
        System.out.println(vo);
    }
}
