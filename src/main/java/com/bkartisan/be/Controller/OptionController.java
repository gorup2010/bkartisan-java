package com.bkartisan.be.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Entity.Option;
import com.bkartisan.be.Service.OptionService;


@RestController
@RequestMapping("/options")
public class OptionController {
    
    private OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/") 
    public ResponseEntity<List<Option>> getRootOptions() {
        return ResponseEntity.ok(optionService.getRootOptions());
    }

    @GetMapping("/{parentOptionName}/children")
    public ResponseEntity<List<String>> getNamesOfChildOptions(@PathVariable String parentOptionName) {
        return ResponseEntity.ok(optionService.getNamesOfChildOptions(parentOptionName));
    }
}
