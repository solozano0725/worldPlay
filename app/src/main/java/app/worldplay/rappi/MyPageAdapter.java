package app.worldplay.rappi;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return FragmentAdapter.newInstance(0, context.getString(R.string.popular));
            case 1:
                return FragmentAdapter.newInstance(0, context.getString(R.string.top_rated));
            case 2:
                return FragmentAdapter.newInstance(0, context.getString(R.string.upcoming));
            case 3:
                return FragmentAdapter.newInstance(1, context.getString(R.string.popular));
            case 4:
                return FragmentAdapter.newInstance(1, context.getString(R.string.top_rated));
            case 5:
                return FragmentAdapter.newInstance(1, context.getString(R.string.upcoming));
            default:
                return FragmentAdapter.newInstance(2, context.getString(R.string.upcoming));
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
