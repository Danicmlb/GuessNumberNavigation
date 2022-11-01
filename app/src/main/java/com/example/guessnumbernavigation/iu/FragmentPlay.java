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
import com.example.guessnumbernavigation.data.GuessNumber;
import com.example.guessnumbernavigation.databinding.FragmentPlayBinding;

/**
 * <h1>Projecto 03 - Guess Number Navigation</h1>
 * <h3>Fragment Play</h3>
 *
 * Este fragment es el del desarrollo del juego en interacción completa con el *POJO GuessNumber*
 * Además de mostrarnos la información del quien esta jugando y con cuantos intentos, nos dice si el número
 * a adivinar es mayor o menor y cuantos intentos nos quedan.
 *
 * @author Daniel
 * @version 1.0.0
 */

public class FragmentPlay extends Fragment {

    public final static String TAG = "Fragment Play";
    private FragmentPlayBinding binding;
    private GuessNumber guessNumber;

    public FragmentPlay() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"FragmentPlay -> OnAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG,"FragmentPlay -> OnCreate()");
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"FragmentPlay -> OnCreateView()");
        binding = FragmentPlayBinding.inflate(inflater);
        binding.setGuessNumber(FragmentPlayArgs.fromBundle(getArguments()).getGuessNumber());

        binding.btComprobar.setOnClickListener(view -> Comprobar());
        binding.btReintento.setOnClickListener(view -> Reintentar());
        binding.btReintento.setEnabled(false);

        guessNumber = binding.getGuessNumber();
        guessNumber.Aleatorio();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"FragmentPlay -> OnStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"FragmentPlay -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"FragmentPlay -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"FragmentPlay -> OnStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (binding != null) {
            outState.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
        }
        Log.d(TAG,"FragmentPlay -> onSaveInstaceState()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            if (binding != null)
                binding.getGuessNumber();
        }
        Log.d(TAG,"FragmentPlay -> onViewStateRestored()");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG,"FragmentPlay -> OnDestroyView()");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"FragmentPlay -> OnDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"FragmentPlay -> OnDetach()");
    }


    public void Comprobar() {
        if (binding.etNumber.getText().toString().isEmpty())
            Toast.makeText(getContext(), "Inserte datos", Toast.LENGTH_SHORT).show();
        else {
            if (Integer.parseInt(String.valueOf(binding.tvIntentos.getText())) > guessNumber.getIntentos()) {
                guessNumber.Incremento();
                if (Integer.parseInt(binding.etNumber.getText().toString()) > guessNumber.getRandomNumber()) {
                    binding.tvResultado.setText("El número a adivinar es menor, te quedan " + (Integer.parseInt(binding.tvIntentos.getText().toString()) - guessNumber.getIntentos()) + " intentos.");
                    binding.btComprobar.setEnabled(false);
                    binding.btReintento.setEnabled(true);
                }

                if (Integer.parseInt(binding.etNumber.getText().toString()) < guessNumber.getRandomNumber()) {
                    binding.tvResultado.setText("El número a adivinar es mayor, te quedan " + (Integer.parseInt(binding.tvIntentos.getText().toString()) - guessNumber.getIntentos()) + " intentos.");
                    binding.btComprobar.setEnabled(false);
                    binding.btReintento.setEnabled(true);
                }

                if (Integer.parseInt(binding.etNumber.getText().toString()) == guessNumber.getRandomNumber()){
                    guessNumber.victoria = true;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
                    NavHostFragment.findNavController(this).navigate(R.id.fragmentEndPlay, bundle);
                }
            }

            if (Integer.parseInt(String.valueOf(binding.tvIntentos.getText())) == guessNumber.getIntentos()) {
                guessNumber.victoria = false;
                Bundle bundle = new Bundle();
                bundle.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
                NavHostFragment.findNavController(this).navigate(R.id.fragmentEndPlay, bundle);
            }

        }
    }

    public void Reintentar(){
        binding.etNumber.setText("");
        binding.tvResultado.setText("");
        binding.btComprobar.setEnabled(true);
        binding.btReintento.setEnabled(false);

    }
}