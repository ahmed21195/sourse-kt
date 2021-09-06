package com.app.telefric.makeen.presentation.view.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.app.telefric.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_otp.confirm
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {
    // get reference of the firebase auth
    lateinit var auth: FirebaseAuth
    var storedVerificationNum : String =""


    // we will use this to match the sent otp from firebase
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        initFirebaseAuth()
        initFirebaseAuthToresend()
        countdown()

    }
    private fun initFirebaseAuth(){
        auth=FirebaseAuth.getInstance()

        // get storedVerificationId from the intent
        val storedVerificationId= intent.getStringExtra("storedVerificationId")
         storedVerificationNum= intent.getStringExtra("storedVerificationNum").toString()

        number.text=storedVerificationNum

        // fill otp and call the on click on button
        next.setOnClickListener {
            val otp = otp_view.otp.toString()

            if(otp.isNotEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(this,"Enter OTP", Toast.LENGTH_SHORT).show()
            }

        }
        back.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))

        }



    }
    // verifies if the code matches sent by firebase
    // if success start the new activity in our case it is main Activity
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    val intent = Intent(this , MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    showDialog()

                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, "Time out ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))




        dialog.setContentView(R.layout.custom_layout)

//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
        val intent = Intent(applicationContext,MainScreen::class.java)
        startActivity(intent)
        finish()


    }

    // count down
    private fun countdown(){
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer.text ="00 : "+ (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                // do something after countdown is done ie. enable button, change color

                timer.visibility= View.GONE
                resend.isClickable= true
                resend.isEnabled= true
                resend.setTextColor(ContextCompat.getColor(applicationContext,R.color.resendActive))

            }
        }.start()
    }


    //resend  methods

    private fun ressend() {

        sendVerificationCode(storedVerificationNum)

    }
    // this method sends the verification code
    // and starts the callback of verification
    // which is implemented above in onCreate
    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("GFG" , "Auth started")
    }
    private fun initFirebaseAuthToresend() {
        auth=FirebaseAuth.getInstance()
        // start verification on click of the button
        //resend code
        resend.setOnClickListener {
            KProgressHUD.create(this@OtpActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .show()


            ressend()



        }
        // Callback function for Phone Auth
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
                Log.d("GFG" , "onVerificationCompleted Success")
            }

            // Called when verification is failed add log statement to see the exception
            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("GFG" , "onVerificationFailed  $e")
            }

            // On code is sent by the firebase this method is called
            // in here we start a new activity where user can enter the OTP
            override fun onCodeSent(

                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Log.d("GFG","onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token



                // Start a new activity using intent
                // also send the storedVerificationId using intent
                // we will use this id to send the otp back to firebase
                val intent = Intent(applicationContext,OtpActivity::class.java)
                intent.putExtra("storedVerificationId",storedVerificationId)
                intent.putExtra("storedVerificationNum",storedVerificationNum)
                startActivity(intent)
                finish()
                KProgressHUD.create(this@OtpActivity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .dismiss()

            }
        }

    }


}