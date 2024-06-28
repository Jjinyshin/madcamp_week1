package com.madcamp.tabapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.madcamp.tabapp.adapter.ContactAdapter
import com.madcamp.tabapp.databinding.FragmentContactsBinding
import com.madcamp.tabapp.model.ContactModel
import java.io.InputStreamReader

class ContactsFragment:Fragment(R.layout.fragment_contacts) {

    private lateinit var binding: FragmentContactsBinding
    private val contactList: ArrayList<ContactModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(layoutInflater)
        loadContactsFromJson()

        binding.breadStoreRv.apply {
            adapter = ContactAdapter(contactList)
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    private fun loadContactsFromJson() {
        val inputStream = resources.openRawResource(R.raw.contacts)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<ContactModel>>() {}.type
        val contacts: List<ContactModel> = Gson().fromJson(reader, type)
        contactList.addAll(contacts)
        println(contactList)
        reader.close()
    }
}