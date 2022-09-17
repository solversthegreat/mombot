package com.code.solvers.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class HtmlEmailBuilder {
 
	@Autowired
    private TemplateEngine templateEngine;
 
    @Autowired
    public HtmlEmailBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    public String build(String template, Context context) {
        return templateEngine.process(template, context);
    }
}