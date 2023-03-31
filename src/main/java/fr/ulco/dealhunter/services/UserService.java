package fr.ulco.dealhunter.services;

import fr.ulco.dealhunter.exceptions.PasswordMismatchException;
import fr.ulco.dealhunter.exceptions.UserAlreadyExistsException;
import fr.ulco.dealhunter.models.dto.auth.CreateUserRequestDto;
import fr.ulco.dealhunter.models.dto.auth.UserResponseDto;
import fr.ulco.dealhunter.models.entities.UserEntity;
import fr.ulco.dealhunter.models.mappers.UserMapper;
import fr.ulco.dealhunter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto create(CreateUserRequestDto newUserRequest) throws UserAlreadyExistsException, PasswordMismatchException {
        if (userRepository.findByUsername(newUserRequest.getUsername()).isPresent()) {
            log.error(format("A user tried to register as %s, but the user already exists", newUserRequest.getUsername()));
            throw new UserAlreadyExistsException(newUserRequest.getUsername());
        }
        if (!newUserRequest.getPassword().equals(newUserRequest.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }

        UserEntity user = userMapper.toEntity(newUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return userMapper.toOutputDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", username)));
    }
}
