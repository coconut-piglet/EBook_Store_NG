package se228.richard.ebookstore.dao;

import se228.richard.ebookstore.entity.Admin;

public interface AdminDao {
    Admin findAdminByAdminname(String adminname);
    Admin findAdminByAdminnameAndAdminpwd(String adminname, String adminpwd);
}
