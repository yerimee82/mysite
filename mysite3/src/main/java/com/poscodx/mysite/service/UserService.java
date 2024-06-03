package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void join(UserVo vo) {
        userRepository.insert(vo);
    }

    public UserVo getUser(String email, String password) {
        return userRepository.findByNoAndPassword(email, password);
    }
    public UserVo getUser(Long no) {
        return userRepository.findByNo(no);
    }

    public void update(UserVo vo) {
        userRepository.update(vo);
    }
}
