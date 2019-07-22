package meambitoprofesia;

import java.util.*;

public class UserDetails {
    // Login Details
    private String email;
    private String password;
    private String name;
    private String surname;
    private String mobile;

    // DB Details
    private String host;
    private Integer port;
    private String dbName;
    private String collName;
    // Search related
    private Date lastUpdatedAt = new Date();
    private Set < Tag > tags = new HashSet<Tag>();
    // Each user should have a collection of their own w/t application IDs
    private Set < Applications > applications = new HashSet<Applications>();

    // empty constructor
    public UserDetails() {
    }

    public UserDetails(String email,
                       String password,
                       String name,
                       String surname,
                       String mobile,
                       String host,
                       Integer port,
                       String dbName,
                       String collName) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.collName = collName;
    }

    // setter && getters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public Integer getPort() { return port; }
    public void setPort(Integer port) { this.port = port; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getCollName() { return collName; }
    public void setCollName(String collName) { this.collName = collName; }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }
    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Set < Tag > getTags() {
        return tags;
    }
    public void setTags(Set < Tag > tags) {
        this.tags = tags;
    }

    public Set < Applications > getApplications() {
        return applications;
    }
    public void setApplications(Set < Applications > applications) {
        this.applications = applications;
    }

}
