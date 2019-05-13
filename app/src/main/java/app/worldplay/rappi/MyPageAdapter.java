package app.worldplay.rappi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return FragmentAdapter.newInstance(0);
            case 1:
                return FragmentAdapter.newInstance(1);
            case 2:
                return FragmentSingleton.newInstance(1, context.getString(R.string.popular));
            case 3:
                return FragmentSingleton.newInstance(1, context.getString(R.string.top_rated));
            case 4:
                return FragmentSingleton.newInstance(1, context.getString(R.string.upcoming));
            case 5:
                return FragmentSingleton.newInstance(2, context.getString(R.string.popular));
            case 6:
                return FragmentSingleton.newInstance(2, context.getString(R.string.top_rated));
            case 7:
                return FragmentSingleton.newInstance(2, context.getString(R.string.upcoming));
            default:
                return FragmentAdapter.newInstance(3);
        }
    }

    @Override
    public int getCount() {
        return 8;
    }
}
