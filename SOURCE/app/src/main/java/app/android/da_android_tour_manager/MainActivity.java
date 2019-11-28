package app.android.da_android_tour_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.fragment.DefaultProfileFragment;
import app.android.da_android_tour_manager.fragment.DefaultSettingsFragment;
import app.android.da_android_tour_manager.fragment.HomeFragment;
import app.android.da_android_tour_manager.fragment.ProfileFragment;
import app.android.da_android_tour_manager.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    int position;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());

    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment !=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                if (position!=1) {
                    fragment = new HomeFragment();
                    position = 1;
                }
                break;
            case R.id.nav_profile:
                if (position!=2) {
                    if(UserCommon.email != null)
                        fragment = new ProfileFragment();
                    else
                        fragment = new DefaultProfileFragment();
                    position = 2;
                }
                break;
            case R.id.nav_settings:
                if (position!=3) {
                    if(UserCommon.checkDangNhap == 1)
                        fragment = new SettingsFragment();
                    else
                        fragment = new DefaultSettingsFragment();
                    position = 3;
                }
                break;
        }
        return loadFragment(fragment);
    }
}
