package dev.opacity.auth.dev.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RequestMapping("/auth")
@RestController
public class REST {
    
    @Autowired
    private lKeyRepository keyRepo;

    @RequestMapping(value="/{keyid}", method = RequestMethod.GET)
    String authenticate(HttpServletRequest request,@PathVariable("keyid") String key) {   
        lkey target = keyRepo.findByContent(key).get(0);        
        if (request.getHeader("host").equals(target.getDomainName()))
            return "Authentication succeeded!";
        else
            return "Authentication failed!";
    }

    @RequestMapping(value="/gen/{owner_id}/{product_id}/", method = RequestMethod.GET)
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