package com.catalog.catalog.mappers;
import com.catalog.catalog.dto.SignUpDTO;
import com.catalog.catalog.dto.UserDTO;
import com.catalog.catalog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
@Component
@Mapper(componentModel="spring")

public interface UserMapper {
    @Mapping(source = "role.roleName", target = "roleName")
    UserDTO toUserDTO(User user);

    User signUpToUser(SignUpDTO userDTO);

}
