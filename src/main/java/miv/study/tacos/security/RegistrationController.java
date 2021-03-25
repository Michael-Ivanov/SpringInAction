package miv.study.tacos.security;

import miv.study.tacos.User;
import miv.study.tacos.jpadatarepository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processForm(User user) {

        // encode user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        logger.info("processForm saving user: " + user);
        userRepository.save(user);
        return "redirect:/loginPage";
    }
}
