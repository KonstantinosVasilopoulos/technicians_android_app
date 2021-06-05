package gr.aueb.team14.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import gr.aueb.team14.R

class AddressesFragment : Fragment() {
    private lateinit var addressField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addresses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addressField = getView()?.findViewById(R.id.addressField) as EditText
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = AddressesFragment()
    }

    fun getAddress(): String = addressField.text.toString()
}