package dev.opacity.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.opacity.auth.lib.lKeyRepository;
import dev.opacity.auth.lib.lkey;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/")
@RestController
public class direct_auth_controller {
    
    @Autowired
    private lKeyRepository keyRepo;
    @RequestMapping(value="/{keyid}", method = RequestMethod.GET)
    String authenticate(HttpServletRequest request,@PathVariable("keyid") String key) {   
        List<lkey> target = keyRepo.findByContent(key);
        if (target.size() < 0)
            return "License key unrecognized";
        if (request.getHeader("host").equals(target.get(0).getDomainName()))
            return "Authentication succeeded!";
        else
            return "Authentication failed!";
    }

}
