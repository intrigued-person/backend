package com.headout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.headout.dao.UserDao;
import com.headout.dto.UserDto;
import com.headout.model.User;
import com.headout.serviceImp.UserServiceImp;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateUser_UserWithoutEmail() {
        // Arrange
        when(user.getFirstName()).thenReturn("John");

        // Act
        String response = userService.createUser(user);

        // Assert
        assertEquals("User not added", response);
        verify(userDao, never()).createUser(any(User.class));
    }

    @Test
    public void testCreateUser_UserWithoutFirstName() {
        // Arrange
        when(user.getEmail()).thenReturn("test@example.com");

        // Act
        String response = userService.createUser(user);

        // Assert
        assertEquals("User not added", response);
        verify(userDao, never()).createUser(any(User.class));
    }

    @Test
    public void testCheckLogin_ValidCredentials() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        
        // Mock user
        User testUser = new User();
        testUser.setUserId(1L);
        testUser.setUserName("JohnDoe");

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setUserId(testUser.getUserId());
        expectedUserDto.setUserName(testUser.getUserName());

        when(userDao.login(email, password)).thenReturn(expectedUserDto); // Return the expected UserDto

        // Act
        UserDto userDto = userService.checkLogin(email, password);

        // Assert
        assertNotNull(userDto);
        assertEquals(1L, userDto.getUserId());
        assertEquals("JohnDoe", userDto.getUserName());
        verify(userDao, times(1)).login(email, password);
    }

    @Test
    public void testCheckLogin_InvalidCredentials() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongPassword";

        when(userDao.login(email, password)).thenReturn(new UserDto()); // Return empty UserDto for invalid login

        // Act
        UserDto userDto = userService.checkLogin(email, password);

        // Assert
        assertNotNull(userDto);
        assertEquals(0, userDto.getUserId());
        assertNull(userDto.getUserName());
        verify(userDao, times(1)).login(email, password);
    }

    @Test
    public void testCheckLogin_NullEmailOrPassword() {
        // Arrange
        String email = null;
        String password = "password123";

        // Act
        UserDto result = userService.checkLogin(email, password);

        // Assert
        assertNull(result); // Expect null response
        verify(userDao, never()).login(anyString(), anyString());
    }

}