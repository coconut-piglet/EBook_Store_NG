package se228.richard.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se228.richard.ebookstore.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findAdminByAdminname(String adminname);
    Admin findAdminByAdminnameAndAdminpwd(String adminname, String adminpwd);
}
