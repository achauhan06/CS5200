package edu.northeastern.cs5200.daos;

public interface RoleDao {

    void assignWebsiteRole(int developerId, int websiteId, int roleId);
    void assignPageRole(int developerId, int pageId, int roleId);
    void deleteWebsiteRole(int developerId, int websiteId, int roleId);
    void deletePageRole(int developerId, int pageId, int roleId);

    String findWebsiteRoles(int developerId, int websiteId);
    String findPageRoles(int developerId, int pageId);

    int updateWebsiteRole(int developerId, int websiteId, String role);
    int updatePageRole(int developerId, int pageId, String role);

}
