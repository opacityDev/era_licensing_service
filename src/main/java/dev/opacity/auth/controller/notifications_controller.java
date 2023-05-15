package dev.opacity.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.opacity.auth.lib.lKeyRepository;
import dev.opacity.auth.lib.lkey;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/notify")
@RestController
public class notifications_controller {
    
    @Autowired
    private lKeyRepository keyRepo;

    @RequestMapping(value="/", method = RequestMethod.GET)
    String hello(HttpServletRequest request,@PathVariable("owner_id") int owner_id,@PathVariable("product_id") int product_id) {    

        return "Generated successfully";
    }
    
    @RequestMapping(value="/gen/{owner_id}/{product_id}", method = RequestMethod.GET)
    String generate(HttpServletRequest request,@PathVariable("owner_id") int owner_id,@PathVariable("product_id") int product_id) {    
        lkey new_lkey = new lkey(owner_id,request.getHeader("host"));
        keyRepo.save(new_lkey);
        return "Generated successfully";
    }

    @RequestMapping(value="/bind/{keyid}/{domainName}", method = RequestMethod.GET)
    String bind(HttpServletRequest request,@PathVariable("keyid") String key,@PathVariable("domainName") String domainName) { 
        
        lkey target = keyRepo.findByContent(key).get(0);
        target.setDomainName(domainName);        
        keyRepo.save(target);
        if (request.getHeader("host").equals(target.getDomainName()))
            return "Authentication succeeded!";
        else
            return "Authentication failed!";
 
    }

}