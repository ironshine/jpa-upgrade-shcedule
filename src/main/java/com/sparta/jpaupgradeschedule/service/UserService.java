package com.sparta.jpaupgradeschedule.service;

import com.sparta.jpaupgradeschedule.dto.UserSaveRequestDto;
import com.sparta.jpaupgradeschedule.dto.UserSaveResponseDto;
import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto) {
        User newUser = new User(requestDto);

        User saveUser = userRepository.save(newUser);

        return new UserSaveResponseDto(saveUser);
    }

    public UserSaveResponseDto getUser(Long id) {
        User idUser = userRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음"));

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
        User idUser = userRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음"));
        idUser.update(requestDto);

        return new UserSaveResponseDto(idUser);
    }

    @Transactional
    public String deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new NullPointerException("id 없음")));
        return "삭제완료";
    }
}