package com.example.goo.carrotmarket.Util.GlobalBus;

/**
 * Created by Goo on 2019-05-15.
 */

public class Events {

    //검색 액티비티에서의 검색 쿼리 값이 주어지면, 세개의 뷰페이저에 있는 프래그먼트에 그 값을 전달
    public static class Event1 {

        private String message;

        public Event1(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    //필터화면에서 홈화면으로 뒤로가기 했을 때
    public static class BackToHomeFromFilter {


    }

    //내지역설정 화면에서 홈화면으로 뒤로가기 했을 때
    public static class BackToHomeFromSetMyLocation {


    }

}