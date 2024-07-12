package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void join(UserVo vo) {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        userRepository.insert(vo);
    }

    public UserVo getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    public UserVo getUser(Long no) {
        return userRepository.findByNo(no);
    }

    public UserVo getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(UserVo vo) {
        vo.setPassword(vo.getPassword().isEmpty() ? "" : passwordEncoder.encode(vo.getPassword()));
        userRepository.update(vo);
    }
}
