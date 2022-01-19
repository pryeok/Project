package com.eomcs.mylist.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.mylist.domain.Todo;
import com.eomcs.util.ArrayList;

@RestController 
public class TodoController {

  ArrayList todoList;

  public TodoController() throws Exception {
    todoList = new ArrayList();
    System.out.println("TodoController() 호출됨!");

    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("todos.data")));

    while (true) { 
      try {
        Todo todo = new Todo();
        todo.setTitle(in.readUTF());
        todo.setDone(in.readBoolean());

        todoList.add(todo); // 파일에서 읽은 한 줄의 CSV 데이터로 객체를 만든 후 목록에 등록한다.
      } catch (Exception e) {
        break;
      }
    }

    in.close();
  }

  @RequestMapping("/todo/list")
  public Object list() {
    return todoList.toArray(); 
  }

  @RequestMapping("/todo/add")
  public Object add(Todo todo) {
    todoList.add(todo);
    return todoList.size();
  }

  @RequestMapping("/todo/update")
  public Object update(int index, Todo todo) {
    if (index < 0 || index >= todoList.size()) {
      return 0;
    }

    Todo old = (Todo) todoList.get(index);
    todo.setDone(old.isDone()); // 기존의 체크 정보를 그대로 가져가야 한다.

    return todoList.set(index, todo) == null ? 0 : 1;
  }

  @RequestMapping("/todo/check")
  public Object check(int index, boolean done) {
    if (index < 0 || index >= todoList.size()) {
      return 0;  // 인덱스가 무효해서 설정하지 못했다.
    }

    ((Todo) todoList.get(index)).setDone(done);
    return 1; // 해당 항목의 상태를 변경했다.
  }

  @RequestMapping("/todo/delete")
  public Object delete(int index) {
    if (index < 0 || index >= todoList.size()) {
      return 0;
    }

    todoList.remove(index); // 메서드 이름으로 코드의 의미를 짐작할 수 있다. 이것이 메서드로 분리하는 이유이다.
    return 1;
  }

  @RequestMapping("/todo/save")
  public Object save() throws Exception {

    // 데이터를 바이너리 형식으로 저장하기 위해 !!!
    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("todos.data")));

    Object[] arr = todoList.toArray();
    for (Object obj : arr) {
      Todo todo = (Todo) obj;
      out.writeUTF(todo.getTitle());
      out.writeBoolean(todo.isDone());

    }

    out.close();
    return arr.length;
  }
}







