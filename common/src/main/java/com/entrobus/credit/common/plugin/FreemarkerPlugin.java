
package com.entrobus.credit.common.plugin;

import com.entrobus.credit.common.util.FreemarkerUtil;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 *
 */
public class FreemarkerPlugin implements IPlugin {

    private FreeMarkerConfigurer configurer;

    @Override
    public void start() {
        FreemarkerUtil.init(configurer.getConfiguration());
    }

    public FreeMarkerConfigurer getConfigurer() {
        return configurer;
    }

    public void setConfigurer(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

}
