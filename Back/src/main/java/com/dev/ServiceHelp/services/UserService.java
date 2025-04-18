package com.dev.ServiceHelp.services;

import com.dev.ServiceHelp.models.dto.shared.RoleDTO;
import com.dev.ServiceHelp.models.dto.shared.UserDTO;
import com.dev.ServiceHelp.models.dto.input.UserUpdateDTO;
import com.dev.ServiceHelp.models.entities.Department;
import com.dev.ServiceHelp.models.entities.Role;
import com.dev.ServiceHelp.models.entities.SolvingArea;
import com.dev.ServiceHelp.models.entities.User;
import com.dev.ServiceHelp.enums.StatusUser;
import com.dev.ServiceHelp.mappers.UserMapper;
import com.dev.ServiceHelp.projections.UserDetailsProjection;
import com.dev.ServiceHelp.repository.DepartmentRepository;
import com.dev.ServiceHelp.repository.RoleRepository;
import com.dev.ServiceHelp.repository.SolvingAreaRepository;
import com.dev.ServiceHelp.repository.UserRepository;
import com.dev.ServiceHelp.services.exceptions.ResourceNotFoundException;
import com.dev.ServiceHelp.utils.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final SolvingAreaRepository solvingAreaRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    @Transactional
    public UserDTO updateDataUserLogged(UserUpdateDTO userUpdateDTO) {

        User user = ResourceUtil.getOrThrow(
                userRepository.findById(authenticated().getId()),
                "User with ID " + authenticated().getId() + " not found");

        if (userUpdateDTO.getSolvingArea() != null) {
            SolvingArea solvingArea = ResourceUtil.getOrThrow(
                    solvingAreaRepository.findById(userUpdateDTO.getSolvingArea().getId()),
                    "SolvingArea with ID " + userUpdateDTO.getSolvingArea().getId() + " not found");
            user.setSolvingArea(solvingArea);
        }

        if (userUpdateDTO.getSolvingArea() != null) {
            Department department = ResourceUtil.getOrThrow(
                    departmentRepository.findById(userUpdateDTO.getDepartment().getId()),
                    "Department with ID " + userUpdateDTO.getDepartment().getId() + " not found");
            user.setDepartment(department);
        }

        if (userUpdateDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getUserPaged(Long id, String name, StatusUser status, Pageable pageable) {
        Page<User> usersResult = userRepository.searchByName(id, name, status, pageable);
        return usersResult.map(user -> userMapper.toUserDTO(user));
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User user = obj.orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return userMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO findUserLogged() {
        User entity = authenticated();
        return userMapper.toUserDTO(entity);
    }

    @Transactional
    public UserDTO unblockUser(UserDTO userDTO) {
        User user = ResourceUtil.getOrThrow(
                userRepository.findById(userDTO.getId()),
                "User with ID " + userDTO.getId() + " not found");
        user.setFailedLoginAttempts(0);
        user.setBlocked(false);
        User userUnlocked = userRepository.save(user);
        return userMapper.toUserDTO(userUnlocked);
    }

    @Transactional
    public UserDTO changeUserStatus(UserDTO userDTO) {
        User user = ResourceUtil.getOrThrow(
                userRepository.findById(userDTO.getId()),
                "User with ID " + userDTO.getId() + " not found");
        user.setStatusUser(userDTO.getStatusUser());
        User userStatusChanged = userRepository.save(user);
        return userMapper.toUserDTO(userStatusChanged);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {

        User user = userMapper.toUserEntity(dto);

        Department department = ResourceUtil.getOrThrow(
                departmentRepository.findById(dto.getDepartment().getId()),
                "Department with ID " + dto.getDepartment().getId() + " not found");
        SolvingArea solvingArea = ResourceUtil.getOrThrow(
                solvingAreaRepository.findById(dto.getSolvingArea().getId()),
                "Department with ID " + dto.getDepartment().getId() + " not found");

        user.setDepartment(department);
        user.setSolvingArea(solvingArea);
        user.setPassword(passwordEncoder.encode("123"));
        user.setStatusUser(StatusUser.ACTIVE);
        user.setFailedLoginAttempts(0);
        for (RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            user.getRoles().add(role);
        }
        user.setBlocked(false);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRoleByEmail(username);

        if (result.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setFirstName(result.get(0).getName());
        user.setLastName(result.get(0).getLast());
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        user.setStatusUser(StatusUser.values()[result.get(0).getStatus().intValue()]);

        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    public User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

}
