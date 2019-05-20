package app.worldplay.rappi.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 2d67ecc87531d30922e21d165d63350c7dffabbc

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
<<<<<<< HEAD

=======
>>>>>>> 2d67ecc87531d30922e21d165d63350c7dffabbc
    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public int getCount() {
<<<<<<< HEAD
        Log.i("size", ""+this.fragments.size());
        return this.fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
=======
        return this.fragments.size();
    }

    public CharSequence getPageTitle(int position){
        switch(position){
>>>>>>> 2d67ecc87531d30922e21d165d63350c7dffabbc
            case 0:
                return mContext.getString(R.string.movies);
            case 1:
                return mContext.getString(R.string.series);
<<<<<<< HEAD
            default:
=======
                default:
>>>>>>> 2d67ecc87531d30922e21d165d63350c7dffabbc
                return null;
        }
    }
}
