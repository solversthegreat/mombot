package com.cs.nlp.controller;

import com.cs.nlp.model.ActionItem;
import com.cs.nlp.model.NlpActionItemReq;
import com.cs.nlp.service.CoreNlpEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/nlp")
public class NlpDataController {

    @Autowired
    CoreNlpEvaluator coreNlpEvaluator;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/getActionItem",method = RequestMethod.POST)
    public Map<String,String> getActionItemForChatContent(@RequestBody String text){
        return coreNlpEvaluator.getActionItems(text);
    }

}
