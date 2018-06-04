package com.example.user.studentpurse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Services.BalanceService;
import com.example.user.studentpurse.Services.IBalanceService;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toogle;
    TextView text;
    ImageButton plus;
    ImageButton minus;
    Button main;
    SmallPurseParameters parameters;
    Balance balance;
    IBalanceService balanceService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toogle = new ActionBarDrawerToggle(this,drawer,R.string.Open, R.string.Close);
        drawer.addDrawerListener(toogle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
        text = (TextView) findViewById(R.id.text);
        minus = (ImageButton) findViewById(R.id.minus);
        plus = (ImageButton) findViewById(R.id.plus);
        main = (Button) findViewById(R.id.main);
        balanceService = new BalanceService(this);
        try {
            parameters = JSONHelper.importFromJSON(this);
            for (Balance bal : parameters.balances){
                if(bal.Storage.equals("Общие")){
                    balance = bal;
                    break;
                }
            }
            if (balance != null){
                main.setText(balanceService.toString(balance));
            }

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Minus.class);
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Static.class);
                startActivity(intent);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Plus.class);
            startActivity(intent);
        }
    });
}

@Override
public boolean onOptionsItemSelected(MenuItem item){
        if(toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
}

public void selectItemDrawer (MenuItem menuItem) {
    Fragment myFragment = null;
    Class fragmentClass;
    switch (menuItem.getItemId()) {
        case R.id.hm:
            fragmentClass = Statistic.class;
            break;
        case R.id.st:
            Intent intent = new Intent(getBaseContext(), StatiscticM.class);
            startActivity(intent);
            fragmentClass = Statistic.class;
            break;
        case R.id.cl:
            fragmentClass = Calandary.class;
            break;
        case R.id.sett:
            fragmentClass = Settings.class;
            break;
        case R.id.mp:
            fragmentClass = Maps.class;
            break;
        default:
            fragmentClass = Statistic.class;
    }
    try {
        myFragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e) {
        e.printStackTrace();
    }

    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.first,myFragment).commit();

    menuItem.setChecked(true);
    setTitle(menuItem.getTitle());
    drawer.closeDrawers();
}
private void setupDrawerContent(NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });

}
}
