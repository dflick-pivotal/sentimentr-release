package io.pivotal.fe.sentimentr.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.pivotal.fe.sentimentr.client.domain.ApplicationInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InfoController {
    @Autowired(required = false)
    private Cloud cloud;

    private Environment springEnvironment;

    @Autowired
    public InfoController(Environment springEnvironment) {
        this.springEnvironment = springEnvironment;
    }

    @RequestMapping(value = "/appinfo")
    public @ResponseBody ApplicationInfo info(HttpServletRequest request) {
    	ApplicationInfo ai = new ApplicationInfo(springEnvironment.getActiveProfiles(), getServiceNames(), request.getLocalAddr(), request.getLocalPort());
    	return ai;
    }

    @RequestMapping(value = "/env")
    @ResponseBody
    public Map<String, String> showEnvironment() {
        return System.getenv();
    }

    @RequestMapping(value = "/service")
    @ResponseBody
    public List<ServiceInfo> showServiceInfo() {
        if (cloud != null) {
            return cloud.getServiceInfos();
        } else {
            return new ArrayList<ServiceInfo>();
        }
    }
 
    private String[] getServiceNames() {
        if (cloud != null) {
            final List<ServiceInfo> serviceInfos = cloud.getServiceInfos();

            List<String> names = new ArrayList<String>();
            for (ServiceInfo serviceInfo : serviceInfos) {
                names.add(serviceInfo.getId());
            }
            return names.toArray(new String[names.size()]);
        } else {
            return new String[]{};
        }
    }    
}