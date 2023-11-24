package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("#findById > When id is null > Throw an exception")
    void findByIdWhenIdIsNullThrowAnException(){
        assertThrows(IllegalArgumentException.class, ()->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNOtNullWhenAUserIsFoundReturnTheUser(){
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                .id(1)
                .name("Fellipe")
                .username("felliperey")
                .build()));
        User response = service.findById(1);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, response.getId()),
                ()-> Assertions.assertEquals("Fellipe", response.getName()),
                ()-> Assertions.assertEquals("felliperey", response.getUsername())
        );

    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throe an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException(){
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }


    //TODO: Implement test cases for getAllUsers

}
