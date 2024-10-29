package nit2x.paba.c14220292_latihan_fragment

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            val fragmentDefault = fSatu()
            val inputBaru = getSharedPreferences("PengaturanGame", Context.MODE_PRIVATE)
            val batasAwal = inputBaru.getInt("batasAwal", 1)
            val bundle = Bundle()
            bundle.putInt("batasAwal", batasAwal)
            fragmentDefault.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragmentDefault)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHal1 -> openFragment(fSatu())
            R.id.menuHal2 -> openFragment(fDua())
            R.id.menuHal3 -> openFragment(fTiga())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment(fragment: Fragment) {
        if (fragment is fSatu) {
            val inputBaru = getSharedPreferences("PengaturanGame", Context.MODE_PRIVATE)
            val batasAwal = inputBaru.getInt("batasAwal", 1)
            val bundle = Bundle()
            bundle.putInt("batasAwal", batasAwal)
            fragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}