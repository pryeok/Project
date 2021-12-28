package com.eomcs.mylist;

public class ArrayList {

  static Object[] list = new Object[5];
  static int size = 0;

  // 기능:
  // - 배열에 항목을 추가한다.
  // - 배열이 꽉찼으면 배열의 크기를 늘린다.
  //
  static void add(Object contact) {
    if (size == list.length) { 
      list = grow(); 
    }
    list[size++] = contact;
  }

  // 기능:
  // - 배열의 크기를 늘린다. 기존 배열의 값을 복사해온다.
  //
  static Object[] grow() {
    Object[] arr = new Object[newLength()];
    copy(list, arr);
    return arr;
  }

  // 기능:
  // - 주어진 배열에 대해 50% 증가시킨 새 배열의 길이를 알려준다.
  //
  static int newLength() {
    return list.length + (list.length >> 1);
  }

  // 기능: 
  // - 배열을 복사한다.
  // 
  static void copy(Object[] source, Object[] target) {
    int length = source.length;
    if (target.length < source.length) {
      length = target.length;
    }
    for (int i = 0; i < length; i++) {
      target[i] = source[i];
    }
  }

  // 기능:
  // - 배열에 저장된 목록만 꺼내 새 배열에 담아 리턴한다.
  //
  static Object[] toArray() {
    Object[] arr = new Object[size]; 
    for (int i = 0; i < size; i++) { 
      arr[i] = list[i]; 
    }
    return arr; 
  }

  // 기능:
  // - 배열에서 지정한 항목을 삭제한다.
  //
  static Object remove(int index) {
    Object old = list[index];
    for (int i = index + 1; i < size; i++) {
      list[i - 1] = list[i];
    }
    size--;
    return old;
  }

  static Object set(int index, Object obj ) {
    if (index < 0 || index >= size) {
      return null;
    }
    Object old = list[index];
    list[index] = obj;
    return old;
  }
}









