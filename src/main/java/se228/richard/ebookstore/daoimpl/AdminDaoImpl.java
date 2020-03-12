package se228.richard.ebookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se228.richard.ebookstore.dao.AdminDao;
import se228.richard.ebookstore.entity.Admin;
import se228.richard.ebookstore.repository.AdminRepo;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public Admin findAdminByAdminname(String adminname) {
        return adminRepo.findAdminByAdminname(adminname);
    }

    @Override
    public Admin findAdminByAdminnameAndAdminpwd(String adminname, String adminpwd) {
        return adminRepo.findAdminByAdminnameAndAdminpwd(adminname, adminpwd);
    }

}
