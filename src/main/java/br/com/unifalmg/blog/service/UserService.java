package br.com.unifalmg.blog.service;

import br.com.unifalmg.blog.controller.request.UserRequest;
import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.InvalidUserException;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers(){
        //TODO: Fazer a conexão com o BD
        return repository.findAll();
    }

    public User findById(Integer id){
        if (Objects.isNull(id)){
            throw new IllegalArgumentException("Id null when fetching for an user.");
        }
        return repository.findById(id).orElseThrow(() ->
             new UserNotFoundException(
                    String.format("No user found for id %d",id))
        );

    }

    public User add(User user){
        if (Objects.isNull(user)
        || Objects.isNull(user.getName()) || Objects.isNull(user.getUsername())
            || Objects.isNull(user.getEmail()) || user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getName().isEmpty()){
            throw new InvalidUserException();
        }
        return repository.save(user);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public User add(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .website(request.getWebsite())
                .build();
        return add(user);
    }

}
