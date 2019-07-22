package meambitoprofesia;

/*class created for the purpose of storing domain specific xpath attributes */

public class Domain {
    String url;
    String loginUrl;
    String email;
    String password;
    String loginButton;
    String nextPage;
    String previousPage;
    String href;
    String search;
    String searchButton;
    String apply;
    String appliedWarning;

    public Domain() {
    }

    public Domain(
            String url,
            String loginUrl,
            String email,
            String password,
            String loginButton,
            String nextPage,
            String previousPage,
            String href,
            String search,
            String apply,
            String appliedWarning) {
        super();
        this.url = url;
        this.loginUrl = loginUrl;
        this.email = email;
        this.loginButton = loginButton;
        this.password = password;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.href = href;
        this.search = search;
        this.apply = apply;
        this.appliedWarning = appliedWarning;
    }

    // setters && getters
    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; }

    public String getLoginUrl() { return loginUrl; }
    public void setLoginUrl(String loginUrl) { this.loginUrl = loginUrl; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public String getLoginButton() { return this.loginButton; }
    public void setLoginButton(String loginButton) { this.loginButton = loginButton; }

    public String getNextPage() { return this.nextPage; }
    public void setNextPage(String nextPage) { this.nextPage = nextPage; }

    public String getPreviousPage() { return this.previousPage; }
    public void setPreviousPage(String previousPage) { this.previousPage = previousPage; }

    public String getHref() { return this.href; }
    public void setHref(String href) { this.href = href; }

    public String getSearch() { return this.search; }
    public void setSearch(String search) { this.search = search; }

    public String getSearchButton() { return searchButton; }
    public void setSearchButton(String searchButton) { this.searchButton = searchButton; }

    public String getApply() { return this.apply; }
    public void setApply(String apply) { this.apply = apply; }

    public String getAppliedWarning() { return this.appliedWarning; }
    public void setAppliedWarning(String appliedWarning) { this.appliedWarning = appliedWarning; }

}
