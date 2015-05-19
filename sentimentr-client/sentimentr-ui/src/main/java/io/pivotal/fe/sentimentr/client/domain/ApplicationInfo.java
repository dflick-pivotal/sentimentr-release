package io.pivotal.fe.sentimentr.client.domain;


public class ApplicationInfo {
    private String[] profiles;
    private String[] services;
    private String ip;
    private int port;
 
    public ApplicationInfo(String[] profiles, String[] services, String ip, int port) {
        this.profiles = profiles;
        this.services = services;
        this.ip = ip;
        this.port = port;
    }

    public String[] getProfiles() {
        return profiles;
    }

    public void setProfiles(String[] profiles) {
        this.profiles = profiles;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

	public String getIp() {
		return ip;
	}
 
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}    
}