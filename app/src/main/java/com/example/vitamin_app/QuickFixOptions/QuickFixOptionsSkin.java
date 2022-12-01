package com.example.vitamin_app.QuickFixOptions;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vitamin_app.R;
import com.example.vitamin_app.recyclerAdapter;

public class QuickFixOptionsSkin extends Fragment {
    public QuickFixOptionsSkin() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quick_fix_recycler, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvVitamins);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter rvAdapter = new recyclerAdapter(this.getContext(), "Skin");
        rv.setAdapter(rvAdapter);
        return v;
    }
}