package shafiei.prghesabdari;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseForHesabdari extends SQLiteOpenHelper {
    public DatabaseForHesabdari(@Nullable Context context) {
        super(context, "hdDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_hesab ( \n" +
                "    ID        INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                             NOT NULL\n" +
                "                             UNIQUE,\n" +
                "    NameHesab VARCHAR( 50 ),\n" +
                "    Mojodi    NUMERIC \n" +
                ");\n");

//        کارهایی زیر را انجام بده بر روی جدول

//        مقدار پیش فرض میدهیم به جدول
        db.execSQL("INSERT INTO tbl_hesab (NameHesab,Mojodi)VALUES('بانک سپه',0)");
        db.execSQL("INSERT INTO tbl_hesab (NameHesab,Mojodi)VALUES('کیف پول',0)");
        db.execSQL("INSERT INTO tbl_hesab (NameHesab,Mojodi)VALUES('بانک پاسارگاد',0)");
        db.execSQL("INSERT INTO tbl_hesab (NameHesab,Mojodi)VALUES('بانک صادرات',0)");
        db.execSQL("INSERT INTO tbl_hesab (NameHesab,Mojodi)VALUES('بانک شهر',0)");

        db.execSQL("CREATE TABLE tbl_dastehayeKoli ( \n" +
                "    ID                INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                                     NOT NULL\n" +
                "                                     UNIQUE,\n" +
                "    NameDastehayKoli VARCHAR( 50 ),\n" +
                "    NoeDastebandi     VARCHAR \n" +
                ");\n");

        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('سود','Daramad') ");
        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('دستمزد','Daramad') ");
        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('فروش','Daramad') ");
        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('کرایه','Hazine') ");
        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('خرید','Hazine') ");
        db.execSQL("INSERT INTO tbl_dastehayeKoli (NameDastehayKoli,NoeDastebandi) VALUES('اجاره','Hazine') ");


        db.execSQL("CREATE TABLE tbl_dastehayeJozee ( \n" +
                "    IDJozee                 INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                                      NOT NULL\n" +
                "                                      UNIQUE,\n" +
                "    NameDastehayeJozee VARCHAR( 50 ),\n" +
                "    ID_DastehayeKoli   INTEGER        NOT NULL\n" +
                "                                      REFERENCES tbl_dastehayeKoli ( ID ) \n" +
                ");\n" +
                "\n");

        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('شرکت',1)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('بانک',1)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('مغازه',1)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('ساختمان',2)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('آموزشگاه',2)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('کارخانه',2)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('محصول1',3)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('محصول2',3)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('محصول3',3)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('تاکسی',4)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('اتوبوس',4)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('مترو',4)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('غذا',5)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('لباس',5)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('نوشیدنی',5)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('آپارتمان',6)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('مغازه',6)");
        db.execSQL("INSERT INTO tbl_dastehayeJozee (NameDastehayeJozee,ID_DastehayeKoli) VALUES ('شرکت',6)");


        db.execSQL("CREATE VIEW View_Koli AS\n" +
                "       SELECT *\n" +
                "         FROM tbl_dastehayeKoli\n" +
                "              INNER JOIN tbl_dastehayeJozee\n" +
                "        WHERE tbl_dastehayeKoli.ID = tbl_dastehayeJozee.ID_dastehayeKoli;");


        db.execSQL("CREATE TABLE tbl_DH ( \n" +
                "    ID                         INTEGER         PRIMARY KEY AUTOINCREMENT\n" +
                "                                               NOT NULL\n" +
                "                                               UNIQUE,\n" +
                "    Mablagh                    NUMERIC,\n" +
                "    ID_ForreignKeyHesab        INTEGER         REFERENCES tbl_hesab ( ID ) ON DELETE CASCADE,\n" +
                "    ID_ForengnKeyDastehayJozee INTEGER         REFERENCES tbl_dastehayeJozee ( ID ) ON DELETE CASCADE,\n" +
                "    Tarikh                     DATE,\n" +
                "    tozihat                    VARCHAR( 140 ) \n" +
                ");\n" +
                "\n");

        db.execSQL("CREATE VIEW View_DH AS\n" +
                "       SELECT *\n" +
                "         FROM tbl_DH\n" +
                "              INNER JOIN View_Koli\n" +
                "        WHERE tbl_DH.ID_ForengnKeyDastehayJozee = View_Koli.IDJozee;\n");

        db.execSQL("CREATE VIEW View_daramad AS\n" +
                "       SELECT *\n" +
                "         FROM View_DH\n" +
                "              INNER JOIN tbl_hesab\n" +
                "        WHERE View_DH.ID_ForreignKeyHesab = tbl_Hesab.ID;\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //    لیستی از حساب آیتم را برای ما select میکند
    public List<HesabItem> redaHesab() {
        List<HesabItem> list=new ArrayList<HesabItem>();
        String sql="SELECT * FROM tbl_Hesab";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                HesabItem hesabItem=new HesabItem();
                hesabItem.ID=cursor.getInt(cursor.getColumnIndex("ID"));
                hesabItem.nameHesab=cursor.getString(cursor.getColumnIndex("NameHesab"));
                hesabItem.Mojodi=cursor.getDouble(cursor.getColumnIndex("Mojodi"));
                list.add(hesabItem);

            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean addHesab(String nameHesab, Double mojodi) {
        boolean result;
//        یک مقدار را از کاربر بگیر و ذخیره کن
        String sql="INSERT INTO tbl_hesab (NameHesab,Mojodi) VALUES ('" + nameHesab + "'," + mojodi + ")";
        try {
            SQLiteDatabase database=this.getWritableDatabase();
//        اس کسو ال را اجرا کم
            database.execSQL(sql);
//        لاین ایشغال کرده را خالی کن
            database=null;
            result=true;
        } catch (Exception ex) {
//            اگر خطایی رخ داد برای ما false برگردان
            result=false;
        }
        return result;
    }

    // کد مربوط به quri حذف حساب
    public boolean deleteHesab(Integer id) {
        boolean result;
        try {

            String sql="DELETE FROM tbl_hesab WHERE ID = " + id;
            SQLiteDatabase database=this.getWritableDatabase();
            database.execSQL(sql);
            database.close();
            result=true;
        } catch (Exception ex) {
            result=false;
        }
        return result;
    }

    //    اینجا quri مربوط به update حساب را مینویسیم
    public boolean updateHesab(String nameHesab, Integer id) {
        boolean result;
        String sql="UPDATE tbl_hesab" +
                " SET NameHesab = '" + nameHesab + "'" +
                "WHERE ID = " + id;
        try {

            SQLiteDatabase database=this.getWritableDatabase();
            database.execSQL(sql);
            database.close();
            result=true;
        } catch (Exception ex) {
            result=false;
        }
        return result;
    }

    public List<DastebandiItem> redaDastebandi(String noeDastebandi) {
        List<DastebandiItem> list=new ArrayList<>();
        String sql="SELECT ID,NameDastehayKoli FROM tbl_dastehayeKoli WHERE NoeDastebandi = '" + noeDastebandi + "'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Integer id=cursor.getInt(cursor.getColumnIndex("ID"));
                String namedastebandi=cursor.getString(cursor.getColumnIndex("NameDastehayKoli"));
                DastebandiItem d=new DastebandiItem();
                d.id=id;
                d.nameHesab=namedastebandi;
                list.add(d);

            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<DastebandiItem> readDastehayeJozee(String ID_Dastehaye_Koli) {
        List<DastebandiItem> list=new ArrayList<>();
        String sql="SELECT IDJozee ,NameDastehayeJozee FROM tbl_dastehayeJozee WHERE ID_DastehayeKoli = '" + ID_Dastehaye_Koli + "'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Integer id=cursor.getInt(cursor.getColumnIndex("IDJozee"));
                String nameDastebandi=cursor.getString(cursor.getColumnIndex("NameDastehayeJozee"));
                DastebandiItem item=new DastebandiItem();
                item.id=id;
                item.nameHesab=nameDastebandi;
                list.add(item);
            } while (cursor.moveToNext());

        }
        return list;
    }

    public String readView(String idJozee) {
        String nameDastehayeJozeeKoli="";
        String sql="SELECT NameDastehayKoli , NameDastehayeJozee FROM View_Koli WHERE IDJozee = '" + idJozee + "'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                nameDastehayeJozeeKoli=
                        cursor.getString(cursor.getColumnIndex("NameDastehayKoli"))
                                + "-"
                                + cursor.getString(cursor.getColumnIndex("NameDastehayeJozee"));

            } while (cursor.moveToNext());
        }
        return nameDastehayeJozeeKoli;
    }

    public boolean addDH(Double mablagh, String idHesab, String idJozee, String tarikh, String tozihat) {
        boolean result;
        try {


            String sql="INSERT INTO tbl_DH (Mablagh , ID_ForreignKeyHesab  ,  ID_ForeignKeyDasthayeJozee , tarikh , tozihat )" +
                    " VALUES ('" + mablagh + "','" + idHesab + "' , '" + idJozee + "','" + tarikh + "' , '" + tarikh + "' )";
            SQLiteDatabase database=this.getWritableDatabase();
            database.execSQL(sql);
            database.close();
            result=true;
        } catch (Exception ex) {
            result=false;
        }
        return result;
    }

    public ItemAdapter readDH() {
        ItemAdapter itemAdapter=new ItemAdapter();
        String sql="SELECT Mablagh , NameHesab, NameDastehayKoli ,NameDastehayeJozee, tarikh FROM View_daramad";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            do {
                itemAdapter.mablaghForData.add(cursor.getDouble(cursor.getColumnIndex("Mablagh")));
                itemAdapter.hesabForData.add(cursor.getString(cursor.getColumnIndex("NameHesab")));
                itemAdapter.dastebandiForData.add(cursor.getString(cursor.getColumnIndex("NameDastehayeKoli"))
                        + "-" + cursor.getString(cursor.getColumnIndex("NameDastehayeJozee"))
                );
                itemAdapter.tarikhForData.add(cursor.getString(cursor.getColumnIndex("tarikh")));

            } while (cursor.moveToNext());
        }
        return itemAdapter;
    }

    public ItemAdapter readDaramad() {
        ItemAdapter itemAdapter=new ItemAdapter();
        String sql="SELECT Mablagh , NameHesab, NameDastehayKoli ,NameDastehayeJozee, tarikh FROM View_daramad WHERE NoeDastebandi = 'Daramad' ";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            do {
                itemAdapter.mablaghForData.add(cursor.getDouble(cursor.getColumnIndex("Mablagh")));
                itemAdapter.hesabForData.add(cursor.getString(cursor.getColumnIndex("NameHesab")));
                itemAdapter.dastebandiForData.add(cursor.getString(cursor.getColumnIndex("NameDastehayeKoli"))
                        + "-" + cursor.getString(cursor.getColumnIndex("NameDastehayeJozee"))
                );
                itemAdapter.tarikhForData.add(cursor.getString(cursor.getColumnIndex("tarikh")));

            } while (cursor.moveToNext());
        }
        return itemAdapter;
    }

    public ItemAdapter readHazine() {
        ItemAdapter itemAdapter=new ItemAdapter();
        String sql="SELECT Mablagh , NameHesab, NameDastehayKoli ,NameDastehayeJozee, tarikh FROM View_daramad WHERE NoeDastebandi = 'Hazine' ";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            do {
                itemAdapter.mablaghForData.add(cursor.getDouble(cursor.getColumnIndex("Mablagh")));
                itemAdapter.hesabForData.add(cursor.getString(cursor.getColumnIndex("NameHesab")));
                itemAdapter.dastebandiForData.add(cursor.getString(cursor.getColumnIndex("NameDastehayeKoli"))
                        + "-" + cursor.getString(cursor.getColumnIndex("NameDastehayeJozee"))
                );
                itemAdapter.tarikhForData.add(cursor.getString(cursor.getColumnIndex("tarikh")));

            } while (cursor.moveToNext());
        }
        return itemAdapter;
    }

    public boolean updateMojodi(String mojodi, String id) {
        String sql="UPDATE tbl_hesab SET Mojodi = Mojodi + '" + mojodi + "' WHERE id = '" + id + "'";
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(sql);
        database.close();
        return true;
    }

    public Double readMojodiKol() {
        Double result=0d;
        String sql="SELECT SUM (Mojodi) FROM tbl_hesab";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            result=cursor.getDouble(cursor.getColumnIndex("SUM (Mojodi)"));
        }
        return result;
    }

    public Double readMojodiDaramad() {
        Double result=0d;
        String sql="SELECT SUM (Mablagh) FROM view_daramad Where NoeDastebandi = 'Daramad' ";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            result=cursor.getDouble(cursor.getColumnIndex("SUM (Mablagh)"));
        }
        return result;
    }

    public Double readMojodiHazine() {
        Double result=0d;
        String sql="SELECT SUM (Mablagh) FROM view_daramad Where NoeDastebandi = 'Hazine' ";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            result=cursor.getDouble(cursor.getColumnIndex("SUM (Mablagh)"));
        }
        return result;
    }

    public Double readDaramadHesab(String azTarikh, String taTarikh, String idHesab) {
        Double result=0d;

        String sql="SELECT SUM(Mablagh) From view_daramad Where ID_ForreignKeyHesab = " + idHesab
                + " and tarikh between '" + azTarikh + "' and '" + taTarikh + "' and NoeDastebandi = 'Daramad'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            result=cursor.getDouble(0);
        }
        return result;
    }

    public Double readHazineHesab(String azTarikh, String taTarikh, String idHesab) {
        Double result=0d;

        String sql="SELECT SUM(Mablagh) From view_daramad Where ID_ForreignKeyHesab = " + idHesab
                + " and tarikh between '" + azTarikh + "' and '" + taTarikh + "' and NoeDastebandi = 'Hazine'";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            result=cursor.getDouble(0);
        }
        return result;
    }

}