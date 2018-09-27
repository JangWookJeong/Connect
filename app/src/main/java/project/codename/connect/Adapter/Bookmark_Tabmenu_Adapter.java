package project.codename.connect.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import project.codename.connect.Fragment.Bookmark_Friend_Fragment;
import project.codename.connect.Fragment.Bookmark_Message_Fragment;
import project.codename.connect.Fragment.Bookmark_Noti_Fragment;

public class Bookmark_Tabmenu_Adapter extends FragmentStatePagerAdapter {
    public static final int BOOKMARK_PAGER_NUMBER = 3;

    public Bookmark_Tabmenu_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Bookmark_Friend_Fragment.newInstance();
            case 1:
                return Bookmark_Message_Fragment.newInstance();
            case 2:
                return Bookmark_Noti_Fragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return BOOKMARK_PAGER_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "친구";
            case 1:
                return "메세지";
            case 2:
                return "알림";
            default:
                return null;
        }
    }
}
