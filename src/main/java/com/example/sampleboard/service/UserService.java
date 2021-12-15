package com.example.sampleboard.service;

import com.example.sampleboard.entity.user.User;
import com.example.sampleboard.entity.user.dto.UserRequestDto;
import com.example.sampleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 로그인 요청 받았을 때 진입해서 유저 정보를 조회하고 반환
     * @param email
     * @return User
     * @throws UsernameNotFoundException
     */
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
    }

    public Long save(UserRequestDto userRequestDto){
        boolean existUser = userRepository.findByEmail(userRequestDto.getEmail()).isEmpty();

        if(!existUser) return null; // 기존회원

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRequestDto.setPassword(encoder.encode(userRequestDto.getPassword()));

        return userRepository.save(userRequestDto.toEntity()).getUserId();
    }

}
