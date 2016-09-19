package com.edu.gvn.jsoupdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.online.AlbumFragment;
import com.edu.gvn.jsoupdemo.fragment.online.NavigationDrawerOnlineFragment;
import com.edu.gvn.jsoupdemo.fragment.online.SearchFragment;
import com.edu.gvn.jsoupdemo.fragment.online.hot.HotMusicFragment;


public class HomeActivity extends AppCompatActivity implements NavigationDrawerOnlineFragment.GetNavItemClickListener {

    private boolean isFishLoad = true;
    private static final String TAG = HomeActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private NavigationDrawerOnlineFragment navigationDrawerOnlineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setNavOnlFragment();

    }

    private void setNavOnlFragment() {
        navigationDrawerOnlineFragment = new NavigationDrawerOnlineFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.rl_nav, navigationDrawerOnlineFragment);
        navigationDrawerOnlineFragment.setUpNavDrawer((DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, this);
        ft.commit();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }


    @Override
    public void onItemClick(View v) {
        navigationDrawerOnlineFragment.closeNavDrawer();
        switch (v.getId()) {
            case R.id.ll_voice_serch:
                break;
            case R.id.ll_search:
                if (!isFishLoad)
                    replaceFragment(new SearchFragment());
                else {
                    addFragment(new SearchFragment());
                    isFishLoad = false;
                }
                break;
            case R.id.ll_hot_music:
                if (!isFishLoad)
                    replaceFragment(new HotMusicFragment());
                else {
                    addFragment(new HotMusicFragment());
                    isFishLoad = false;
                }

                break;
            case R.id.ll_rank:
                break;
            case R.id.ll_artists:
                break;
            case R.id.ll_albums:
                if (!isFishLoad)
                    replaceFragment(new AlbumFragment());
                else {
                    addFragment(new AlbumFragment());
                    isFishLoad = false;
                }

                break;

            case R.id.ll_top_ten:
                break;
            case R.id.ll_option_lyric_screen:
                break;
            case R.id.ll_option_suggested_apps:
                break;
            case R.id.ll_option_settings:
                break;
            case R.id.ll_option_exit:
                break;


        }
    }

    //if parent fragment
    public void addFragment(Fragment toFragment) {
        String backStackName = toFragment.getClass().getName();
        String tag = backStackName;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.rl_parent, toFragment, tag);
            transaction.addToBackStack(backStackName);
            transaction.commit();
        }

    }

    public void replaceFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();
        String tag = backStackName;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // nếu tìm thấy fragment này trong backStack thì return true
        // else return false.
        
        boolean isExistFragment = manager.popBackStackImmediate(backStackName, 0);
        if (!isExistFragment) {
            transaction.replace(R.id.rl_parent, fragment);
            transaction.addToBackStack(backStackName);
            transaction.commit();
        }


    }

    //if parent fragment
    public void addFragment(Fragment fromFragment, Fragment toFragment, int idParentView) {
        String backStackName = toFragment.getClass().getName();
        String tag = backStackName;
        FragmentManager mFragmentManager = fromFragment.getChildFragmentManager();
        FragmentTransaction transaction = fromFragment.getChildFragmentManager().beginTransaction();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(idParentView, toFragment, tag);
            transaction.addToBackStack(backStackName);
            transaction.commit();
        }

    }

    public void replaceFragment(Fragment fromFragment, Fragment toFragment, int idParentView) {
        FragmentTransaction transaction = fromFragment.getChildFragmentManager().beginTransaction();
        transaction.replace(idParentView, toFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
