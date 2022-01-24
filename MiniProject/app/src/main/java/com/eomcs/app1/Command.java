package com.eomcs.app1;

import java.util.ArrayList;

public class Command {
  String name;  // 명령어 이름 ex) add
  ArrayList params = new ArrayList();  // 명령을 저장하는 값 ex) 100 200

  public Command(String name, String[] values) {
    this.name = name;
    for (String value : values) {
      this.params.add(value);
    }
  }

  public String getName() {
    return this.name;
  }

  public String getString(int paramIndex) {
    return (String) this.params.get(paramIndex);
  }

  public int getInt(int paramIndex) {
    return Integer.parseInt((String) this.params.get(paramIndex));
  }

  public int getParamSize() {
    return this.params.size();
  }

}
