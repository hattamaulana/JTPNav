package example.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import example.app.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        // Set untuk membuat menu (Help).
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Binding View
        // dan menambahkan event OnClick Listener.
        Button button = (Button) view.findViewById(R.id.navigate_destination_button);
        button.setOnClickListener(navigateStep);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Menampilkan menu 'nav_menu_home' ke view
        inflater.inflate(R.menu.nav_menu_home, menu);
    }

    // Ini merupakan listener dari
    // 'Button' onClick Listener
    private View.OnClickListener navigateStep =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Inisiasi dari Direction
                    // Object ini merupakan hasil generated dari navigation.xml.
                    HomeFragmentDirections.NextStep next = HomeFragmentDirections.nextStep();

                    // Set Argumen
                    next.setStepNumber(1);

                    // mengeksekusi untuk berpindah
                    // ke fragment yang dituju.
                    Navigation.findNavController(view)
                            .navigate(next);
                }
            };
}
