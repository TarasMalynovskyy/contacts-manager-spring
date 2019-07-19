package com.ivvysoft.cms.services;

import com.ivvysoft.cms.model.User;

public interface UserService {

    void save(User user);

    User findByUserName(String username);
    
    public String currentUser();
}
