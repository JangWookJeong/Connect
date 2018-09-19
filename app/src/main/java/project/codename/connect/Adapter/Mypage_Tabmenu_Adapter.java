package project.codename.connect.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import project.codename.connect.Fragment.Mypage_content_Fragment;

public class Mypage_Tabmenu_Adapter extends FragmentStatePagerAdapter {

    private static final int PAGE_NUMBER = 4;

    public Mypage_Tabmenu_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return Mypage_content_Fragment.newInstance();
            case 1:
                return Mypage_content_Fragment.newInstance();
            case 2:
                return Mypage_content_Fragment.newInstance();
            case 3:
                return Mypage_content_Fragment.newInstance();
        }
        return null;
    }/////getItem

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }/////getCount

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "포스트";
            case 1:
                return "동영상";
            case 2:
                return "사진";
            case 3:
                return "정보";

            default:
                return null;
        }////getpagetitle
    }
}/////Mypage_Tabmenu_Adapter Class
