package com.example.cashsplash.converters;

import com.example.cashsplash.common.Converter;
import com.example.cashsplash.models.User;
import com.example.cashsplash.dtos.user.UserRequestDTO;
import com.example.cashsplash.dtos.user.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserRequestDTO, UserResponseDTO> {
    private final ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User requestDTOToEntity(UserRequestDTO request) {
        return this.modelMapper.map(request, User.class);
    }

    public User responseDTOToEntity(UserResponseDTO response) {
        return this.modelMapper.map(response, User.class);
    }

    public UserRequestDTO entityToRequestDTO(User user) {
        return this.modelMapper.map(user, UserRequestDTO.class);
    }

    public UserResponseDTO entityToResponseDTO(User user) {
        return this.modelMapper.map(user, UserResponseDTO.class);
    }
}
