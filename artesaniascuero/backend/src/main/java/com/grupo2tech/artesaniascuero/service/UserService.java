package com.grupo2tech.artesaniascuero.service;
import org.springframework.stereotype.Service;
import com.grupo2tech.artesaniascuero.model.User;
import com.grupo2tech.artesaniascuero.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){

        // lógica de negocio
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("El email ya existe");
        }

        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new RuntimeException("El username ya existe");
        }

        user.setRole("USER");
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}
