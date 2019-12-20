package day14.copy;
import java.util.*;
class HashMapExample2 {
    public static void main(String args[]) {
        HashMap<Name, Integer> hm = 
                                new HashMap<Name, Integer>();
        hm.put(new Name("해리", "포터"), new Integer(95));
        hm.put(new Name("헤르미온느", "그레인져"), new Integer(100));	//요
        hm.put(new Name("론", "위즐리"), new Integer(85));
        hm.put(new Name("드레이코", "말포이"), new Integer(93));
        hm.put(new Name("네빌", "롱버텀"), new Integer(70));
        hm.put(new Name("헤르미온느", "그레인져"), new Integer(55));
        //두개를 동일객체로 보지 않는다. Object의 참조값을 보기때문에 각각 다른 값이다.
        //따라서 equals 와 hasCode를 오버라이딩 해줘야 한다.
        System.out.println(hm);
    }
}
