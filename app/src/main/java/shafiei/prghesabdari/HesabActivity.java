package shafiei.prghesabdari;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class HesabActivity extends AppCompatActivity {
    ListView lstHesab;
//    موارد داخل layout و data ها را رندر کند در listview
    ArrayAdapter<String> adapter;
    List<HesabItem> list;
    DatabaseForHesabdari db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesab);

        lstHesab = (ListView) findViewById(R.id.lst_hesab);
        db = new DatabaseForHesabdari(this);
        bindHesab();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog dialog = new MaterialDialog.Builder(HesabActivity.this)
                        .customView(R.layout.dialog_hesab_row,true)
                        .title(R.string.dialog_hesab_title)
                        .show();
//                ویجیت های داخل layout مربوط به dialog را شناسایی میکنیم
                final TextInputLayout inputLayoutHesabJadid = (TextInputLayout) dialog.findViewById(R.id.inputHesabJadid);
                TextInputLayout inputLayoutMojodi = (TextInputLayout) dialog.findViewById(R.id.inputMojodiJadid);
                final EditText editTextHesabJadid = (EditText) dialog.findViewById(R.id.editTextHesabJadid);
                final EditText editTextMojodi = (EditText) dialog.findViewById(R.id.editTextMojodiJadid);
                ImageView img_OK = (ImageView) dialog.findViewById(R.id.image_OK);
                ImageView img_Cancel = (ImageView) dialog.findViewById(R.id.image_Cancel);

//                برای دکمه OK یک رویداد کلیل ثبت میکنیم
                img_OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTextHesabJadid.getText().toString().isEmpty())
                        {
                            inputLayoutHesabJadid.setError(getString(R.string.err_dialog_hesab));
                            editTextHesabJadid.requestFocus();
                            return;
                        }else {
                            inputLayoutHesabJadid.setErrorEnabled(false);
                        }
//                        مقدار پیشفرض موجودی را صفر قرار بده
                        Double mojodi = 0d;
                        try {
                            mojodi = Double.parseDouble(editTextMojodi.getText().toString());
                        }catch (Exception ex)
                        {

                        }
//                        دیتابیس را معرفی میکنیم
                        db.addHesab(editTextHesabJadid.getText().toString(),mojodi);

                        bindHesab();
//                        دیالوگ بسته شود
                        dialog.dismiss();
                    }
                });
                img_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });




        lstHesab.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                final String nameHesab = list.get(position).nameHesab;
                MaterialDialog dialog = new MaterialDialog.Builder(HesabActivity.this)
                        .title(nameHesab)
                        .titleGravity(GravityEnum.END)
                        .negativeText("ویرایش")
                        .positiveText("حذف")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                final MaterialDialog updateDialog = new MaterialDialog.Builder(HesabActivity.this)
                                        .title(nameHesab)
                                        .titleGravity(GravityEnum.END)
                                        .customView(R.layout.update_dialog,true)
                                        .show();
                                final EditText editTextUpdate = (EditText) updateDialog.findViewById(R.id.updateeditTextHesabJadid);
                                final EditText editTextMojodi =( EditText ) updateDialog.findViewById(R.id.updateeditTextMojodiJadid);
                                String mojodi = list.get(position).Mojodi.toString();
                                editTextMojodi.setText(mojodi);
                                editTextUpdate.setText(nameHesab);
                                ImageView ok = (ImageView) updateDialog.findViewById(R.id.update_image_OK);
                                ImageView cancel = (ImageView) updateDialog.findViewById(R.id.update_image_Cancel);
                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Integer id = list.get(position).ID;
                                        db.updateHesab(editTextUpdate.getText().toString(), id);
                                        bindHesab();
                                        updateDialog.dismiss();
                                    }
                                });
                            }
                        })








//                        عملیات حذف حساب
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                موقعیتی که کاربر ارسال کرده را بخوان و از داخل آن positive آی دی را برای ما استخراج کن و در id بریز
                                Integer id = list.get(position).ID;
//                                        حال آن id را به اینجا پاس میدهیم تا حذفش کنه
                                db.deleteHesab(id);
//                                مجدد تمام دیتابیس را در listview بارگزاری کن
                                bindHesab();
                            }
                        })
                        .show();
                return false;
            }
        });


//        در اینجا nameHesab را به اکتیویتی ارسال میکنیم تا در editText قرار بگیرد
        lstHesab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameHesab = list.get(position).nameHesab.toString();
                String idHesab = list.get(position).ID.toString();
                Intent intent = new Intent();
                intent.putExtra("nameHesab",nameHesab);
                intent.putExtra("idHesab" , idHesab);
                setResult(1,intent);
                finish();
            }
        });

    }
//    وظیفه این متد وصل کردن Data به ListView است
    public void bindHesab()
    {
//        دیتابیس را انتقال میدهیم به این شیِء list
        list = db.redaHesab();
        ArrayList<String> DataForList = new ArrayList<String>();
//        این حلقه تا زمانی که data وجود داشته باشه میچرخه و به لیست ما اضافه میکنه
        for (int i = 0;i<list.size();i++)
        {
            HesabItem h =list.get(i);
            DataForList.add(h.nameHesab);
        }
        adapter = new ArrayAdapter<String>(this , R.layout.hesab_row,DataForList);
        lstHesab.setAdapter(adapter);
    }
}
