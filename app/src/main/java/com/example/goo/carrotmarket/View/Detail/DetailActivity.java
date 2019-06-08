package com.example.goo.carrotmarket.View.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.goo.carrotmarket.Model.Chat;
import com.example.goo.carrotmarket.Model.Product;
import com.example.goo.carrotmarket.Model.UserInfo;
import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.GlobalBus.Events;
import com.example.goo.carrotmarket.Util.GlobalBus.GlobalBus;
import com.example.goo.carrotmarket.Util.SessionManager;
import com.example.goo.carrotmarket.View.Chat.ChatRoom.ChatRoomActivity;
import com.example.goo.carrotmarket.View.Detail.BottomSheet.BottomSheetDialog;
import com.example.goo.carrotmarket.View.Detail.ChatList.ChatListActivity;
import com.example.goo.carrotmarket.View.Detail.Reply.ReplyActivity;
import com.example.goo.carrotmarket.View.Detail.SelectBuyer.SelectBuyerActivity;
import com.example.goo.carrotmarket.View.Seller.SellerProducts.SellerActivity;
import com.example.goo.carrotmarket.View.Seller.SellerProfile.SellerProfileActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Goo on 2019-04-26.
 */

public class DetailActivity extends AppCompatActivity implements DetailView, View.OnClickListener, BottomSheetDialog.BottomSheetListener {

    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.relative_image)
    RelativeLayout relative_image;
    @BindView(R.id.profileImg)
    CircleImageView profileImg;
    @BindView(R.id.nick)
    TextView nick;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.relative_profile)
    RelativeLayout relative_profile;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.chatCount)
    TextView chatCount;
    @BindView(R.id.likeCount)
    TextView likeCount;
    @BindView(R.id.lookCount)
    TextView lookCount;
    @BindView(R.id.relative_report)
    RelativeLayout relative_report;
    @BindView(R.id.relative_reply)
    RelativeLayout relative_reply;
    @BindView(R.id.replyCount)
    TextView replyCount;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.relative_products)
    RelativeLayout relative_products;
    @BindView(R.id.recyclerView_products)
    RecyclerView recyclerView_products;
    @BindView(R.id.products)
    TextView txtProducts;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.txt_reply)
    TextView txt_reply;
    @BindView(R.id.priceNego)
    TextView priceNego;
    @BindView(R.id.priceNonNego)
    TextView priceNonNego;
    @BindView(R.id.txt_showAll)
    TextView txt_showAll;
    @BindView(R.id.like_unchecked)
    ImageView like_unchecked;
    @BindView(R.id.like_checked)
    ImageView like_checked;
    @BindView(R.id.cardview_chat)
    CardView cardview_chat;
    @BindView(R.id.startChat)
    TextView startChat;

    //bottom sheet dialog
    @BindView(R.id.relative_state)
    RelativeLayout relative_state;
    @BindView(R.id.cardview_selling)
    CardView cardview_selling;
    @BindView(R.id.cardview_reserving)
    CardView cardview_reserving;
    @BindView(R.id.cardview_deal_complete)
    CardView cardview_deal_complete;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.SliderDots)
    LinearLayout SliderDots;


    private List<Product> product;
    private List<Product> sellingProducts;
    private List<Product> productLike;
    private List<Chat> chat;

    private List<UserInfo> userinfo;
    private DetailPresenter presenter;
    Intent intent;

    private DetailImageSlider mViewPager;
    private int dotsCount;
    private ImageView[] dots;

    //private List<SliderImages> sliderImagesList;
    boolean refresh;

    int hide;

    int id;
    int position;
    String seller;

    String fragment;
    String myNick;
    String chatRoomId;
    String userPartner;

    //좋아요 눌렀는지 안 눌렀는지 판단
    boolean flag;

    SessionManager sessionManager;
    HashMap<String, String> user;

    DetailSellerProductsAdapter adapter;
    DetailSellerProductsAdapter.ItemClickListener itemClickListener;


    Events.BackToHomeFromDetail backToHomeFromDetail;
    Events.BackToSellingFromDetail backToSellingFromDetail;
    Events.BackToCompleteFromDetail backToCompleteFromDetail;
    Events.BackToHideFromDetail backToHideFromDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setToolbar();

        intent = getIntent();

        recyclerView_products.setLayoutManager(new LinearLayoutManager(this));
        id = Integer.parseInt(intent.getStringExtra("id"));  //해당 게시글 불러올 id 값
        seller = intent.getStringExtra("seller");
        hide = intent.getIntExtra("hide", 0);
        position = intent.getIntExtra("position", 0);

        refresh = false;

        flag = false;

        //세션
        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetail();
        myNick = user.get(sessionManager.NICK).toString();

        //OttoBus 이벤트 객체 생성
        backToHomeFromDetail = new Events.BackToHomeFromDetail(position, id);
        backToSellingFromDetail = new Events.BackToSellingFromDetail(position, id);
        backToCompleteFromDetail = new Events.BackToCompleteFromDetail(position, id);
        backToHideFromDetail = new Events.BackToHideFromDetail(position, id);
        //프레젠터 이벤트
        initPresenter();

        //버튼 이벤트
        initButton();

        //리사이클러뷰 아이템 클릭 이벤트
        setItemClickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //툴바 설정
    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바에 타이틀 적지 않기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }


    //버튼 이벤트
    public void initButton() {
        txt_showAll.setOnClickListener(this);

        relative_profile.setOnClickListener(this);

        like_checked.setOnClickListener(this);

        like_unchecked.setOnClickListener(this);

        //댓글작성
        if (sessionManager.isLoggIn() == true) {
            txt_reply.setOnClickListener(this);
        } else {
            presenter.showLoginDialog(this);
        }

        //판매중,예약중, 거래완료 상태 변경
        if (sessionManager.isLoggIn() == true && seller.equals(myNick)) {
            relative_state.setVisibility(View.VISIBLE);
            cardview_selling.setOnClickListener(this);
            cardview_reserving.setOnClickListener(this);
            cardview_deal_complete.setOnClickListener(this);
        }

        cardview_chat.setOnClickListener(this);


    }

    //프레젠터
    public void initPresenter() {

        presenter = new DetailPresenter(this);

        //로그인 되어 있을 떄만 해당 글을 관심목록에 담을 수 있음.
        if (sessionManager.isLoggIn()) {

            presenter.getLikeStates(myNick, id);
            presenter.getData(id, refresh);
            presenter.getSellerProducts(seller, refresh, id);
            presenter.getSellerProfile(seller);
            presenter.getChatRoom(myNick, id);
        } else {
            presenter.getData(id, refresh);
            presenter.getSellerProducts(seller, refresh, id);
            presenter.getSellerProfile(seller);

        }
    }

    //판매자의 다른 게시글 눌렀을 때 상세화면 보기로 넘어가기
    public void setItemClickListener() {
        itemClickListener = ((view1, position) -> {

            String id = String.valueOf(sellingProducts.get(position).getId());
            String seller = sellingProducts.get(position).getSeller();
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("seller", seller);
            intent.putExtra("hide", hide);
            startActivity(intent);
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        });
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(String message) {
        showSnackBar(message);
    }

    @Override
    public void onGetResultDelete(String message) {
        Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

        GlobalBus.getBus().post(backToHomeFromDetail);


        finish();
    }

    @Override
    public void onGetResult(List<Product> products) {
        product = products;
        setValues();

        if (product.get(0).getImageCnt() == 0) {
            relative_image.setVisibility(View.GONE);
        } else {
            relative_image.setVisibility(View.VISIBLE);
            initDotsSlider();
        }

    }

    @Override
    public void onGetResultIsChatRoom(List<Chat> chats) {
        chat = chats;
        if (chat.size() == 0) {
            chatRoomId = "firstChat";
            System.out.println("채팅방이 아무것도 없어요.");
        } else {
            chatRoomId = chat.get(0).getRoom_id();
            startChat.setText("채팅으로 거래하기(" + chat.size() + ")");


        }


    }

    @Override
    public void onGetResultSellerInfo(List<UserInfo> userinfos) {
        userinfo = userinfos;
        setSellerInfo(userinfo);

    }


    @Override
    public void onGetSellerProductsResult(List<Product> products) {
        sellingProducts = products;
        setSellerProducts();

    }

    @Override
    public void onGetResultLikeState(List<Product> products) {
        productLike = products;
        // System.out.println("좋아요를 눌렀냐 안눌렀냐 !? " + product.get(0).getLike_state());
        if (productLike.size() != 0) {
            if (productLike.get(0).getLike_state() == 1) {

                like_checked.setVisibility(View.VISIBLE);
                like_unchecked.setVisibility(View.INVISIBLE);
            } else {
                like_checked.setVisibility(View.INVISIBLE);
                like_unchecked.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPostLike() {
        Snackbar.make(getWindow().getDecorView().getRootView(), "관심목록에 추가되었습니다.", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSnackBar(String text) {
        Snackbar.make(getWindow().getDecorView().getRootView(), text, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //내 게시글일 떄 숨기기/숨기기 내 게시글 전용 메뉴 아이템 띄워주기
        if (sessionManager.isLoggIn() == true && seller.equals(myNick)) {
            getMenuInflater().inflate(R.menu.appbar_my_detail_product, menu);

            if (hide == 0) {
                menu.getItem(3).setTitle("숨기기");
            } else if (hide == 1) {
                menu.getItem(3).setTitle("숨기기 해제");
            }

        } else {
            //상대방 게시글일 때 보여줄 메뉴 아이템
            getMenuInflater().inflate(R.menu.appbar_product_detail, menu);
        }


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:


                GlobalBus.getBus().post(backToHomeFromDetail);
                finish();


                return true;
            case R.id.refresh:

                refresh = true;
                presenter.getData(id, refresh);
                presenter.getSellerProducts(seller, refresh, id);
                presenter.getSellerProfile(seller);

                if (sessionManager.isLoggIn() == true) {
                    //String nick = user.get(sessionManager.NICK).toString();
                    presenter.getLikeStates(myNick, id);
                }


                SliderDots.removeAllViews();    //새로고침을 했을 떄, 이미지 dots가 계속해서 생겨나는 걸 없애준다.
                return true;

            case R.id.report:


                return true;

            case R.id.forbid:


                return true;

            case R.id.share:


                return true;

            case R.id.update:

                if (product.get(0).getUpdateWritingCnt() == 4) {
                    Toast.makeText(this, "끌올은 한 게시글 당 4번 밖에 되지 않습니다.", Toast.LENGTH_LONG).show();
                } else {
                    String date = presenter.getCurrentTime("yyyyMMddHHmmssSSS");
                    presenter.updateProductDate(id, date);
                }


                return true;


            case R.id.motify:


                return true;


            case R.id.hide:

                if (item.getTitle().equals("숨기기")) {
                    presenter.updateWritingState(id, 4, item);
                } else {
                    presenter.updateWritingState(id, 5, item);
                }


                return true;


            case R.id.delete:
                presenter.showDialog(this, id);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //이미지 슬라이더 불러오기 메서드
    public void initDotsSlider() {

        mViewPager = new DetailImageSlider(product, this);
        viewPager.setAdapter(mViewPager);
        dotsCount = mViewPager.getCount();
        dots = new ImageView[dotsCount];

        if (dotsCount != 0) {
            for (int i = 0; i < dotsCount; i++) {

                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                SliderDots.addView(dots[i], params);

            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {


                    for (int i = 0; i < dotsCount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    public void setValues() {

        title.setText(product.get(0).getTitle().toString());
        if (product.get(0).getUpdateWritingCnt() >= 1) {
            date.setText("끌올 " + product.get(0).getDate().toString() + "전에");
        } else {
            date.setText(product.get(0).getDate().toString() + "전에");
        }

        description.setText(product.get(0).getDescription().toString());
        price.setText(product.get(0).getPrice().toString() + "원");
        category.setText(product.get(0).getCategory().toString());
        txtProducts.setText(product.get(0).getSeller().toString() + "님의 판매 상품");
        toolbar_title.setText(product.get(0).getTitle().toString());

        int dealable = product.get(0).getDealable();

        if (dealable == 0) {
            priceNego.setVisibility(View.GONE);
            priceNonNego.setVisibility(View.VISIBLE);
        } else if (dealable == 1) {
            priceNego.setVisibility(View.VISIBLE);
            priceNonNego.setVisibility(View.GONE);
        }

        likeCount.setText(product.get(0).getLike_count() + "");
        //likeCount.setText(product.get(0).getChat_count() + "");

        //내 게시글일 떄!

        relative_state.setVisibility(View.VISIBLE);
        if (product.get(0).getState() == 1) {
            showMyProductStateSelling();

        } else if (product.get(0).getState() == 2) {
            showMyProductStateReserving();

        } else if (product.get(0).getState() == 3) {
            showMyProductStateComplete();

        }


    }

    public void setSellerInfo(List<UserInfo> userinfo) {
        nick.setText(userinfo.get(0).getNick().toString());
        if (userinfo.get(0).getLocation1_state().equals("1")) {
            location.setText(userinfo.get(0).getDong1().toString());
        } else if (userinfo.get(0).getLocation2_state().equals("1")) {
            location.setText(userinfo.get(0).getDong2().toString());
        }
        //  String profile_img = "http://18.218.21.240/CarrotMarket/productsImages/2019050115353353019729.jpg";
        Glide.with(this).load(userinfo.get(0).getProfileImage()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.profileimg).into(profileImg);

    }

    //판매자 프로필 셋팅
    public void setSellerProducts() {
        //판매자의 판매하고 있는 상품이 현재 보고 있는 상품밖에 없으면 판매자의 상품 모두보기 화면은 안보이게 처리
        if (sellingProducts.size() == 0) {
            relative_products.setVisibility(View.GONE);
            recyclerView_products.setVisibility(View.GONE);
        } else {
            relative_products.setVisibility(View.VISIBLE);
            recyclerView_products.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView_products.setLayoutManager(layoutManager);
            adapter = new DetailSellerProductsAdapter(this, sellingProducts, itemClickListener);
            adapter.notifyDataSetChanged();
            recyclerView_products.setAdapter(adapter);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_showAll:
                Intent intent = new Intent(this, SellerActivity.class);
                intent.putExtra("nick", nick.getText().toString());
                startActivity(intent);
                break;

            case R.id.relative_profile:
                intent = new Intent(DetailActivity.this, SellerProfileActivity.class);
                intent.putExtra("nick", nick.getText().toString());
                startActivity(intent);

                break;

            case R.id.like_checked:
                if (sessionManager.isLoggIn() == true) {
                    //String nick = user.get(sessionManager.NICK).toString();
                    presenter.dislike(id, 0, myNick);
                    like_checked.setVisibility(View.INVISIBLE);
                    like_unchecked.setVisibility(View.VISIBLE);
                } else {
                    presenter.showLoginDialog(this);
                }

                break;

            case R.id.like_unchecked:
                if (sessionManager.isLoggIn() == true) {
                    // String nick = user.get(sessionManager.NICK).toString();
                    presenter.like(id, 1, myNick);
                    like_checked.setVisibility(View.VISIBLE);
                    like_unchecked.setVisibility(View.INVISIBLE);
                } else {
                    presenter.showLoginDialog(this);
                }

                break;

            case R.id.cardview_selling:
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "bottomSheet");
                break;

            case R.id.cardview_reserving:
                BottomSheetDialog bottomSheet2 = new BottomSheetDialog();
                bottomSheet2.show(getSupportFragmentManager(), "bottomSheet2");
                break;

            case R.id.cardview_deal_complete:
                BottomSheetDialog bottomSheet3 = new BottomSheetDialog();
                bottomSheet3.show(getSupportFragmentManager(), "bottomSheet3");
                break;

            case R.id.txt_reply:
                intent = new Intent(DetailActivity.this, ReplyActivity.class);
                //intent.putExtra("nick", nick.getText().toString());
                startActivity(intent);

                break;

            case R.id.cardview_chat:

                //처음 채팅을 시작하는 경우
                if (sessionManager.isLoggIn() == true && !seller.equals(myNick) && chat.size() == 0) {
                    intent = new Intent(DetailActivity.this, ChatRoomActivity.class);

                    intent.putExtra("id", id);
                    intent.putExtra("roomNum", chatRoomId); //roomNum = "firstChat"
                    intent.putExtra("nick", user.get(sessionManager.NICK).toString());
                    intent.putExtra("seller", product.get(0).getSeller());
                    intent.putExtra("partner", product.get(0).getSeller());
                    startActivity(intent);

                    //내 게시글이 아니고, 기존의 채팅방이 존재하는 경우
                } else if (sessionManager.isLoggIn() == true && !seller.equals(myNick) && chat.size() != 0) {
                    intent = new Intent(DetailActivity.this, ChatRoomActivity.class);

                    intent.putExtra("id", id);
                    intent.putExtra("roomNum", chat.get(0).getRoom_id());
                    intent.putExtra("nick", myNick);
                    intent.putExtra("seller", product.get(0).getSeller());
                    intent.putExtra("partner", product.get(0).getSeller());
                    Toast.makeText(this, product.get(0).getSeller(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    //내 게시글이고, 기존의 채팅방이 존재하는 경우
                } else if (sessionManager.isLoggIn() == true && seller.equals(myNick) && chat.size() != 0) {
                    intent = new Intent(DetailActivity.this, ChatListActivity.class);
                    intent.putExtra("title", product.get(0).getTitle());
                    intent.putExtra("id", id);
                    intent.putExtra("nick", myNick);
                    startActivity(intent);
                } //내 게시글이고, 기존의 채팅방이 존재하지 않는 경우
                else if (sessionManager.isLoggIn() == true && seller.equals(myNick) && chat.size() == 0) {
                    Toast.makeText(this, "채팅중인 거래가 없습니다. ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "접근불가 ", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }


    //bottom sheet dialog interface
    @Override
    public void onButtonClicked(int state) {
        if (state == 1) {

            presenter.updateWritingState(id, 1);
        } else if (state == 2) {

            presenter.updateWritingState(id, 2);
        } else if (state == 3) {

            presenter.updateWritingState(id, 3);
        }

    }

    @Override
    public void showMyProductStateSelling() {
        cardview_selling.setVisibility(View.VISIBLE);
        cardview_reserving.setVisibility(View.GONE);
        cardview_deal_complete.setVisibility(View.GONE);
    }

    @Override
    public void showMyProductStateReserving() {
        cardview_selling.setVisibility(View.GONE);
        cardview_reserving.setVisibility(View.VISIBLE);
        cardview_deal_complete.setVisibility(View.GONE);
    }

    @Override
    public void showMyProductStateComplete() {
        cardview_selling.setVisibility(View.GONE);
        cardview_reserving.setVisibility(View.GONE);
        cardview_deal_complete.setVisibility(View.VISIBLE);

        if (sessionManager.isLoggIn() == true && user.get(sessionManager.NICK).toString().equals(seller)) {
            Intent intent = new Intent(this, SelectBuyerActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", product.get(0).getTitle().toString());

            if (!product.get(0).getImage0().toString().isEmpty()) {
                intent.putExtra("product_image", product.get(0).getImage0().toString());
            } else {
                intent.putExtra("product_image", "ggg");
            }

            startActivity(intent);
        }

    }
}

