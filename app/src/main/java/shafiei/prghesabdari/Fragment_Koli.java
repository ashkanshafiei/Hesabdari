package shafiei.prghesabdari;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Koli extends Fragment {
    //    در اینجا recyclerView و adapter رو معرفی میکنیم
    public static AdapterHesabdari adapter;
    RecyclerView recyclerView;
    DatabaseForHesabdari databaseForHesabdari;
    public Fragment_Koli(){
    }
    String nd = "Daramad";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__koli,container,false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        databaseForHesabdari = new DatabaseForHesabdari(getActivity());
        adapter = new AdapterHesabdari(getActivity() ,databaseForHesabdari.readDH());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
