package com.example.guessnumbernavigation.iu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.guessnumbernavigation.R;
import com.example.guessnumbernavigation.databinding.FragmentConfigBinding;
import com.example.guessnumbernavigation.data.GuessNumber;

/**
 * <h1>Projecto 03 - Guess Number Navigation</h1>
 * <h3>Fragment Config</h3>
 *
 * Este fragmento es el encargado de recoger los datos de configuración de la partida
 * Tanto nombre de usuario como número de intentos.
 *
 * @author Daniel
 * @version 1.0.0
 */

public class FragmentConfig extends Fragment {

    public final static String TAG = "Fragment Config";
    private FragmentConfigBinding binding;

    public FragmentConfig() {

        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"FragmentConfig -> OnAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"FragmentConfig -> OnCreate()");}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"FragmentConfig -> OnCreateView()");
        binding= FragmentConfigBinding.inflate(inflater);
        binding.setGuessNumber(new GuessNumber());
        binding.btSendConfig.setOnClickListener(  view -> sendNumber());

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"FragmentConfig -> OnStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"FragmentConfig -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"FragmentConfig -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"FragmentConfig -> OnStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (binding != null) {
            outState.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
        }
        Log.d(TAG,"FragmentConfig -> onSaveInstanceState()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            if (binding != null)
                binding.getGuessNumber();
        }
        Log.d(TAG,"FragmentConfig -> onViewStateRestored()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG,"FragmentConfig -> OnDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"FragmentConfig -> OnDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"FragmentConfig -> OnDetach()");
    }

    public void sendNumber() {
        {
            if (binding.etUser.getText().toString().isEmpty() || binding.etIntentos.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Inserte Datos", Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
                NavHostFragment.findNavController(this).navigate(R.id.fragmentPlay, bundle); }
        }
    }
}