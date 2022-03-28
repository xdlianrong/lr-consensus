package com.lrtech.consensus.fisco;

import com.lrtech.consensus.BasicController;
import com.lrtech.consensus.entity.ResponseEntity;
import com.lrtech.consensus.validation.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/fisco")
@RestController
public class FiscoController extends BasicController {

    public FiscoService fiscoService;

    @Autowired
    public void setFiscoService(FiscoService fiscoService) {
        this.fiscoService = fiscoService;
    }

    @GetMapping("/query/all")
    public ResponseEntity findAll() {
        String result = fiscoService.getService1();
        return new ResponseEntity(Violation.SUCCESS, result);
    }

    @GetMapping("/query/anyone/{userId}/item/submissionTime={submissionTime}")
    public ResponseEntity anyoneItem(@PathVariable Long userId, @PathVariable String submissionTime) {
        return new ResponseEntity(Violation.SUCCESS);
    }

    @PostMapping("/register/one")
    public ResponseEntity register(@RequestBody Object obj) {
        return new ResponseEntity(Violation.SUCCESS);
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestParam String oldPassword,
                                         @RequestParam String newPassword, HttpServletRequest request) {
        return new ResponseEntity(Violation.SUCCESS);
    }
}