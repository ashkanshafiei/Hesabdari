package shafiei.prghesabdari;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDaramad extends Fragment {
//    در اینجا recyclerView و adapter رو معرفی میکنیم
    public static AdapterHesabdari adapter;
    RecyclerView recyclerView;
    DatabaseForHesabdari databaseForHesabdari;
public FragmentDaramad(){
}
    String nd = "Daramad";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daramad,container,false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext() , Activity_daramad.class);
                i.putExtra("nd",nd);
                startActivity(i);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        databaseForHesabdari = new DatabaseForHesabdari(getActivity());
        adapter = new AdapterHesabdari(getActivity() ,databaseForHesabdari.readDaramad());
        recyclerView.setAdapter(adapter);
        TextView tv = view.findViewById(R.id.tv);
        if (recyclerView == null)
        {
            tv.setVisibility(view.VISIBLE);
        }else {
            tv.setVisibility(view.INVISIBLE);
        }
        return view;
    }
}
