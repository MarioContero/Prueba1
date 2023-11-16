package com.example.evaluacion1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.evaluacion1.network.DigimonApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DigimonApiService apiService;
    private TextView nameTextView, levelTextView;
    private ImageView digimonImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://digimon-api.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(DigimonApiService.class);


        nameTextView = findViewById(R.id.nameTextView);
        levelTextView = findViewById(R.id.levelTextView);
        digimonImageView = findViewById(R.id.digimonImageView);


        Call<List<Digimon>> call = apiService.getDigimons();
        call.enqueue(new Callback<List<Digimon>>() {
            @Override
            public void onResponse(Call<List<Digimon>> call, Response<List<Digimon>> response) {
                if (response.isSuccessful()) {
                    List<Digimon> digimons = response.body();

                    if (digimons != null && !digimons.isEmpty()) {
                        Digimon firstDigimon = digimons.get(0);

                        nameTextView.setText("Nombre: " + firstDigimon.getName());
                        levelTextView.setText("Nivel: " + firstDigimon.getLevel());

                    } else {
                        Toast.makeText(MainActivity.this, "No se encontraron Digimons", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Digimon>> call, Throwable t) {
                Log.e("MainActivity", "Error de conexión", t);
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
