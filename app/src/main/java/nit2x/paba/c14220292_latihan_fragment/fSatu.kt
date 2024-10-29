package nit2x.paba.c14220292_latihan_fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fSatu.newInstance] factory method to
 * create an instance of this fragment.
 */
class fSatu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var gridGame: GridLayout
    private lateinit var _tvModal: TextView
    private lateinit var _btnNyerah: Button
    private var nilai = 50
    private var pairsFound = 0
    private var firstClickedButton: Button? = null
    private var firstClickedNumber: Int? = null
    private var numberRangeStart = 1

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
        val view = inflater.inflate(R.layout.fragment_f_satu, container, false)

        gridGame = view.findViewById(R.id.gridAngka)
        _tvModal = view.findViewById(R.id.tvModal)
        _btnNyerah = view.findViewById(R.id.btnNyerah)

        setupGame()
        _btnNyerah.setOnClickListener { navigateToResult() }

        return view
    }

    private fun setupGame() {
        val numbers = generateRandomNumbers()
        for (i in numbers.indices) {
            val button = Button(requireContext()).apply {
                text = "?"
                tag = numbers[i]
                setOnClickListener { onNumberClicked(this) }
            }
            gridGame.addView(button)
        }
    }

    private fun generateRandomNumbers(): MutableList<Int> {
        val numbers = mutableListOf<Int>()
        for (i in numberRangeStart until numberRangeStart + 5) {
            numbers.add(i)
            numbers.add(i)
        }
        numbers.shuffle()
        return numbers
    }

    private fun onNumberClicked(button: Button) {
        val clickedNumber = button.tag as Int

        if (firstClickedButton == null) {
            firstClickedButton = button
            firstClickedNumber = clickedNumber
            button.text = clickedNumber.toString()
        } else {
            button.text = clickedNumber.toString()

            if (firstClickedNumber == clickedNumber) {
                pairsFound++
                nilai += 10
                _tvModal.text = "Modal Awal: $nilai"
                firstClickedButton = null
                firstClickedNumber = null
                
                if (pairsFound == 5) {
                    navigateToResult()
                }
            } else {
                nilai -= 5
                _tvModal.text = "Modal Awal: $nilai"
                
                Handler(Looper.getMainLooper()).postDelayed({
                    button.text = "?"
                    firstClickedButton?.text = "?"
                    firstClickedButton = null
                    firstClickedNumber = null
                }, 1000)
            }
        }
    }

    private fun navigateToResult() {
        val bundle = Bundle()
        bundle.putInt("nilaiAkhir", nilai)
        findNavController().navigate(R.id.action_gameFragment_to_resultFragment, bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fSatu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fSatu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}