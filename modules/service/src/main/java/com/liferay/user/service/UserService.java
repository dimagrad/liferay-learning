package com.liferay.user.service;

import com.liferay.user.model.UserEntity;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dima
 */
@Component(
        immediate = true,
        service = UserService.class
)

public class UserService {

    public List<UserEntity> initUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity("Ivan", "Ivanov", "ivanivanov@ivan.iv"));
        users.add(new UserEntity("Ivan1", "Ivanov1", "ivanivanov1@ivan.iv"));
        users.add(new UserEntity("Ivan2", "Ivanov2", "ivanivanov2@ivan.iv"));
        return users;

    }
}