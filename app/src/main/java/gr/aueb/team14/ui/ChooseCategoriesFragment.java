package gr.aueb.team14.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gr.aueb.team14.R;


public class ChooseCategoriesFragment extends Fragment {

    public ChooseCategoriesFragment() {
        // Required empty public constructor
    }

    public static ChooseCategoriesFragment newInstance(String param1, String param2) {
        return new ChooseCategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_categories, container, false);
    }
}