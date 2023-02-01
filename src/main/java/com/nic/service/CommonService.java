package com.nic.service;


import com.nic.jwtconfig.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class CommonService {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<UserInfo> findUserByName(String name) {
        try {
            String qry = "select citizen_id,citizen_pwd from scanuser.user_profile where citizen_id=?";
            return jdbcTemplate.query(qry, new Object[]{name}, rs -> {
                if (rs.next()) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUsername(rs.getString(1));
                    userInfo.setPassword(new BCryptPasswordEncoder().encode(rs.getString(2)));
                    Optional<UserInfo> optionalUserInfo = Optional.of(userInfo);
                    return optionalUserInfo;
                }
                return Optional.empty();
            });
        } catch (Exception e) {
            System.out.println("Exception in findUserByName(String name) {} : " + e+name+" |||| "+e.getStackTrace()[0]);
            return Optional.empty();
        }
    }
}
