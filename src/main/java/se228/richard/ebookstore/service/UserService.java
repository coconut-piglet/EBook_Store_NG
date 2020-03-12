package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Message;

public interface UserService {
    Message check_availability(String usernmae);
    Message verify_credential(String username, String userpwd);
    Message register_newuser(String username, String useremail, String userpwd);
    Message modify_user(String username, String useremail, String userpwd);
}
