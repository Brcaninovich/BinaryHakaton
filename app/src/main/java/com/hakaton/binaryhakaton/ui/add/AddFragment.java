package com.hakaton.binaryhakaton.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hakaton.binaryhakaton.databinding.FragmentAddBinding;
import com.hakaton.binaryhakaton.databinding.FragmentDashboardBinding;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.dodatnaSvojstvaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.osnovneInfoSkrol.setVisibility(View.GONE);
                binding.dodatneInfoSkrolAutomobil.setVisibility(View.VISIBLE);
            }
        });

        binding.dodajSlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.dodatneInfoSkrolAutomobil.setVisibility(View.GONE);
                binding.dodatneSlikeSkrolAutomobil.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}