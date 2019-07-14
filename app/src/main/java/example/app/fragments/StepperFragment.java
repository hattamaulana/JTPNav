package example.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import example.app.R;

public class StepperFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        // set untuk membuat icon tab back.
        setHasOptionsMenu(true);

        // menerima argument yang dikirimkan
        // dari view sebelumnya atau default argument
        // yang didefinisikan di navigation.
        int stepNumber = getArguments().getInt("StepNumber") == 1 ?
                R.layout.fragment_step_one : R.layout.fragment_step_two;

        return inflater.inflate(stepNumber, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Binding View
        // dan menambahkan OnClick Listener
        Button button = (Button) view.findViewById(R.id.navigate_destination_button);
        button.setOnClickListener(
                // menjalankan action pada id next_step di navigation
                Navigation.createNavigateOnClickListener(R.id.next_step)
        );
    }
}
