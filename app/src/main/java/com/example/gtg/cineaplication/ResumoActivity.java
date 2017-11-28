package com.example.gtg.cineaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gtg.cineaplication.DB.IngressoBD;
import com.example.gtg.cineaplication.modelo.Filme;
import com.example.gtg.cineaplication.modelo.Ingresso;
import com.example.gtg.cineaplication.modelo.Sessao;

public class ResumoActivity extends AppCompatActivity {
    private TextView lblFilmeComprado;
    private TextView lblNumeroSala;
    private TextView lblDescricaoHora;
    private TextView lblQtdInteira;
    private TextView lblPrecoInteira;
    private TextView lblQtdMeia;
    private TextView lblPrecoMeia;
    private TextView lblQtdPipocaRefri;
    private TextView lblPrecoPipocaRefri;
    private TextView lblValorTotal;
    private Filme filme;
    private Sessao sessao;
    private Ingresso ingresso;
    private IngressoBD ingressoBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        lblFilmeComprado = findViewById(R.id.lblFilmeComprado);
        lblNumeroSala = findViewById(R.id.lblNumeroSala);
        lblDescricaoHora = findViewById(R.id.lblDescricaoHora);
        lblQtdInteira = findViewById(R.id.lblQtdInteira);
        lblPrecoInteira = findViewById(R.id.lblPrecoInteira);
        lblQtdMeia = findViewById(R.id.lblQtdMeia);
        lblPrecoMeia = findViewById(R.id.lblPrecoMeia);
        lblQtdPipocaRefri = findViewById(R.id.lblQtdPipocaRefri);
        lblPrecoPipocaRefri = findViewById(R.id.lblPrecoPipocaRefri);
        lblValorTotal = findViewById(R.id.lblValorTotal);

        Bundle parametros = getIntent().getExtras();
        filme = new Filme();
        filme.setIdfilme(parametros.getInt("idfilme"));
        filme.setNome(parametros.getString("nome"));
        lblFilmeComprado.setText(filme.getNome());
        lblDescricaoHora.setText(parametros.getString("horario"));
        lblNumeroSala.setText(String.valueOf(parametros.getInt("numeroSala")));
        int qtdInteira = Integer.parseInt(parametros.getString("qtdInteira"));
        int qtdMeia = Integer.parseInt(parametros.getString("qtdMeia"));
        lblQtdInteira.setText(String.valueOf(qtdInteira));
        lblQtdMeia.setText(String.valueOf(qtdMeia));
        lblQtdPipocaRefri.setText(String.valueOf(parametros.getInt("lanche")));
        double precoInteira = parametros.getDouble("precoInteira");
        double precoLanche = parametros.getDouble("precoLanche");
        int qtdLanche = parametros.getInt("qtdLanche");
        double precoTotalInteira = qtdInteira*precoInteira;
        double precoTotalMeia = qtdMeia*(precoInteira/2);
        double precoTotalLanche = qtdLanche*precoLanche;

        lblPrecoInteira.setText("R$ "+String.valueOf(qtdInteira*precoInteira));
        lblPrecoMeia.setText("R$ "+String.valueOf(precoTotalMeia));
        lblPrecoPipocaRefri.setText("R$ "+String.valueOf(qtdLanche*precoLanche));
        lblValorTotal.setText("R$ "+String.valueOf(precoTotalInteira+precoTotalMeia+precoTotalLanche));
        sessao = new Sessao();
        ingresso = new Ingresso();
        sessao.setIdsessao(parametros.getInt("idsessao"));
        ingresso.setPrecoingresso(precoInteira);
        ingresso.setQtdinteira(qtdInteira);
        ingresso.setQtdmeia(qtdMeia);
        ingresso.setPipocarefrigerante(qtdLanche);
        ingresso.setPrecopipocarefrigerante(precoLanche);
        ingresso.setSessao(sessao);
        ingressoBD = new IngressoBD(this);
    }

    public void confirmarCompraIngresso(View view){
         ingressoBD.saveIngresso(ingresso);
        Intent intentPrincipal= new Intent(this, PrincipalActivity.class);
        startActivity(intentPrincipal);
    }
}
