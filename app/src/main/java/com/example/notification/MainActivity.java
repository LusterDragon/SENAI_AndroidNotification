package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Criando o ovjeto que representa do serviço de notificação do android
    NotificationManager service;

    //Código inteiro para representar a nossa notificação no serviço
    private final int cod_notificacao = 8795;

    //Endereço da notificação para o canal de notificação (Android 8 e acima);
    private final String iD_CANAL = "canalAppNotificacao";

    //Método para verificar qual a versão do Android, e caso seja s versão 8 (Oreo) ou acima, irá criar o canal da notificação
    private void criaCanalNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //criação de um nome, descrição e prioridade do canal
            CharSequence nome = "nomeCanal";
            String descricacao = "descrição do Canal App Notification";
            int prioridade = NotificationManager.IMPORTANCE_DEFAULT;
            //Criação do canal de notificação
            NotificationChannel canal = new NotificationChannel(
                    iD_CANAL, //ID criado no inicio da classe
                    nome, prioridade);
            canal.setDescription(descricacao);
            //Acessar o serviço de notificação do Android e atribuir o nosso canal
            NotificationManager servico = getSystemService(NotificationManager.class);
            //Adicionar o nosso canal ao serviço do Android
            servico.createNotificationChannel(canal);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criaCanalNotificacao();
        Button btnNoficar = findViewById(R.id.btnClickME);
        btnNoficar.setOnClickListener((v -> {
            //Inciando  objeto do "serviço" e acessar o serviço de notificação  Android
            service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //criar uma intent para indicar qual o recurso será aberto ao acesso da noticação
            Intent intent = new Intent(MainActivity.this, Tela2.class);
            intent.putExtra("texteExtra", "abrirTela2");
            //Transformar a intent em PendingIntent que ficará em espera
            PendingIntent n = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //Montando a notificação
            NotificationCompat.Builder notificacao = new NotificationCompat.Builder(MainActivity.this,iD_CANAL)
                    .setContentTitle("Título da notificação").setContentText("texto que vai na parte intera da notificação")
                    .setAutoCancel(true).setContentIntent(n).setSmallIcon(R.drawable.icone_notificacao);
            //Exibir a notificação
            service.notify(cod_notificacao,notificacao.build());
        }));

    }
}