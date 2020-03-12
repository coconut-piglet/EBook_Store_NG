package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.AdminService;
import se228.richard.ebookstore.service.BookService;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/fetchuserinfo", method = RequestMethod.GET)
    public List<User> fetchUserList() {
        return adminService.fetchUserList();
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public Message activateUser(@RequestParam("userid") int userid) {
        return adminService.activateUser(userid);
    }

    @RequestMapping(value = "/disable", method = RequestMethod.GET)
    public Message disableUser(@RequestParam("userid") int userid) {
        return adminService.disableUser(userid);
    }

    @RequestMapping(value = "/modifybookdetail")
    @ResponseBody
    public Message modifyBookDetail(@RequestParam(name = "bookcover") MultipartFile file) {
        if (!file.isEmpty()) {
            String Sbookid = file.getOriginalFilename();
            int bookid = Integer.parseInt(Sbookid);
            try {
                byte[] bookcover = file.getBytes();
                BookDetail oldDetail = bookService.fetchBookDetail(bookid);
                if (oldDetail == null) {
                    BookDetail newDetail = new BookDetail(bookid,bookcover,"");
                    return adminService.modifyBookDetail(newDetail);
                }
                else {
                    oldDetail.setBookcover(bookcover);
                    adminService.deleteBookDetail(oldDetail.getBookid());
                    return adminService.modifyBookDetail(oldDetail);
                }
            } catch (Exception excp) {
                return new Message("ERROR","can not read file");
            }
        }
        else {
            return new Message("FAIL", "");
        }
    }

    @RequestMapping(value = "/modifybookdesc", method = RequestMethod.POST)
    public Message modifyBookDesc(@RequestBody BookDetail bookDetail) {
        String desc = bookDetail.getBookdescription();
        if (!desc.equals("")) {
            int bookid = bookDetail.getBookid();
            BookDetail oldDetail = bookService.fetchBookDetail(bookid);
            if (oldDetail == null) {
                byte[] blank = {0};
                BookDetail newDetail = new BookDetail(bookid,blank,bookDetail.getBookdescription());
                return adminService.modifyBookDetail(newDetail);
            }
            else {
                oldDetail.setBookdescription(bookDetail.getBookdescription());
                adminService.deleteBookDetail(oldDetail.getBookid());
                return adminService.modifyBookDetail(oldDetail);
            }
        }
        else {
            return new Message("FAIL", "");
        }
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.GET)
    public Message deleteBook(@RequestParam("bookid") int bookid) {
        return adminService.deleteBook(bookid);
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public Message addBook(@RequestBody Book book) {
        String bookisbn = book.getBookisbn();
        String bookname = book.getBookname();
        String bookauthor = book.getBookauthor();
        double bookprice = book.getBookprice();
        int bookstock = book.getBookstock();
        return adminService.addBook(bookisbn, bookname, bookauthor, bookprice, bookstock);
    }

    @RequestMapping(value = "/modifybook", method = RequestMethod.POST)
    public Message modifyBook(@RequestBody Book book) {
        int bookid = book.getBookid();
        String bookisbn = book.getBookisbn();
        String bookname = book.getBookname();
        String bookauthor = book.getBookauthor();
        double bookprice = book.getBookprice();
        int bookstock = book.getBookstock();
        return adminService.modifyBook(bookid, bookisbn, bookname, bookauthor, bookprice, bookstock);
    }

}
