package br.com.jef.agendav6.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jef.agendav6.R
import br.com.jef.agendav6.databinding.ActivityMainBinding
import br.com.jef.agendav6.ui.model.User
import br.com.jef.agendav6.ui.utils.Constants
import br.com.jef.agendav6.ui.utils.listener.GetUser

class MainActivity : AppCompatActivity(), GetUser {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            opneDialog()
        }


    }

    private fun initRecycler(user: List<User>) {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = Adapter(user, this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu -> {
                toggleDarkMode()

                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
                true
            }

        }
    }


    private fun opneDialog() {
        val dialog = AddDialogFragment()
        dialog.show(supportFragmentManager, dialog.tag)
    }

    fun toggleDarkMode() {
        val modoNoturno =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(
            if (modoNoturno) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        )
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {

        if (isDarkModeEnabled()) {
            val item = menu.findItem(R.id.main_menu)
            item.title = getString(R.string.light_mode)
            return super.onPrepareOptionsMenu(menu)
        } else
            return false
    }

    fun isDarkModeEnabled(): Boolean {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }


    override fun userList(user: List<User>) {
        initRecycler(user)

    }

    override fun onClick(user: User) {
        val intent = Intent(this, Resume::class.java)
        intent.putExtra(Constants.NAME, user.name)
        intent.putExtra(Constants.TELEPHONE_TYPE, user.typeTel)
        intent.putExtra(Constants.TELEPHONE, user.telephone)
        intent.putExtra(Constants.BIRTH_DATE, user.birthDate)
        intent.putExtra(Constants.GENDER, user.gender)
        startActivity(intent)

    }




}
