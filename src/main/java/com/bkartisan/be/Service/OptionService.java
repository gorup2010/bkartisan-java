package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Option;
import com.bkartisan.be.Repository.OptionRepository;

@Service
public class OptionService {
    
    private OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> getRootOptions() {
        return optionRepository.findByParentOptionOptionId(0);
    }

    public List<Option> getChildOptions(String parentOptionName) {
        return optionRepository.findChildOptionsByOptionName(parentOptionName);
    }

}
