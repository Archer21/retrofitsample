package com.archer.retrofittest.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.archer.retrofittest.R;
import com.archer.retrofittest.ui.fragments.DownloadsFragment;
import com.archer.retrofittest.ui.fragments.FavoritesFragment;
import com.archer.retrofittest.ui.fragments.FriendsFragment;
import com.archer.retrofittest.ui.fragments.MainMenuFragment;
import com.archer.retrofittest.ui.fragments.SongsFragment;
import com.archer.retrofittest.ui.fragments.TopArtistsFragment;


public class MainActivity extends AppCompatActivity{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawerLayout;
    Toolbar toolbar;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null)
            setSupportActionBar(toolbar);

        if (savedInstanceState == null)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_container, new MainMenuFragment()).commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        setDrawerLayout(drawerLayout); // Setear Toolbar como action bar
    }

    private void setDrawerLayout(DrawerLayout drawer) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public Fragment selectItem(MenuItem item){
        int id = item.getItemId();

        Fragment fragment = null;


        if (id == R.id.nav_all_songs) {
            fragment = new MainMenuFragment();
        } else if (id == R.id.nav_downloads) {
            fragment = new DownloadsFragment();
        } else if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
        } else if (id == R.id.nav_friends) {
            fragment = new FriendsFragment();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(getApplicationContext(), "SettingsFragment", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_help) {
            Toast.makeText(getApplicationContext(), "HelpFragment", Toast.LENGTH_SHORT).show();
        }

        return fragment;
    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment currentFragment = selectItem(item);

                        if (currentFragment != null)
                        {
                            fragmentTransaction.replace(R.id.main_container, currentFragment).commit();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }
        );
    }
}
