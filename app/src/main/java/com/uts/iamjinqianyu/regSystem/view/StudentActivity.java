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

import com.google.firebase.auth.FirebaseAuth;
import com.uts.iamjinqianyu.regSystem.fragments.StudentOneFragment;
import com.uts.iamjinqianyu.regSystem.fragments.StudentTwoFragment;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.Views;

public class StudentActivity extends AppCompatActivity implements Views {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;
    /*FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Class> mClassArrayList = new ArrayList<Class>();*/

    public static int navItemIndex = 0;

    private static final String TAG_ONE = "one";
    private static final String TAG_TWO = "two";
    public static String CURRENT_TAG = TAG_ONE;
    private final String KEY_NAVINDEX = "KEY_NAVINDEX";
    private final String KEY_CURRENT_TAG = "KEY_CURRENT_TAG";
    private String[] activityTitles = {"function 1", "function 2"};
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_student);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);

        setNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_ONE;
            Log.d("DEBUG onCreate", CURRENT_TAG + " is null");
            loadFragment();
        }
        /*setUpClass();

        mRecyclerView = (RecyclerView) findViewById(R.id.classList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ClassListAdapter(mClassArrayList, STUDENT_CLASSLIST);
        mRecyclerView.setAdapter(mAdapter);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "sign out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                firebaseAuth.signOut();
                startActivity(new Intent(StudentActivity.this, EntryActivity.class));

            }
        });*/
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
                StudentOneFragment adminFunctionOneFragment = new StudentOneFragment();
                return adminFunctionOneFragment;
            case 1:
                StudentTwoFragment adminFunctionTwoFragment = new StudentTwoFragment();
                return adminFunctionTwoFragment;
            default:
                return new StudentOneFragment();
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
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_student);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseAuth.signOut();
            startActivity(new Intent(StudentActivity.this, EntryActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*private void setUpClass() {
        Class classOne = new Class("Coding Class", 10);
        Class classTwo = new Class("Math class", 5);
        mClassArrayList.add(classOne);
        mClassArrayList.add(classTwo);
    }*/

}
