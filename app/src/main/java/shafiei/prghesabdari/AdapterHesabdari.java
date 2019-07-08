package shafiei.prghesabdari;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdapterHesabdari extends RecyclerView.Adapter<AdapterHesabdari.HesabdariViewHolder>{
    Context context;
    ItemAdapter itemAdapter;

    public AdapterHesabdari (Context context , ItemAdapter itemAdapter)
    {
        this.itemAdapter = itemAdapter;
        this.context = context;
    }

    @NonNull
    @Override
    public HesabdariViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        لایوت خود را به Adapter معرفی میکنیم
        return new HesabdariViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HesabdariViewHolder holder, int position) {
        holder.mablaghForLayout.setText(String.valueOf(itemAdapter.getMablagh(position)));
        holder.hesabForLayout.setText(itemAdapter.getHesab(position));
        holder.dastebandiForLayout.setText(itemAdapter.getdastebandi(position));
        holder.tarikhForLayout.setText(itemAdapter.getTarikh(position));

    }

    @Override
//    تعداد سطر هایی که قرار است return شود
    public int getItemCount() {
        return itemAdapter.mablaghForData.size();
    }

    //    ساخت ViewHolder
    class HesabdariViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView mablaghForLayout;
        TextView hesabForLayout;
        TextView dastebandiForLayout;
        TextView tarikhForLayout;
        public HesabdariViewHolder(View itemView) {
            super(itemView);
            this.mablaghForLayout = (TextView) itemView.findViewById(R.id.mablagh_recycle);
            this.hesabForLayout = (TextView) itemView.findViewById(R.id.hesab_recycle);
            this.dastebandiForLayout = (TextView) itemView.findViewById(R.id.dastebandi_recycle);
            this.tarikhForLayout = (TextView) itemView.findViewById(R.id.tarikh_recycle);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
    public void updateItem (ItemAdapter itemAdapter)
    {
        this.itemAdapter = itemAdapter;
        this.notifyDataSetChanged();
    }
}
