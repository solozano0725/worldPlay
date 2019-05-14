package app.worldplay.rappi.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.worldplay.rappi.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    private List<Fragment> fragments;

    public ViewPagerAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        this.mContext = context;
        this.fragments = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return mContext.getString(R.string.movies);
            case 1:
                return mContext.getString(R.string.series);
                default:
                return null;
        }
    }
}
