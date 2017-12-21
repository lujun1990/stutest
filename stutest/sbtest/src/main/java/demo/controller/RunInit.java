package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
public class RunInit {

    @Autowired
    private Environment env;

    public String getProjectVersion(){
        if(env != null){
            String description = env.getProperty("spring.application.description");
            String version = env.getProperty("spring.application.version");
            System.out.print(description + version);
            return description + version;
        }
        return null;
    }
}
