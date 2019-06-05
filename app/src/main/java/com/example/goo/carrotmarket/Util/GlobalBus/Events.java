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

    //상세보기 화면에서 홈화면으로 돌아왔을 때, 특정 게시글 새로고침 해주기
    public static class BackToHomeFromDetail {

        private int position, product_id;

        public BackToHomeFromDetail(int position, int product_id) {
            this.position = position;
            this.product_id = product_id;
        }

        public int getPosition() {
            return position;
        }

        public int getProduct_id() {return product_id; }

    }


    //상세보기 화면에서 내 물건 판매목록으로 돌아왔을 때, 특정 게시글 새로고침 해주기
    public static class BackToSellingFromDetail {

        private int position, product_id;

        public BackToSellingFromDetail(int position, int product_id) {
            this.position = position;
            this.product_id = product_id;
        }

        public int getPosition() {
            return position;
        }

        public int getProduct_id() {return product_id; }

    }


    //상세보기 화면에서 거래완료 화면으로 돌아왔을 때, 특정 게시글 새로고침 해주기
    public static class BackToCompleteFromDetail {

        private int position, product_id;

        public BackToCompleteFromDetail(int position, int product_id) {
            this.position = position;
            this.product_id = product_id;
        }

        public int getPosition() {
            return position;
        }

        public int getProduct_id() {return product_id; }

    }


    //상세보기 화면에서 숨김화면으로 돌아왔을 때, 특정 게시글 새로고침 해주기
    public static class BackToHideFromDetail {

        private int position, product_id;

        public BackToHideFromDetail(int position, int product_id) {
            this.position = position;
            this.product_id = product_id;
        }

        public int getPosition() {
            return position;
        }

        public int getProduct_id() {return product_id; }

    }
}