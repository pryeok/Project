package com.eomcs.mylist;

import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class BoardController {

  // Contact 객체 목록을 저장할 메모리 준비
  // => Object[] list = new Object[5];
  // => int size = 0;
  ArrayList boardList = new ArrayList();

  @RequestMapping("/board/list")
  public Object list() {
    return ArrayList.toArray(boardList);
  }

  @RequestMapping("/board/add")
  public Object add(Board board) {

    board.setCreatedDate(new Date(System.currentTimeMillis()));
    ArrayList.add(boardList, board);
    return boardList.size;
  }


  @RequestMapping("/board/get")
  public Object get(int index) {
    if (index < 0 || index >= boardList.size) {
      return "";
    }
    Board board = (Board) boardList.list[index];
    board.viewCount++;
    return board;
  }

  @RequestMapping("/board/update")
  public Object update(int index, Board board) {
    if (index < 0 || index >= boardList.size) {
      return 0;
    }

    Board old = (Board) boardList.list[index];
    board.viewCount = old.viewCount;
    board.createdDate = old.createdDate;

    return ArrayList.set(boardList, index, board) == null ? 0 : 1;
  }

  @RequestMapping("/board/delete")
  public Object delete(int index) {
    if (index < 0 || index >= boardList.size) {
      return 0;
    }

    ArrayList.remove(boardList, index);
    return 1;
  }

}


