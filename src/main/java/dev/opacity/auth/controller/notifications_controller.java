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

@RequestMapping("/notify")
@RestController
public class notifications_controller {
    
    @Autowired
    private lKeyRepository keyRepo;
    
    @RequestMapping(value="/gen/{owner_id}/{product_id}", method = RequestMethod.GET)
    String generate(HttpServletRequest request,@PathVariable("owner_id") int owner_id,@PathVariable("product_id") int product_id) {    
        lkey new_lkey = new lkey(owner_id,product_id);
        keyRepo.save(new_lkey);
        return "Generated successfully<br>" + new_lkey.toString();
    }

    @RequestMapping(value="/bind/{keyid}/{domainName}", method = RequestMethod.GET)
    String bind(HttpServletRequest request,@PathVariable("keyid") String key,@PathVariable("domainName") String domainName) {
        List<lkey> res = keyRepo.findByContent(key);
        if (res.size() != 1)
            return "License key unrecognized";
        lkey target = res.get(0);
        target.setDomainName(domainName);        
        keyRepo.save(target);
        return "License key has been bound successfully ! <br> " + target.toString() ;
    }

}