package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.config.PasswordEncoder;
import com.sparta.jpaupgradeschedule.dto.LoginRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.entity.UserRoleEnum;
import com.sparta.jpaupgradeschedule.jwt.JwtUtil;
import com.sparta.jpaupgradeschedule.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto, HttpServletResponse res) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User newUser = new User(requestDto);

        User saveUser = userRepository.save(newUser);

        // JWT 생성
        String token = jwtUtil.createToken(saveUser.getEmail(), UserRoleEnum.USER);
        // JWT 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return new UserSaveResponseDto(saveUser);
    }

    public UserSaveResponseDto getUser(Long id) {
        User idUser = userFindById(id);

        return new UserSaveResponseDto(idUser);
    }

    public List<UserSaveResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserSaveResponseDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserSaveResponseDto dto = new UserSaveResponseDto(user);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public UserSaveResponseDto updateUser(Long id, UserSaveRequestDto requestDto) {
        User idUser = userFindById(id);
        idUser.update(requestDto);

        return new UserSaveResponseDto(idUser);
    }

    @Transactional
    public String deleteUser(Long id) {
        userRepository.delete(userFindById(id));
        return "삭제완료";
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) throws IOException {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // email 확인
        User user = userRepository.findByEmail(email);
        if (user == null) {
            res.sendError(401,"401, email error");
        }

        // 비밀번호 확인
        else if (!passwordEncoder.matches(password, user.getPassword())) {
            res.sendError(401, "401, password error");
        } else {
            // JWT 생성
            String token = jwtUtil.createToken(user.getEmail(), user.getRole());
            jwtUtil.addJwtToCookie(token, res);
        }
    }

    public User userFindById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음"));
    }
}