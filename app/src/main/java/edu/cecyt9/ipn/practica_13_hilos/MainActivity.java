package edu.cecyt9.ipn.practica_13_hilos;

import android.app.Activity;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;


public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;
    private TextView salida2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        salida2 = (TextView) findViewById(R.id.salida2);
    }

    class MiThread extends Thread {
        private int n, res;

        public MiThread(int n) {
            this.n = n;
        }


        @Override
        public void run() {
            res = factorial(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida.append(res + "\n");
                }
            });
        }
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida.append(n + "! = ");
        salida2.append(n + "! = ");
        MiThread thread = new MiThread(n);
        hilo2 hilo2 = new hilo2(n);
        thread.start();
        hilo2.start();
    }

	class hilo2 extends Thread {
        private int n, res;

        public hilo2(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = cosa(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida2.append(res + "\n");
                }
            });
        }
    }

    public int factorial (int n){
        int res = 1;
        for (int i=1; i<=n; i++){
            res*=i;
            SystemClock.sleep(1000);
        }
        return res;
    }

    public int cosa (int n){
        int res = 0;
        int res2 = 1;
        int res3 = 0;
        for (int i=1; i<=n; i++){
            if(i%3 == 0){
                res2 = res + res3;
            }
            else{
                if(i%3 == 1){
                    res = res2 + res3;
                }
                else{
                    res3 = res + res2;
                }
            }
            SystemClock.sleep(1000);
        }
        return res;
    }
}