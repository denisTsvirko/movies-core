package com.mymovies.movies.service.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mymovies.movies.configs.JwtTokenProvider;
import com.mymovies.movies.entity.auth.Role;
import com.mymovies.movies.entity.auth.User;
import com.mymovies.movies.exception.BadRequestException;
import com.mymovies.movies.model.dto.LoginDto;
import com.mymovies.movies.model.request.SignupRequest;
import com.mymovies.movies.repository.RoleRepository;
import com.mymovies.movies.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public void registerUser(SignupRequest signupRequest) {
        Set<Role> roles = new HashSet<>();

        for (String role : signupRequest.getRoles()) {
            Role optionalRole = roleRepository.findByName(role);
	
            if(optionalRole == null){
                throw new BadRequestException("Role " + role + "not found");
            }
            roles.add(optionalRole);
        }
   
		User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setRoles(roles);

		userRepository.save(user);
    }
}
