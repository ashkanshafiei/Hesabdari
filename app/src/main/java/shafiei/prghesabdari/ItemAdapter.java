package shafiei.prghesabdari;

import java.util.ArrayList;

public class ItemAdapter {
    public ArrayList<Double> mablaghForData = new ArrayList<Double>();
    public ArrayList<String> hesabForData = new ArrayList<String>();
    public ArrayList<String> dastebandiForData = new ArrayList<String>();
    public ArrayList<String> tarikhForData = new ArrayList<String>();
    public ArrayList<String> Tozihat = new ArrayList<String>();

    public void addItems(Double mablagh , String Hesab , String dastebandi , String tarikh , String tozihat) {
        mablaghForData.add(mablagh);
        hesabForData.add(Hesab);
        dastebandiForData.add(dastebandi);
        tarikhForData.add(tarikh);
        Tozihat.add(tozihat);
    }
    public Double getMablagh (int position)
    {
        return mablaghForData.get(position);
    }
    public String getHesab (int position)
    {
        return hesabForData.get(position);
    }
    public String getdastebandi (int position)
    {
        return dastebandiForData.get(position);
    }
    public String getTarikh (int position)
    {
        return tarikhForData.get(position);
    }
    public String gettozihat (int position)
    {
        return Tozihat.get(position);
    }
}






