package com.lugares_u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lugares_u.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Objeto para realizar la comunicacion con FirebaseAuth
    private lateinit var auth: FirebaseAuth
    //Objeto para tener acceso a los controles creados en la vista...
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //fije la vista de contenido con lo que esta en activity_main.xml
        //Inicializo el objeto de autenticacion, realmente Firebase
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        //control para los botones
        binding.btLogin.setOnClickListener { haceLogin() }
        binding.btRegister.setOnClickListener { haceRegistro() }
    }

    private fun haceRegistro() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(correo,clave)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){ //si se hizo el registro
                    val user = auth.currentUser
                    refresca(user)
                } else { // si no se hizo el registro
                    Toast.makeText(baseContext, getString(R.string.msg_fallo), Toast.LENGTH_LONG)
                    refresca(null)
                }

            }
    }

    private fun refresca(user: FirebaseUser?) {
        if (user != null){
            //Me paso a la pantalla principal...
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    private fun haceLogin() {
        TODO("Not yet implemented")
    }


}