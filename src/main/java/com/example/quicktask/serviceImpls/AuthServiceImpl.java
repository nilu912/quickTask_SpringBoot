package com.example.quicktask.serviceImpls;

//import com.example.quicktask.common.CheckNullAndEmpty;
import com.example.quicktask.common.CheckNullAndEmpty;
import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.LoginUserDTO;
import com.example.quicktask.dtos.RegisterUserDTO;
import com.example.quicktask.entity.User;
import com.example.quicktask.repository.UserRepository;
import com.example.quicktask.security.JwtService;
import com.example.quicktask.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

//    @Autowired
//    private User user;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
//    @Autowired
//    private  CheckNullAndEmpty checkNullAndEmpty;

    public CommonResponseBean registerUser(RegisterUserDTO dto) {
        try {
//            if(dto.getEmail() == null || dto.getEmail().isEmpty() || dto.getPassword() == null || dto.getPassword().isEmpty() || dto.getUsername() == null ||dto.getUsername().isEmpty() || dto.getAge().toString() == null || dto.getAge().toString().isEmpty() || dto.getAddress().isEmpty())
            if(CheckNullAndEmpty.isNullAndEmpty(dto.getEmail()) ||
                    CheckNullAndEmpty.isNullAndEmpty(dto.getPassword()) ||
                    CheckNullAndEmpty.isNullAndEmpty(dto.getName()) ||
                    CheckNullAndEmpty.isNullAndEmpty(dto.getAddress()) ||
                    CheckNullAndEmpty.isNullAndEmpty(dto.getAge()))
                return new CommonResponseBean(false, 404, "parameter is missing", null);

            Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
            if(existingUser != null)
                return new CommonResponseBean(false, 5000, "email id already exist", null);

            User user = new User();

            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setAge(dto.getAge());
            user.setAddress(dto.getAddress());
            userRepository.save(user);

            return new CommonResponseBean(true, 200, "", dto);
        } catch (Exception e) {
            System.out.println("Registration error"+ e);
        }
        return new CommonResponseBean(false, 500, "Something went wrong", null);
    }

    public CommonResponseBean loginUser(LoginUserDTO dto) {
        try {
//            if(dto.getEmail() == null || dto.getPassword() == null || dto.getEmail().isEmpty() || dto.getPassword().isEmpty())
            if(CheckNullAndEmpty.isNullAndEmpty(dto.getEmail()) || CheckNullAndEmpty.isNullAndEmpty(dto.getPassword()))
                return new CommonResponseBean(false, 404, "parameter is missing", null);

            // step 1 - Verify Credentials
            // this internally call loadUserByUsername + Bcrypt.matches
            // If wrong then it throws AuthenticationException + Spring return 401
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );

            // step 2 - if credentials are valid then we reach here
            // Load the user to generate token
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // step 3 - generate token
            String token = jwtService.generateToken(user.getEmail());

            return new CommonResponseBean(true, 200, "", token);

//            Optional<User> user = userRepository.findByEmail(dto.getEmail());
//            if(user == null) return  new CommonResponseBean(false, 404, "User not found", null);
//            if(passwordEncoder.matches(dto.getPassword(), user.getPassword()))
//                return new CommonResponseBean(true, 200, "", "login successfully");
//            else
//                return new CommonResponseBean(false, 404, "invalid credentials", null);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println("Login error: "+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server error", null);
    }
}