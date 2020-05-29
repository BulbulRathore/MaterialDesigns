package com.example.useofmaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class SocialMediaActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private TabLayout tablayout;
    private TabAdapter tabadapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        mAppBarLayout = findViewById(R.id.my_appbar);
        tablayout = findViewById(R.id.myToolbar);
        viewPager = findViewById(R.id.viewpager);
        tabadapter = new TabAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(tabadapter);
        tablayout.setupWithViewPager(viewPager,false);
    }
}
