package com.example.baidu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

@RestController
public class BaiduController {

    @Autowired
private RestTemplate restTemplate;
    private String ak="ioPQ4NBLFIRYrODl0V7fOiTYCVsTKanF";

    @GetMapping("/get_addr")
    public Object getAddrUserIP(HttpServletRequest request) throws Exception {
        String ip=request.getRemoteHost();
        System.out.println(ip);
        ip="10.12.68.149";
        String url="https://api.map.baidu.com/location/ip?ak="+ak+"&ip="+ip+"&coor=bd09ll";

        //向百度地图发送请求

        ResponseEntity<Map> forEntity = restTemplate.getForEntity(new URI(url), Map.class);
        Map body = forEntity.getBody();
        return body;
    }
}
