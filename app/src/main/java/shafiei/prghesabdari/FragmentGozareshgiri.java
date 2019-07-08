package shafiei.prghesabdari;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;

import java.util.Calendar;

public class FragmentGozareshgiri extends Fragment implements DateSetListener {
    Button azTarikhHesab , taTarikhHesab , nameHesab , azTarikhTarakonesh , taTarikhTarakonesh;
    RadioGroup rb;
    String idHesab;
    String nameHesab_;
    DatabaseForHesabdari databaseForHesabdari;
    TextView txtMojodiKol,txtMojodiDaramad,txtMojodiHazine;
    Double mojodiKol;
    Double mojodiDaramad;
    Double mojodiHazine;
    Button btn_enter_hesab;
    TextView txt_hesab_kol , txt_hesab_kol_mablagh ,
            txt_hesab_daramad , txt_hesab_daramad_mablagh , txt_hesab_hazine , txt_hesab_hazine_mablagh;



    public FragmentGozareshgiri(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gozareshgiri,container,false);
        azTarikhHesab = (Button) view.findViewById(R.id.btn_azTarikh);
        taTarikhHesab = (Button) view.findViewById(R.id.btn_tzTarikh);
        azTarikhTarakonesh = (Button) view.findViewById(R.id.btn_azTarikh_tarakonesh);
        taTarikhTarakonesh = (Button) view.findViewById(R.id.btn_tzTarikh_tarakonesh);
        nameHesab = (Button) view.findViewById(R.id.btn_hesab);
        rb = (RadioGroup ) view.findViewById(R.id.radioG);

        txt_hesab_kol = (TextView) view.findViewById(R.id.txt_hesab_kol);
        txt_hesab_kol_mablagh = (TextView) view.findViewById(R.id.txt_kol_hesab);
        txt_hesab_daramad = (TextView) view.findViewById(R.id.txt_daramad_hesab);
        txt_hesab_daramad_mablagh = (TextView) view.findViewById(R.id.txt_hesab_daramad_mablagh);
        txt_hesab_hazine = (TextView) view.findViewById(R.id.txt_hesab_hazine);
        txt_hesab_hazine_mablagh = (TextView) view.findViewById(R.id.txt_hesab_hazine_mablagh);

        btn_enter_hesab = (Button) view.findViewById(R.id.btn_namayesh);




        txtMojodiKol = view.findViewById(R.id.txt_kol);
        txtMojodiDaramad = view.findViewById(R.id.txt_daramad);
        txtMojodiHazine = view.findViewById(R.id.txt_hazine);

        databaseForHesabdari = new DatabaseForHesabdari(getContext());
        mojodiKol = databaseForHesabdari.readMojodiKol();
        txtMojodiKol.setText(mojodiKol.toString());

        mojodiDaramad = databaseForHesabdari.readMojodiDaramad();
        txtMojodiDaramad.setText(mojodiDaramad.toString());

        mojodiHazine = databaseForHesabdari.readMojodiDaramad();
        txtMojodiHazine.setText(mojodiHazine.toString());

        Typeface typeface= Typeface.createFromAsset(getActivity().getAssets(), "BNazanin_0.ttf");
        azTarikhHesab.setTypeface(typeface);
        taTarikhHesab.setTypeface(typeface);
        azTarikhTarakonesh.setTypeface(typeface);
        taTarikhTarakonesh.setTypeface(typeface);
        nameHesab.setTypeface(typeface);
        txtMojodiDaramad.setTypeface(typeface);
        txtMojodiHazine.setTypeface(typeface);
        txtMojodiKol.setTypeface(typeface);

        txt_hesab_daramad_mablagh.setTypeface(typeface);
        txt_hesab_hazine_mablagh.setTypeface(typeface);
        txt_hesab_kol_mablagh.setTypeface(typeface);

        azTarikhHesab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(1)
                        .build(FragmentGozareshgiri.this)
                        .show(getFragmentManager(), "");
            }
        });

        taTarikhHesab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(2)
                        .build(FragmentGozareshgiri.this)
                        .show(getFragmentManager(), "");
            }
        });

        azTarikhTarakonesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(3)
                        .build(FragmentGozareshgiri.this)
                        .show(getFragmentManager(), "");
            }
        });

        taTarikhTarakonesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(4)
                        .build(FragmentGozareshgiri.this)
                        .show(getFragmentManager(), "");
            }
        });

        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rb.getCheckedRadioButtonId())
                {
                    case R.id.radioD:
                        Toast.makeText(getContext(), "Radio Daramad clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioG:
                        Toast.makeText(getContext(), "Radio Hazine clicked", Toast.LENGTH_SHORT).show();
                        break;   
                }
            }
        });

        nameHesab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),HesabActivity.class);
                startActivityForResult(intent, 1);
                nameHesab.setText(nameHesab_);
            }
        });


        btn_enter_hesab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (azTarikhHesab.getText().toString().equals("")|| taTarikhHesab.getText().toString().equals("")
                || nameHesab.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "موارد مربوط به تاریخ و نام حساب را انتخاب نمایید", Toast.LENGTH_SHORT).show();
                }
                else {
                    Double daramad = databaseForHesabdari.readDaramadHesab(azTarikhHesab.getText().toString(),
                            taTarikhHesab.getText().toString(),idHesab);
                    Double hazine = databaseForHesabdari.readHazineHesab(azTarikhHesab.getText().toString(),
                            taTarikhHesab.getText().toString(),idHesab);
                    Double kol = daramad - hazine;

                    txt_hesab_kol_mablagh.setText(kol.toString());
                    txt_hesab_daramad_mablagh.setText(daramad.toString());
                    txt_hesab_hazine_mablagh.setText(hazine.toString());

                    txt_hesab_hazine.setVisibility(View.VISIBLE);
                    txt_hesab_kol_mablagh.setVisibility(View.VISIBLE);
                    txt_hesab_daramad_mablagh.setVisibility(View.VISIBLE);
                    txt_hesab_hazine_mablagh.setVisibility(View.VISIBLE);
                    txt_hesab_daramad.setVisibility(View.VISIBLE);
                    txt_hesab_kol.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==1)
        {
            idHesab = data.getStringExtra("idHesab");
            nameHesab_ = data.getStringExtra("nameHesab");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        switch (id)
        {
            case 1:
                azTarikhHesab.setText(year + "/" + month + "/" + day);
                break;
            case 2:
                taTarikhHesab.setText(year + "/" + month + "/" + day);
                break;
            case 3:
                azTarikhTarakonesh.setText(year + "/" + month + "/" + day);
                break;
            case 4:
                taTarikhTarakonesh.setText(year + "/" + month + "/" + day);
                break;
        }
    }
}
