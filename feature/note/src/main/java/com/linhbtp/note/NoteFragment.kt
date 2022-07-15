package com.linhbtp.note

import com.linhbtp.note.databinding.FragmentNoteBinding
import com.template.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : BaseFragment<FragmentNoteBinding, NoteViewModel>() {

    override fun initControl() {

    }

    override fun initView() {
    }

    override fun initViewModel() {
    }

    override fun getLayout(): Int = R.layout.fragment_note
}