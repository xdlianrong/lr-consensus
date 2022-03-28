package com.lrtech.consensus.user;

import com.lrtech.consensus.BasicController;
import com.lrtech.consensus.config.operators.OperatorSimpleFactory;
import com.lrtech.consensus.entity.ResponseEntity;
import com.lrtech.consensus.validation.Violation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RequestMapping("/current")
@RestController
public class UserDetailsController extends BasicController {

    @GetMapping("/user-details")
    public ResponseEntity userDetails(HttpServletRequest request) {
        UsernameEncoder usernameEncoder = OperatorSimpleFactory.usernameEncoderInstance();
        Authentication a = authentication();
        String username = usernameEncoder.decode(a.getName());

        List<String> roles = new ArrayList<>();
        a.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
            @Override
            public void accept(GrantedAuthority grantedAuthority) {
                roles.add(grantedAuthority.getAuthority());
            }
        });

        String name = username;
        return new ResponseEntity(Violation.SUCCESS, new ResponseEntity.UserDetails(username, name, roles));
    }
}