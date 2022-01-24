package com.eomcs.mylist.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Contact;
import com.eomcs.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController 
public class ContactController {

  ArrayList contactList;

  public ContactController() throws Exception {
    contactList = new ArrayList();
    System.out.println("ContactController() 호출됨!");

    try {
      BufferedReader in = new BufferedReader(new FileReader("contacts.json"));

      // JSON 문자열을 다룰 객체 준비
      ObjectMapper mapper = new ObjectMapper();

      // 1) JSON 파일에서 문자열을 읽어온다
      // => 읽어 온 문자열은 배열 형식이다
      String jsonStr = in.readLine();

      // 2) JSON 문자열을 가지고 자바 객체를 생성한다
      // => 배열 형식의 JSON 문자열에서 Board의 배열 객체를 생성한다
      Contact[] contacts = mapper.readValue(jsonStr, Contact[].class);

      // 3) 배열 객체를 ArrayList 에 저장한다
      //      for (Contact contact : contacts) {
      //        contactList.add(contact);
      //      }
      // => 다음과 같이 addALl()을 호출하여 배열을 목록에 추가할 수 있다
      //      contactList.addAll(contacts);

      // => 다음과 같이 생성자를 통해 배열을 목록에 추가할 수 있다
      contactList = new ArrayList(contacts);

      in.close();

    } catch (Exception e) {
      System.out.println("연락처 데이터를 로딩하는 중 오류 발생!");
    }
  }

  @RequestMapping("/contact/list")
  public Object list() {
    return contactList.toArray(); 
  }

  @RequestMapping("/contact/add")
  public Object add(Contact contact) {
    //    System.out.println(contact);
    contactList.add(contact);
    return contactList.size();
  }


  @RequestMapping("/contact/get")
  public Object get(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return "";
    }
    return contactList.get(index);
  }

  @RequestMapping("/contact/update")
  public Object update(Contact contact) {
    int index = indexOf(contact.getEmail());
    if (index == -1) {
      return 0;
    }

    return contactList.set(index, contact) == null ? 0 : 1;
  }

  @RequestMapping("/contact/delete")
  public Object delete(String email) {
    int index = indexOf(email);
    if (index == -1) {
      return 0;
    }

    contactList.remove(index);
    return 1;
  }

  @RequestMapping("/contact/save")
  public Object save() throws Exception {

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contacts.json")));

    // JSON 형식의 문자열을 다룰 객체를 준비한다
    ObjectMapper mapper = new ObjectMapper();

    // 1) 객체를 JSON 형식의 문자열로 생성한다
    // => ArrayList 에서 Board 배열을 꺼낸 후 JSON 문자열로 만든다
    String jsonStr = mapper.writeValueAsString(contactList.toArray());

    // 2) JSON 형식으로 바꾼 문자열을 파일로 출력한다
    out.println(jsonStr);

    out.close();
    return contactList.size();
  }

  int indexOf(String email) {
    for (int i = 0; i < contactList.size(); i++) {
      Contact contact =  (Contact) contactList.get(i);
      if (contact.getEmail().equals(email)) { 
        return i;
      }
    }
    return -1;
  }
}






