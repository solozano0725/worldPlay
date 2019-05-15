package app.worldplay.rappi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import app.worldplay.rappi.ui.FragmentAdapter;
import app.worldplay.rappi.ui.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setupViewPager();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_series:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_movies:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_popular:
                // mTextMessage.setText(R.string.series);
                return true;
            case R.id.search_top:
                //  mTextMessage.setText(R.string.movies);
                return true;
            case R.id.search_upcoming:
                //  mTextMessage.setText(R.string.movies);
                return true;
        }
        return false;
    }

    private void initUI(){
        viewPager = findViewById(R.id.view_pager);
        navView = findViewById(R.id.nav_view);
        pagerAdapter = new ViewPagerAdapter(this,getSupportFragmentManager());
        pagerAdapter.addFragment(FragmentAdapter.newInstance("type", getString(R.string.movies)));
        pagerAdapter.addFragment(FragmentAdapter.newInstance("type", getString(R.string.series)));
        viewPager.setAdapter(pagerAdapter);
    }

    public void setupViewPager(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    navView.getMenu().getItem(0).setChecked(false);

                navView.getMenu().getItem(i).setChecked(true);
                prevMenuItem = navView.getMenu().getItem(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
