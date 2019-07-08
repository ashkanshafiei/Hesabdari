package shafiei.prghesabdari;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class Activity_daramad extends AppCompatActivity implements DateSetListener {
    private TextInputLayout mablagh,hesab,dastebandi,tarikh,tozihat;
    EditText editTextMablagh ,editTextHesab ,editTextDastebandi,editTextTarikh,editTextTozihat;
    Button buttonSave;
    ImageView imageViewBack;
    DatabaseForHesabdari databaseForHesabdari;
    String idHesab;
    String idJozee;
    String nd;

    //    تک تک داده هایی را که از hesabActivity فرستادیم در اینجا پس میگیریم
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        اگر کد یک بود ، یعنی اگر از hesabActivity چیزی برای ما ارسال شده بود
        if (resultCode == 1)
        {
//            میگوییم شما nameHesab را از hesabActivity دریافت کن
            String nameHesab = data.getStringExtra("nameHesab");
            idHesab = data.getStringExtra("idHesab");

//            در اینجا داده ره setText میکنیم در editText
            editTextHesab.setText(nameHesab);
        }
        if (resultCode == 2)
        {
            idJozee = data.getStringExtra("idJozee");
            String nameDastehayeJozeeKoli = databaseForHesabdari.readView(idJozee);
            editTextDastebandi.setText(nameDastehayeJozeeKoli);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         شناسایی ویجیت ها
        setContentView(R.layout.activity_daramad);
        Intent intent = getIntent();
        nd = intent.getStringExtra("nd");
        mablagh = (TextInputLayout) findViewById(R.id.inputMablagh);
        hesab = (TextInputLayout) findViewById(R.id.inputHesab);
        dastebandi = (TextInputLayout) findViewById(R.id.inputDastebandi);
        tarikh = (TextInputLayout) findViewById(R.id.inputTarikh);
        tozihat = (TextInputLayout) findViewById(R.id.inputTozihat);
        editTextMablagh = (EditText) findViewById(R.id.editTextMablagh);
        editTextHesab = (EditText) findViewById(R.id.editTextHesab);
        editTextDastebandi = (EditText) findViewById(R.id.editTextDastebandi);
        editTextTarikh = (EditText) findViewById(R.id.editTextTarikh);
        editTextTozihat = (EditText) findViewById(R.id.editTextTozihat);
        databaseForHesabdari = new DatabaseForHesabdari(this);
        buttonSave = (Button) findViewById(R.id.btn_sign_Up);
        imageViewBack = (ImageView) findViewById(R.id.img_toolbar);
//        یک فونت اعمال می کنیم
        Typeface typeface= Typeface.createFromAsset(getAssets(), "BNazanin_0.ttf");
        editTextMablagh.setTypeface(typeface);
        editTextHesab.setTypeface(typeface);
        editTextDastebandi.setTypeface(typeface);
        editTextTarikh.setTypeface(typeface);
        editTextTozihat.setTypeface(typeface);


//        کد برای سه رقم جدا کردن editTextmablagh نوشتیم
        editTextMablagh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editTextMablagh.getText().toString().trim().isEmpty())
                {
                    return;
                }
                if (hasFocus==false)
                {
//                    مقدار داخل editTextmablagh را تبدیل به عدد میکند و داخل mablagh میریزد
                    Double mablagh = Double.parseDouble(editTextMablagh.getText().toString());
//                    این دستور برای جدا کردن عدد به صورت سه رقم سه رقم است
                    editTextMablagh.setText(NumberFormat.getNumberInstance(Locale.US).format(mablagh));
                }
                else {
                    String seperator =
                            editTextMablagh.getText().toString().replace(",","");
                    editTextMablagh.setText(seperator);
                }
            }
        });

//        رویداد کلیک برای Imageview
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





        editTextHesab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_daramad.this, HesabActivity.class);
                startActivityForResult(intent,1);

            }



        });

        editTextDastebandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_daramad.this,DastehayeKoliActivity.class);
                intent.putExtra("nd",nd);
                startActivityForResult(intent,2);
            }
        });



               editTextTarikh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                   @Override
                   public void onFocusChange(View v, boolean hasFocus) {
                      if (hasFocus)
                      {
                          new DatePicker.Builder()
                                  .id(1)
                                  .build(Activity_daramad.this)
                                  .show(getSupportFragmentManager(), "");
                      }
                   }
               });






//                برسی زیر تابع ها
                SubmitForm();
            }
        });
    }
    private void SubmitForm()
    {
//        برسی خالی بودن editetext ها
        if(!validateMablagh())
        {
            return;
        }
        if (!validateHesab())
        {
            return;
        }
        if (!validatedastebandi())
        {
            return;
        }
        if (!validatetarikh())
        {
            return;
        }
        databaseForHesabdari.addDH(Double.parseDouble(editTextMablagh.getText().toString().replace(",", "")), idHesab, idJozee,
                editTextTarikh.getText().toString(), editTextTozihat.getText().toString());
        Fragment_Koli.adapter.updateItem(databaseForHesabdari.readDH());

        if (nd.equals("Daramad"))
        {
            databaseForHesabdari.updateMojodi((editTextMablagh.getText().toString().replace(",", "")),idHesab);
            FragmentDaramad.adapter.updateItem(databaseForHesabdari.readDaramad());
        }else {
            Double mablagh = Double.parseDouble(editTextMablagh.getText().toString().replace(",", ""));
            mablagh = mablagh * -1;
            databaseForHesabdari.updateMojodi(mablagh.toString(),idHesab);
            FragmentHazine.adapter.updateItem(databaseForHesabdari.readHazine());
        }

//        در نهایت ثبت شدن و نشان دادن toast
        Toast.makeText(this, "ذخیره شد", Toast.LENGTH_SHORT).show();


//        حالا editText رو خالی میکنیم
        editTextMablagh.setText("");
        editTextHesab.setText("");
        editTextDastebandi.setText("");
        editTextTarikh.setText("");
        editTextTozihat.setText("");
//        و فوکوس کن بر روی editText مبلغ
        editTextMablagh.requestFocus();
//        برگرد به صفحه اصلی
        finish();
    }
    private boolean validateMablagh()
    {
        if (editTextMablagh.getText().toString().trim().isEmpty())
        {
            mablagh.setError(getString(R.string.err_msg_mablagh));
            editTextMablagh.requestFocus();
            return false;
        }else{
            mablagh.setErrorEnabled(false);
    }
    return true;
    }


    private boolean validateHesab()
    {
        if (editTextHesab.getText().toString().trim().isEmpty())
        {
            hesab.setError(getString(R.string.err_msg_hesab));
            editTextHesab.requestFocus();
            return false;
        }else{
            hesab.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validatedastebandi()
    {
        if (editTextDastebandi.getText().toString().trim().isEmpty())
        {
            dastebandi.setError(getString(R.string.err_msg_dastebandi));
            editTextDastebandi.requestFocus();
            return false;
        }else{
            dastebandi.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validatetarikh()
    {
        if (editTextTarikh.getText().toString().trim().isEmpty())
        {
            tarikh.setError(getString(R.string.err_msg_tarikh));
            editTextTarikh.requestFocus();
            return false;
        }else{
            tarikh.setErrorEnabled(false);
        }
        return true;
    }





    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        editTextTarikh.setText(year + "/" + month + "/" + day);

    }
}
