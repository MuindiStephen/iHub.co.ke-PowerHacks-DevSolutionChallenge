package com.steve_md.ndai.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.steve_md.ndai.R
import com.steve_md.ndai.databinding.FragmentPaymentBinding
import com.androidstudy.daraja.Daraja

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var daraja: Daraja

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding =  FragmentPaymentBinding.inflate(inflater, container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daraja= Daraja.with("vdGFxeLAnSG4kbE5A4JM8faaF0BULt62",
            "oLAV7OPavgaPtGP1",
            Env.SANDBOX,object:DarajaListener<AccessToken>{
                override fun onResult(result: AccessToken) {
                    Toast.makeText(this@MainActivity, "MPESA TOKEN :${result.access_token}", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: String?) {
                    Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_SHORT).show()

                }

            })
        binding.button.setOnClickListener {
            val phoneNumber=binding.editTextPhone.text.toString()
            val amt=binding.editTextTextPersonName.text.toString()
            val lnmExpress = LNMExpress(
                "174379", //Test credential but shortcode is mostly paybill number, email mpesa businnes fo clarification
                "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                TransactionType.CustomerPayBillOnline,  // TransactionType.CustomerPayBillOnline  <- Apply any of these two
                amt,
                phoneNumber,
                "174379",
                phoneNumber,
                "https://us-central1-mpesaapisecond.cloudfunctions.net/myCallbackUrl", // call back url send back payload info if the transactions went through. Its important inorder to update ui after user has paid, its essential but the service can work without it.
                "001ABC",
                "Goods Payment"
            )
            daraja.requestMPESAExpress(lnmExpress,object :DarajaListener<LNMResult>{
                override fun onResult(result: LNMResult) {
                    Toast.makeText(this@MainActivity, result.CustomerMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: String?) {
                    Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_SHORT).show()
                }

            })

        }
    }

}