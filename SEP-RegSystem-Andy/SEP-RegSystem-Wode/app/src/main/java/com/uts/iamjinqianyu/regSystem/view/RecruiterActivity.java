package com.uts.iamjinqianyu.regSystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.fragments.RecruiterOneFragment;

public class RecruiterActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navHeader;
    private TextView navHeaderName;
    private TextView navHeadEmail;
    private Toolbar toolbar;
    public static int navItemIndex = 0;

    private static final String TAG_ONE = "one";
    private static final String TAG_TWO = "two";
    public static String CURRENT_TAG = TAG_ONE;

    private final String KEY_NAVINDEX = "KEY_NAVINDEX";
    private final String KEY_CURRENT_TAG = "KEY_CURRENT_TAG";

    private String[] activityTitles = {"Function 1", "Function 2"};

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_recruiter);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        navHeadEmail = (TextView) navHeader.findViewById(R.id.textView_navemail);
        navHeaderName = (TextView) navHeader.findViewById(R.id.text_name);
        //activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_ONE;
            Log.d("DEBUG onCreate", CURRENT_TAG + " is null");
            loadFragment();
        }

        if (firebaseAuth.getCurrentUser() != null) {
            //navHeadEmail = (TextView) findViewById(R.id.textView_email);
            navHeadEmail.setText(firebaseAuth.getCurrentUser().getEmail());
        }
    }
    public void loadFragment() {
        selectNavMenu();
        setToolbar();
        Fragment fragment = getFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                RecruiterOneFragment recFunctionOneFragment = new RecruiterOneFragment();
                return recFunctionOneFragment;
            case 1:
                RecruiterOneFragment recFunctionTwoFragment = new RecruiterOneFragment();
                return recFunctionTwoFragment;
            default:
                return new RecruiterOneFragment();
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbar() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_function1) {
                    // Handle the camera action
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_ONE;
                } else if (id == R.id.nav_function2) {
                    navItemIndex = 1;
                    CURRENT_TAG = TAG_TWO;
                } else {
                    navItemIndex = 0;
                }
                /*if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);*/
                loadFragment();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_recruiter);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseAuth.signOut();
            startActivity(new Intent(RecruiterActivity.this, SignInActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
