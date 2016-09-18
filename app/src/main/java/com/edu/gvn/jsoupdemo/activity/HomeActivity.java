package com.edu.gvn.jsoupdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.edu.gvn.jsoupdemo.R;
import com.edu.gvn.jsoupdemo.fragment.online.AlbumFragment;
import com.edu.gvn.jsoupdemo.fragment.online.NavigationDrawerOnlineFragment;
import com.edu.gvn.jsoupdemo.fragment.online.SearchFragment;
import com.edu.gvn.jsoupdemo.fragment.online.hot.HotMusicFragment;


public class HomeActivity extends AppCompatActivity implements NavigationDrawerOnlineFragment.GetNavItemClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Toolbar mToolbar;

    private NavigationDrawerOnlineFragment mOnlineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setNavOnlFragment();

    }

    private void setNavOnlFragment() {
        mOnlineFragment = (NavigationDrawerOnlineFragment) getSupportFragmentManager().findFragmentById(R.id.frg_nav_drawer_onl);
        mOnlineFragment.setUpNavDrawer(R.id.frg_nav_drawer_onl, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, this);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }


    @Override
    public void onItemClick(View v) {
        Toast.makeText(HomeActivity.this, v.getId() + "", Toast.LENGTH_SHORT).show();
        switch (v.getId()) {

            case R.id.ll_voice_serch:
                break;
            case R.id.ll_search:
                replaceFragment(new SearchFragment());
                mOnlineFragment.closeNavDrawer();
                break;
            case R.id.ll_hot_music:
                replaceFragment(new HotMusicFragment());
                mOnlineFragment.closeNavDrawer();
                break;
            case R.id.ll_rank:
                break;
            case R.id.ll_artists:
                break;
            case R.id.ll_albums:
                replaceFragment(new AlbumFragment());
                mOnlineFragment.closeNavDrawer();
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


    public void replaceFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();
        String tag = backStackName;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rl_parent, fragment);
        transaction.addToBackStack(backStackName);
        transaction.commit();
    }

    //if parent fragment
    public void addFragment(Fragment fromFragment, Fragment toFragment, int idParentView) {
        String backStackName = toFragment.getClass().getName();
        String tag = backStackName;
        FragmentManager mFragmentManager = fromFragment.getChildFragmentManager();
        FragmentTransaction transaction = fromFragment.getChildFragmentManager().beginTransaction();

        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(backStackName,0);
        if (!fragmentPopped && mFragmentManager.findFragmentByTag(tag)==null){
            transaction.add(idParentView, toFragment, tag);
            transaction.addToBackStack(backStackName);
            transaction.commit();
        }

    }

    public void replaceFragment(Fragment fromFragment,Fragment toFragment, int idParentView){
        String backStackName = toFragment.getClass().getName();
        String tag=backStackName;
        FragmentTransaction transaction = fromFragment.getChildFragmentManager().beginTransaction();
        transaction.replace(idParentView,toFragment,tag);
        transaction.addToBackStack(backStackName);
        transaction.commit();
    }

}
