package com.eomcs.util;

public class ArrayList {

  // 인스턴스 필드(변수)
  // => 인스턴스 필드는 new 명령을 통해 생성한다
  Object[] list = new Object[5];
  int size = 0;

  // 인스턴스 주소를 앞쪽에서 받으려면 static 키워드를 붙이면 안된다.
  // 즉 non-static 메서드로 정의해야 한다.
  // 그리고 메서드가 호출될 때 받은 인스턴스를 사용하려면 내장 변수 this를 이용해야 한다.

  // 기능:
  // - 배열에 항목을 추가한다.
  // - 배열이 꽉찼으면 배열의 크기를 늘린다.
  //
  public void add(Object contact) {
    if (this.size == this.list.length) { 
      this.list = this.grow(); 
    }
    this.list[this.size++] = contact;
  }

  // 기능:
  // - 배열의 크기를 늘린다. 기존 배열의 값을 복사해온다.
  //
  Object[] grow() {
    Object[] arr = new Object[this.newLength()];
    this.copy(arr);
    return arr;
  }

  // 기능:
  // - 주어진 배열에 대해 50% 증가시킨 새 배열의 길이를 알려준다.
  //
  int newLength() {
    return this.list.length + (this.list.length >> 1);
  }

  // 기능: 
  // - 배열을 복사한다.
  // 
  void copy(Object[] target) {
    int length = this.list.length;
    if (target.length < this.list.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) {
      target[i] = this.list[i];
    }
  }

  // 기능:
  // - 배열에 저장된 목록만 꺼내 새 배열에 담아 리턴한다.
  //
  public Object[] toArray() {
    Object[] arr = new Object[this.size]; 
    for (int i = 0; i < this.size; i++) { 
      arr[i] = this.list[i]; 
    }
    return arr; 
  }

  // 기능:
  // - 배열에서 지정한 항목을 삭제한다.
  //
  public Object remove(int index) {
    Object old = this.list[index];
    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.size--;
    return old;
  }

  public Object set(int index, Object obj ) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Object old = this.list[index];
    this.list[index] = obj;
    return old;
  }

  public int size() {
    return this.size;
  }

  public Object get(int index) {
    return this.list[index];
  }

}









