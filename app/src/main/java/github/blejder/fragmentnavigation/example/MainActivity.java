package github.blejder.fragmentnavigation.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_view);

        NavigationFragment fragment = NavigationFragment.newInstance(new Depth());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setPrimaryNavigationFragment(fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
