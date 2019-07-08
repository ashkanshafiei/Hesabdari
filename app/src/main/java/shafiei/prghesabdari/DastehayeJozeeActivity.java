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

public class DastehayeJozeeActivity extends AppCompatActivity {
    ListView lstDastehayeJozee;
    DatabaseForHesabdari databaseForHesabdari;
    ArrayAdapter <String> adapter;
    ArrayList <String> list;
    List<DastebandiItem> dastebandiItem;
    String ID_Dastehaye_Koli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dastehaye_jozee);
        final Intent intent = getIntent();
        ID_Dastehaye_Koli = intent.getStringExtra("idKoli");
        databaseForHesabdari = new DatabaseForHesabdari(this);
        list = new ArrayList<>();
        lstDastehayeJozee = (ListView) findViewById(R.id.lst_dastehayeJozee);
        bindDastehayeJozee();
        lstDastehayeJozee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idJozee = dastebandiItem .get(position).id.toString();
                Intent i = new Intent();
                i.putExtra("idJozee" , idJozee);
                setResult(2,i);
                finish();
            }
        });

    }
    public void bindDastehayeJozee()
    {
        dastebandiItem = databaseForHesabdari.readDastehayeJozee(ID_Dastehaye_Koli);
        for (int i = 0;i<dastebandiItem.size() ; i++)
        {
            list.add(dastebandiItem.get(i).nameHesab);
        }
        adapter = new ArrayAdapter<>(this, R.layout.hesab_row, list);
        lstDastehayeJozee.setAdapter(adapter);
    }
}
