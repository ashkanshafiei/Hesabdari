package shafiei.prghesabdari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DastehayeKoliActivity extends AppCompatActivity {
    ListView lstDastehayeKoli;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    DatabaseForHesabdari databaseForHesabdari;
    List<DastebandiItem> dastebandiItems;
    String nd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dastehaye_koli);
//        هرکدام از این مقدار ها را که قرار است شناسایی بشوند را شناسایی کند
        lstDastehayeKoli = (ListView) findViewById(R.id.lst_dastehayeKoli);
        Intent intent = getIntent();
        nd = intent.getStringExtra("nd");

        list = new ArrayList<>();
        databaseForHesabdari = new DatabaseForHesabdari(this);
        bindDastebandi();
        lstDastehayeKoli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer idDastebandiKoli = dastebandiItems.get(position).id;
                Intent intent = new  Intent(DastehayeKoliActivity.this, DastehayeJozeeActivity.class);
                intent.putExtra("idKoli",idDastebandiKoli.toString());
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String idJozee = data.getStringExtra("idJozee");
        Intent intent = new Intent();
        intent.putExtra("idJozee" ,idJozee);
        setResult(2,intent);
        finish();
    }

    public void bindDastebandi()
    {
        dastebandiItems = databaseForHesabdari.redaDastebandi(nd);

        for (int i = 0; i<dastebandiItems.size() ; i++)
        {
//            این nameHesab را به شی list انتقال بده
            list.add(dastebandiItems.get(i).nameHesab);
        }
//        و حال adapter را تکمیل کن
        adapter = new ArrayAdapter<String>(this, R.layout.hesab_row,list);
        lstDastehayeKoli.setAdapter(adapter);
    }
}
