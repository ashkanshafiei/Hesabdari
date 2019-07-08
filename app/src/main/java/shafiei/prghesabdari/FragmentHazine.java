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

public class FragmentHazine extends Fragment {
    public FragmentHazine(){
    }
    public static AdapterHesabdari adapter;
    RecyclerView recyclerView;
    DatabaseForHesabdari databaseForHesabdari;
    String nd = "Hazine";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daramad,container,false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_daramad.class);
                intent.putExtra("nd",nd);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        databaseForHesabdari = new DatabaseForHesabdari(getActivity());
        adapter = new AdapterHesabdari(getActivity() ,databaseForHesabdari.readHazine());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
