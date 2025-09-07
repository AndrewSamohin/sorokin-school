package org.schoolsorokin.javacore.testing.users;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Test
    void testSavedUser() {
        UserRepository userRepositoryMock = mock(UserRepository.class);
        User dummyUser = new User(1, "Alice");
        when(userRepositoryMock.saveUser(dummyUser)).thenReturn(dummyUser);

        UserService userService = new UserService(userRepositoryMock);

        String userName = userService.saveUser(dummyUser);

        assertEquals("Alice saved!", userName);

        verify(userRepositoryMock, times(1)).saveUser(dummyUser);
    }
}
