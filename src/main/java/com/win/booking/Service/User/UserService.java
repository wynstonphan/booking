package com.win.booking.Service.User;

import com.win.booking.Payload.Request.RegisterRequest;
import com.win.booking.Model.User;

public interface UserService {
    void saveUser(RegisterRequest registerRequest);

    User findByUsername(String username);
}
