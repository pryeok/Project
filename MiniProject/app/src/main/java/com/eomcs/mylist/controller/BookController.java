package com.eomcs.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Book;
import com.eomcs.util.ArrayList;

@RestController 
public class BookController {

  ArrayList bookList;

  public BookController() throws Exception {
    bookList = new ArrayList();
    System.out.println("BookController() 호출됨!");

    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("books.data")));

    while (true) { 
      try {
        Book book = new Book();
        book.setTitle(in.readUTF());
        book.setAuthor(in.readUTF());
        book.setPress(in.readUTF());
        book.setPage(in.readInt());
        book.setPrice(in.readInt());
        String date = in.readUTF();
        if (date.length() > 0) {
          book.setReadDate(Date.valueOf(date));
        }
        book.setFeed(in.readUTF());

        bookList.add(book); // 파일에서 읽은 한 줄의 CSV 데이터로 객체를 만든 후 목록에 등록한다.
      } catch (Exception e) {   //try 돌려보다가 예외가 catch(발견)되었을 때 break !!
        break;
      }

    }

    in.close();
  }

  @RequestMapping("/book/list")
  public Object list() {
    return bookList.toArray(); 
  }

  @RequestMapping("/book/add")
  public Object add(Book book) {
    bookList.add(book);
    return bookList.size();
  }


  @RequestMapping("/book/get")
  public Object get(int index) {
    if (index < 0 || index >= bookList.size()) {
      return "";
    }
    return bookList.get(index);
  }

  @RequestMapping("/book/update")
  public Object update(int index, Book book) {
    if (index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.set(index, book) == null ? 0 : 1;
  }

  @RequestMapping("/book/delete")
  public Object delete(int index) {
    if (index < 0 || index >= bookList.size()) {
      return 0;
    }
    return bookList.remove(index) == null ? 0 : 1;
  }

  @RequestMapping("/book/save")
  public Object save() throws Exception {

    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("books.data")));

    Object[] arr = bookList.toArray();
    for (Object obj : arr) {
      Book book = (Book) obj;
      out.writeUTF(book.getTitle());
      out.writeUTF(book.getAuthor());
      out.writeUTF(book.getPress());
      out.writeInt(book.getPage());
      out.writeInt(book.getPrice());
      if (book.getReadDate() == null) {
        out.writeUTF("");
      } else {
        out.writeUTF(book.getReadDate().toString());
      }
      out.writeUTF(book.getFeed());

    }


    out.close();
    return arr.length;
  }
}





