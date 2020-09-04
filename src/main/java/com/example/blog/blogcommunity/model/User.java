package com.example.blog.blogcommunity.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.ACCOUNT_ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.NAME
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.TOKEN
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.GMT_CREATE
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.GMT_MODIFIED
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.bio
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.icon_url
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private String iconUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mailbox
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    private Integer mailbox;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.ID
     *
     * @return the value of user.ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.ID
     *
     * @param id the value for user.ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.ACCOUNT_ID
     *
     * @return the value of user.ACCOUNT_ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.ACCOUNT_ID
     *
     * @param accountId the value for user.ACCOUNT_ID
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.NAME
     *
     * @return the value of user.NAME
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.NAME
     *
     * @param name the value for user.NAME
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.TOKEN
     *
     * @return the value of user.TOKEN
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.TOKEN
     *
     * @param token the value for user.TOKEN
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.GMT_CREATE
     *
     * @return the value of user.GMT_CREATE
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.GMT_CREATE
     *
     * @param gmtCreate the value for user.GMT_CREATE
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.GMT_MODIFIED
     *
     * @return the value of user.GMT_MODIFIED
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.GMT_MODIFIED
     *
     * @param gmtModified the value for user.GMT_MODIFIED
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.bio
     *
     * @return the value of user.bio
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.bio
     *
     * @param bio the value for user.bio
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.icon_url
     *
     * @return the value of user.icon_url
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.icon_url
     *
     * @param iconUrl the value for user.icon_url
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mailbox
     *
     * @return the value of user.mailbox
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public Integer getMailbox() {
        return mailbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mailbox
     *
     * @param mailbox the value for user.mailbox
     *
     * @mbg.generated Fri Sep 04 11:59:16 GMT+08:00 2020
     */
    public void setMailbox(Integer mailbox) {
        this.mailbox = mailbox;
    }
}