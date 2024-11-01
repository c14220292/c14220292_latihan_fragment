package nit2x.paba.c14220292_latihan_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fTiga.newInstance] factory method to
 * create an instance of this fragment.
 */
class fTiga : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _etBatasAwal: EditText
    private lateinit var _btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_tiga, container, false)

        _etBatasAwal = view.findViewById(R.id.etBatasAwal)
        _btnSimpan = view.findViewById(R.id.btnSimpan)

        val inputBaru = requireContext().getSharedPreferences("PengaturanGame", Context.MODE_PRIVATE)
        val batasAwal = inputBaru.getInt("batasAwal", 1)
        _etBatasAwal.setText(batasAwal.toString())

        _btnSimpan.setOnClickListener {
            val batasAwalBaru = _etBatasAwal.text.toString().toIntOrNull()

            if (batasAwalBaru != null) {
                inputBaru.edit().putInt("batasAwal", batasAwalBaru).apply()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fTiga.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fTiga().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}