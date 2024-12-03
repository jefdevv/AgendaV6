package br.com.jef.agendav6.ui

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import br.com.jef.agendav6.R
import br.com.jef.agendav6.databinding.ActivityFormBinding
import br.com.jef.agendav6.ui.utils.ToastUtil
import br.com.jef.agendav6.ui.model.User
import br.com.jef.agendav6.ui.utils.listener.GetUser
import java.text.SimpleDateFormat
import java.util.Locale

class AddDialogFragment : DialogFragment() {
    private lateinit var callback: GetUser
    private lateinit var binding: ActivityFormBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        binding = ActivityFormBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val calendario = Calendar.getInstance()
        val fecha = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            calendario.set(Calendar.YEAR, year)
            calendario.set(Calendar.MONTH, month)
            calendario.set(Calendar.DAY_OF_MONTH, day)
            setBirthDate(calendario)
        }

        binding.edtBirthDateMain.setOnClickListener() {
            DatePickerDialog(
                view.context,
                fecha,
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnCancel()

        btnSaveUser(view)

    }


    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

    }


    private fun setBirthDate(calendar: Calendar) {
        val formatoFecha = "dd/MM/yyyy"
        val formatoSimples = SimpleDateFormat(formatoFecha, Locale.ENGLISH)
        val birthText = formatoSimples.format(calendar.time)
        binding.edtBirthDateMain.setText(birthText)
    }

    private fun btnCancel() {
        binding.btnCancel.setOnClickListener() {
            dismiss()
        }

    }

    private fun saveUser(view: View) {
        val name = binding.edtNameMain.text.toString()
        val typeTel = getTypeTelephone()
        val tel = binding.edtTelMain.text.toString()
        val birth = binding.edtBirthDateMain.text.toString()
        val gender = getGender(view)
        val user = User(name, typeTel, tel, birth, gender)


        val userData = mutableListOf<User>()
        userData.add(user)
        callback.userList(userData)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = context as GetUser

    }

    private fun btnSaveUser(view: View) {

        binding.btnSave.setOnClickListener() {

            if (!isValid()) {
                saveUser(view)
                dismiss()
            }


        }


    }

    private fun getGender(view: View): String {
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val checkedId = radioGroup.checkedRadioButtonId
        val radioButton = view.findViewById<RadioButton>(checkedId)
        val genderText = radioButton.text.toString()

        return genderText
    }

    private fun getTypeTelephone(): String {
        val spinner = binding.spinnerMain
        val position = spinner.selectedItemPosition
        val selected = spinner.adapter.getItem(position).toString()

        return selected
    }


    private fun isValid(): Boolean {
        if (binding.edtNameMain.text?.isEmpty() == true) {
            binding.edtNameMain.error = getString(R.string.empty_field)
            return true
        } else if (binding.edtTelMain.text?.isEmpty() == true) {
            binding.edtTelMain.error = getString(R.string.empty_field)
            return true
        } else if (binding.edtBirthDateMain.text?.isEmpty() == true) {
            binding.edtBirthDateMain.error = getString(R.string.empty_field)
            return true

        } else if (binding.spinnerMain.selectedItem == getString(R.string.select_telephone_type)) {
            ToastUtil.mostrarMensagem(requireContext(), getString(R.string.select_telephone_type))
            return true
        } else {

            return false
        }

    }


}