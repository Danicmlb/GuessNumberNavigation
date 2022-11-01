package com.example.guessnumbernavigation.iu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.guessnumbernavigation.R;
import com.example.guessnumbernavigation.data.GuessNumber;
import com.example.guessnumbernavigation.databinding.FragmentEndplayBinding;

/**
 * <h1>Projecto 03 - Guess Number Navigation</h1>
 * <h3>Fragment EndPlay</h3>
 *
 * Este fragment es simplemente la pantalla final del juego donde una vez terminado nos comunica si hemos ganado
 * o perdido y nos da información sobre la partida. Una vez que pulsemos volver a jugar nos limpiara la pila de fragmentos
 * y pasará al fragment de config, de esa forma no creamos un bucle y si pulsamos back, salimos de la aplicación.
 *
 * @author Daniel
 * @version 1.0.0
 */

public class FragmentEndPlay extends Fragment {

    public static final String TAG = "Fragment EndPlay";
    private FragmentEndplayBinding binding;
    private GuessNumber resultado;

    public FragmentEndPlay() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"FragmentEndPlay -> OnAttach()");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEndplayBinding.inflate(inflater);
        binding.setGuessNumber(FragmentEndPlayArgs.fromBundle(getArguments()).getGuessNumber());
        resultado = binding.getGuessNumber();

        if (resultado.victoria == true) {
            binding.tvResultadoFinalVictoria.setText("¡Has Ganado!");
            binding.tvResultadoExplicacion.setText("¡Enhorabuena " + resultado.getUser() + "! Has acertado el número, era " + resultado.getRandomNumber() + ". Lo has conseguido en " + resultado.getIntentos() + " / " + resultado.toString() + " intentos.");
        } else {
            binding.tvResultadoFinalVictoria.setText("¡Has Perdido!");
            binding.tvResultadoExplicacion.setText("Otra vez será " + resultado.getUser() + ", el número era " + resultado.getRandomNumber() + ".");
        }
        binding.btVolverAJugar.setOnClickListener( view ->NavHostFragment.findNavController(this).navigate(R.id.action_fragmentEndPlay_to_fragmentConfig));
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"FragmentEndPlay -> OnStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"FragmentEndPlay -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"FragmentEndPlay -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"FragmentEndPlay -> OnStop()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (binding != null) {
            outState.putParcelable(GuessNumber.KEY, binding.getGuessNumber());
        }
        Log.d(TAG,"FragmentEndPlay -> onSaveInstanceState()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            if (binding != null)
                binding.getGuessNumber();
        }
        Log.d(TAG,"FragmentEndPlay -> onViewStateRestored()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG,"FragmentEndPlay -> OnDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"FragmentEndPlay -> OnDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"FragmentEndPlay -> OnDetach()");
    }
}