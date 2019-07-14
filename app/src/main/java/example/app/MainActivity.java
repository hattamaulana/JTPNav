package example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =
            MainActivity.class.getName();

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding toolbar dan
        // set toolbar ke drawer view
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Binding Fragment ke variabel NavHostFragment
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentMain);

        // Mendapatkan Navigation Controller
        mNavController = Objects.requireNonNull(host)
                .getNavController();

        // setup action bar
        setupActionBar();

        // setup navigation view
        // untuk layout landscape
        setupNavView();

        // setup bottom navigation menu
        // untuk layout potrait
        setupBottomNavMenu();

        // menambahkan destinasi ch
        mNavController.addOnDestinationChangedListener(destinationAction);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        NavigationView nav = findViewById(R.id.nav_view);

        if (nav == null) {
            getMenuInflater().inflate(R.menu.setting_menu, menu);

            return true;
        }

        return result;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected: "+ item.getItemId());
        return NavigationUI.onNavDestinationSelected(item, mNavController) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(findViewById(R.id.fragmentMain))
                .navigateUp();
    }

    private NavController.OnDestinationChangedListener destinationAction =
            new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(
                        @NonNull NavController controller,
                        @NonNull NavDestination destination,
                        @Nullable Bundle arguments
                ) {
                    String dest = getResources().getResourceName(destination.getId());

                    Log.i(TAG, "onDestinationChanged: "+ dest);
                }
            };

    private void setupBottomNavMenu() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_view);

        if (navigationView != null) NavigationUI
                .setupWithNavController(navigationView, mNavController);
    }

    private void setupNavView() {
        NavigationView nav = findViewById(R.id.nav_view);

        if (nav != null) NavigationUI
                .setupWithNavController(nav, mNavController);
    }

    private void setupActionBar() {
        NavigationUI.setupActionBarWithNavController(MainActivity.this, mNavController);
    }

}
