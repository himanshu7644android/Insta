package com.examplmakecodeeasy.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;


public class SocialMediaActivity extends AppCompatActivity {

   // private Toolbar toolbar;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private androidx.appcompat.widget.Toolbar toolbar;
   // private  TableLayout tabLayout;
   private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("Social Media App!!");

        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,false);








    }


}