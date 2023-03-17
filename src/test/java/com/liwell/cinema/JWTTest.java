package com.liwell.cinema;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/17
 */
@SpringBootTest
@ActiveProfiles("local")
public class JWTTest {

    @Test
    public void testCreate() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 12);
        map.put("expire_time", System.currentTimeMillis() + 60 * 60 * 1000);
        String jwtToken = JWTUtil.createToken(map, "123456".getBytes());
        System.out.println(jwtToken);
    }

    @Test
    public void testParse() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHBpcmVfdGltZSI6MTY3OTA0MTM2MTYyMiwidXNlcklkIjoxMn0.02NI3JMRskYmNg6eHtMoWkBJ-VN_spBvY7rE9FEQjVI";
        JWT jwt = JWTUtil.parseToken(token);
        System.out.println(jwt.getPayload("userId"));
    }

}
