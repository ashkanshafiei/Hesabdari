package shafiei.prghesabdari;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSetting extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        Button button =  (Button) view.findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                FragmentDaramad  fragment= new FragmentDaramad();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = null;
                if (fragmentManager != null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                }
                if (fragmentTransaction != null) {
                    fragmentTransaction.replace(R.id.fragmentdaramad,fragment);
                }
                if (fragmentTransaction != null) {
                    fragmentTransaction.commit();
                }
                if (fragmentTransaction != null) {
                    fragmentTransaction.hide(FragmentSetting.this);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
