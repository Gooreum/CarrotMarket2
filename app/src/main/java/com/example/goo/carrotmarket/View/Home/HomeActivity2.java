package com.example.goo.carrotmarket.View.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.goo.carrotmarket.R;
import com.example.goo.carrotmarket.Util.BottomNavigationViewHelper;
import com.example.goo.carrotmarket.View.Category.CategoryFragment;
import com.example.goo.carrotmarket.View.Chat.ChatList.ChatListFragment;
import com.example.goo.carrotmarket.View.MyProfile.MyProfileFragment;
import com.example.goo.carrotmarket.View.Write.WriteFragment;


public class HomeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //BottomNavigationViewHelper 클래스로부터 하단네비게이션바의 shifting 애니메이션을 없애도록 한다.
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //프래그먼트 중에서 홈화면이 제일 첫 화면으로 나오게 한다.
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //번들을 이용해서 개인화면에 로그인 할 때 가져온 이메일 값을 보내줌.
                    Bundle bundle1 = new Bundle();
                    selectedFragment = new HomeFragment();
                    selectedFragment.setArguments(bundle1);

                    break;


                case R.id.navigation_category:
                    Bundle bundle2 = new Bundle();
                    selectedFragment = new CategoryFragment();
                    selectedFragment.setArguments(bundle2);

                    break;

                case R.id.navigation_writing:

                    Bundle bundle3 = new Bundle();
                    selectedFragment = new WriteFragment();
                    selectedFragment.setArguments(bundle3);


                    break;

                case R.id.navigation_chat:


                    Bundle bundle4 = new Bundle();
                    selectedFragment = new ChatListFragment();
                    selectedFragment.setArguments(bundle4);

                    break;

                case R.id.navigation_my_profile:

                    Bundle bundle5 = new Bundle();
                    selectedFragment = new MyProfileFragment();
                    selectedFragment.setArguments(bundle5);

                    break;
            }
            //화면 전환을 가능하게 해줌.
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    selectedFragment).commit();

            return true;
        }
    };

}
