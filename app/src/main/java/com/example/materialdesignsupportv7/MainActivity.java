package com.example.materialdesignsupportv7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.materialdesignsupportv7.adapters.MyFragmentPagerAdapter;
import com.example.materialdesignsupportv7.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Main Activity");
        toolbar.setSubtitle("Just a subtitle");
        toolbar.setLogo(R.drawable.ic_launcher_background);
        setSupportActionBar(toolbar);

        Toolbar toolbarBottom = findViewById(R.id.inc_tb_bottom);
        toolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.action_youtube:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.action_google_plus:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://plus.google.com"));
                        break;
                    case R.id.action_linkedin:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.action_whatsapp:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }

                startActivity(it);
                return true;
            }
        });

        toolbarBottom.inflateMenu(R.menu.menu_bottom);

        toolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Settings pressed", Toast.LENGTH_SHORT).show();
            }
        });

        //TAB com os fragments
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.title_tab)));
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_second_activity) {
            startActivity(new Intent(this, SecondActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Car> getSetCarList(int qtd/*, int category*/) {
        String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
        int[] categories = new int[]{2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        String[] urlPhotos = new String[]{"gallardo.jpg", "vyron.jpg", "corvette.jpg", "paganni_zonda.jpg", "porsche_911.jpg", "bmw_720.jpg", "db77.jpg", "mustang.jpg", "camaro.jpg", "ct6.jpg"};
        String description = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        List<Car> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Car c = new Car(models[i % models.length], brands[i % brands.length], photos[i % models.length], Car.PATH + urlPhotos[i % models.length]);
            c.setDescription(description);
            c.setCategory(categories[i % brands.length]);
            c.setTel("33221155");

//            if(category != 0 && c.getCategory() != category){
//                continue;
//            }
            listAux.add(c);
        }
        return (listAux);
    }
}
