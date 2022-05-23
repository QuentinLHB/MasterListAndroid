package com.example.masterlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.masterlist.R;
import com.example.masterlist.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {

    private FragmentMainMenuBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnHandleMasterLists.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), HandleMasterListsActivity.class);
            getActivity().startActivity(intent);
        });

        binding.btnCreateErrandList.setOnClickListener(v -> NavHostFragment.findNavController(MainMenuFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}