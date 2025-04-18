package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.shared.UserDTO;
import com.dev.ServiceHelp.models.dto.input.UserUpdateDTO;
import com.dev.ServiceHelp.enums.StatusUser;
import com.dev.ServiceHelp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PatchMapping(value = "/updateDataUserLogged")
    public ResponseEntity<UserDTO> updateDataUserLogged(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO userDataUpdated = userService.updateDataUserLogged(userUpdateDTO);
        return ResponseEntity.ok().body(userDataUpdated);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")

    public ResponseEntity<Page<UserDTO>> getUserPaged(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "status", required = false) StatusUser status,
            Pageable pageable) {
        {
            Page<UserDTO> list = userService.getUserPaged(id, name, status, pageable);
            return ResponseEntity.ok().body(list);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "getUser/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO dto = userService.getUserById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getUserProfile() {
        UserDTO dto = userService.findUserLogged();
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTO userDTO) {
        UserDTO user = userService.insert(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/unblock")
    public ResponseEntity<UserDTO> unblockUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO user = userService.unblockUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user/status")
    public ResponseEntity<UserDTO> changeUserStatus(@RequestBody @Valid UserDTO userStatusDTO) {
        UserDTO user = userService.changeUserStatus(userStatusDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
