package top.cyanide.cyanide.config;

import top.cyanide.cyanide.model.tag.ArticleTagDirective;
import top.cyanide.cyanide.model.tag.CommonTagDirective;
import top.cyanide.cyanide.service.OptionsService;
import top.cyanide.cyanide.service.UserService;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author : RYAN0UP
 * @version : 1.0
 * @date : 2018/4/26
 */
@Slf4j
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonTagDirective commonTagDirective;

    @Autowired
    private ArticleTagDirective articleTagDirective;

    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("commonTag",commonTagDirective);
        configuration.setSharedVariable("articleTag",articleTagDirective);
        try{
            configuration.setSharedVariable("options",optionsService.findAllOptions());
            configuration.setSharedVariable("user",userService.findUser());
        }catch (TemplateModelException e){
            e.printStackTrace();
        }
    }
}
