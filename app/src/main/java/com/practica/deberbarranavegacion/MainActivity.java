package com.practica.deberbarranavegacion;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navView;
    private Menu menu;
    private Fragment1 fragment;
    private DrawerLayout drawerLayout;
    private MenuItem selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupToolbar();
        setupNavigationView();
        createDynamicMenu();
        displayInitialFragment();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.barranav);
    }

    private void setupToolbar() {
        toolbar.setTitle("Mi Aplicación");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupNavigationView() {
        navView.setNavigationItemSelectedListener(this);
        menu = navView.getMenu();
    }

    private void createDynamicMenu() {
        SubMenu GmailSubMenu = menu.addSubMenu("GMAIL");

        MenuItem ItemTodos = GmailSubMenu.add(Menu.NONE, 1, 1, "Todos los correos");
        ItemTodos.setIcon(R.drawable.ic_correos);

        MenuItem ItemPrincipal = GmailSubMenu.add(Menu.NONE, 2, 2, "Principal");
        ItemPrincipal.setIcon(R.drawable.inbox);

        MenuItem ItemSocial = GmailSubMenu.add(Menu.NONE, 3, 3, "Promociones");
        ItemSocial.setIcon(R.drawable.ic_promociones);

        MenuItem ItemPromociones = GmailSubMenu.add(Menu.NONE, 4, 4, "Social");
        ItemPromociones.setIcon(R.drawable.ic_social);

        SubMenu labelsSubMenu = menu.addSubMenu("Todas las etiquetas");


        MenuItem ItemDestacados = labelsSubMenu.add(Menu.NONE, 5, 5, "Destacados");
        ItemDestacados.setIcon(R.drawable.ic_destacados);

        MenuItem ItemImportantes = labelsSubMenu.add(Menu.NONE, 6, 6, "Importantes");
        ItemImportantes.setIcon(R.drawable.ic_importan);

        MenuItem ItemEnviados = labelsSubMenu.add(Menu.NONE, 7, 7, "Enviados");
        ItemEnviados.setIcon(R.drawable.ic_enviados);

        MenuItem ItemProgramados = labelsSubMenu.add(Menu.NONE, 8, 8, "Programados");
        ItemProgramados.setIcon(R.drawable.ic_eprogramados);

        MenuItem ItemBandejaSalida = labelsSubMenu.add(Menu.NONE, 8, 8, "Bandeja de salida");
        ItemBandejaSalida.setIcon(R.drawable.ic_salida);


    }


    private void displayInitialFragment() {
        fragment = new Fragment1();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenido_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        clearSelectedItem();
        selectItem(item);
        updateToolbarTitle(item);
        updateFragmentContent(item);
        closeDrawer();
        return true;
    }

    private void clearSelectedItem() {
        if (selectedItem != null) {
            selectedItem.setChecked(false);
        }
    }

    private void selectItem(MenuItem item) {
        item.setChecked(true);
        selectedItem = item;
    }

    private void updateToolbarTitle(MenuItem item) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(item.getTitle());
        }
    }

    private void updateFragmentContent(MenuItem item) {
        String content = "Contenido del Item " + item.getTitle();
        fragment.setContent(content);
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}